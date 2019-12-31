package com.jveda.messaging;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.LinkedBlockingQueue;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.jveda.entity.Request;

import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.slf4j.Logger;

import io.smallrye.reactive.messaging.kafka.KafkaMessage;

/**
 * Sender
 */
@ApplicationScoped
public class Sender {

  @Inject
  Logger logger;

  private BlockingQueue<Request> messages = new LinkedBlockingQueue<>();

  public void add(Request request) {
    messages.add(request);
  }

  @Outgoing("replay")
  public CompletionStage<KafkaMessage<String, Request>> send() {
    return CompletableFuture.supplyAsync(() -> {
      try {
        Request message = messages.take();
        logger.info("Sending message to Kafka topic 'requests'");
        return KafkaMessage.of("requests", "key", message);
      } catch (InterruptedException e) {
        logger.error("Exception while placing message into topic 'requests'", e);
        throw new RuntimeException(e);
      }
    });
  }
}
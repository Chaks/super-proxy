package com.jveda.messaging;

import com.jveda.entity.Request;
import io.smallrye.reactive.messaging.kafka.KafkaRecord;
import io.smallrye.reactive.messaging.kafka.OutgoingKafkaRecord;
import org.eclipse.microprofile.opentracing.Traced;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Sender
 */
@ApplicationScoped
@Traced
public class Sender {

  private final BlockingQueue<Request> messages = new LinkedBlockingQueue<>();
  @Inject
  Logger logger;

  public void add(Request request) {
    messages.add(request);
  }

  @Outgoing("replay")
  public CompletableFuture<OutgoingKafkaRecord<String, Request>> send() {
    return CompletableFuture.supplyAsync(() -> {
      try {
        Request message = messages.take();
        logger.info("Sending message to Kafka topic 'requests'");
        return KafkaRecord.of("requests", "key", message);
      } catch (InterruptedException e) {
        logger.error("Exception while placing message into topic 'requests'", e);
        throw new RuntimeException(e);
      }
    });
  }
}
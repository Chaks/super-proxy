package com.jveda.messaging;

import java.util.concurrent.CompletionStage;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.jveda.entity.Backend;
import com.jveda.entity.Request;
import com.jveda.entity.Response;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;

import io.smallrye.reactive.messaging.kafka.KafkaMessage;
import io.vertx.axle.core.Vertx;
import io.vertx.axle.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;

/**
 * Receiver
 */
@ApplicationScoped
public class Receiver {

  @Inject
  Logger logger;

  @Inject
  Backend backend;

  @Inject
  Vertx vertx;

  @Incoming("requests")
  public CompletionStage<Void> consume(KafkaMessage<String, Request> message) {
    logger.info("Received successfully from Kafka topic 'requests'");
    String key = message.getKey();
    Request payload = message.getPayload();
    // MessageHeaders headers = message.getHeaders();
    // Integer partition = message.getPartition();
    // Long timestamp = message.getTimestamp();
    logger.info("key: " + key);
    logger.info("payload: " + payload);

    WebClient client = WebClient.create(vertx,
        new WebClientOptions().setDefaultHost(payload.getMetadata().getHostName())
            .setDefaultPort(payload.getMetadata().getPort()).setSsl(payload.getMetadata().isSsl()));

    client.get("/" + payload.getMetadata().getContext()).send().thenApply(res -> {
      Response response = new Response();
      response.setStatusCode(res.statusCode());
      response.setResponse(res.bodyAsString());
      logger.info("response: " + response);
      vertx.executeBlocking(future -> {
        backend.store(payload, response);
      });
      return null;
    });

    logger.info("Message acknowledged");
    return message.ack();
  }

}
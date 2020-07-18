package com.jveda.service;

import com.datastax.oss.driver.shaded.guava.common.hash.Hasher;
import com.datastax.oss.driver.shaded.guava.common.hash.Hashing;
import com.jveda.entity.Exchange;
import com.jveda.entity.Metadata;
import com.jveda.entity.Request;
import com.jveda.entity.Response;
import com.jveda.entity.service.ExchangeService;
import com.jveda.entity.service.ExchangeServiceReactive;
import io.smallrye.mutiny.Uni;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.client.WebClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.nio.charset.Charset;

@ApplicationScoped
public class RestInvoker implements Invoker {
  @Inject
  ExchangeServiceReactive exchangeServiceReactive;

  @Inject
  ExchangeService exchangeService;

  @Inject
  Vertx vertx;

  @Override
  public Uni<Response> execute(Request request) {
    Metadata metadata = request.getMetadata();
    WebClient client = WebClient.create(vertx,
        new WebClientOptions().setDefaultHost(metadata.getHostName())
            .setDefaultPort(metadata.getPort())
            .setSsl(metadata.isSsl())
            .setTrustAll(true));

    Response response = new Response();
    exchangeServiceReactive.get(buildHash(request))
        .onItem()
        .apply(item -> {
          response.setResponse(item.getResponse());
          response.setStatusCode(200);
          return null;
        });

    if (response.getResponse() == null) {
      return client.get("/" + metadata.getContext() + "/" + metadata.getPathParam())
          .send()
          .onItem()
          .apply(result -> {
            Response httpResponse = new Response();
            httpResponse.setStatusCode(result.statusCode());
            httpResponse.setResponse(result.bodyAsString());

            Exchange exchange = new Exchange();
            exchange.setHashCode(buildHash(request));
            exchange.setContext(metadata.getContext());
            exchange.setResponse(result.bodyAsString());
            exchangeServiceReactive.save(exchange);
            //exchangeService.save(exchange);

            return httpResponse;
          });
    }

    return Uni.createFrom().item(response);
  }

  private String buildHash(Request request) {
    Hasher hasher = Hashing.murmur3_32().newHasher();
    hasher.putString(request.getPayload(), Charset.defaultCharset());
    return hasher.hash().toString();
  }
}

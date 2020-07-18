package com.jveda.resources;

import com.jveda.entity.Metadata;
import com.jveda.entity.Request;
import com.jveda.service.Invoker;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.opentracing.Traced;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Proxy
 */
@ApplicationScoped
@Path("/")
@Traced
public class Proxy {

  @ConfigProperty(name = "api.allowed.contexts")
  List<String> allowedContexts;

  @Inject
  Map<String, Metadata> metadataMap;

  @Inject
  Invoker restInvoker;

  @Inject
  Logger logger;

  @GET
  @Path("ping")
  @Produces(MediaType.TEXT_PLAIN)
  public String ping() {
    return "15 packets transmitted, 15 received, 0% packet loss, time 14021ms";
  }

  @GET
  @Path("{context:.*}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Timed(name = "stats", unit = MetricUnits.MILLISECONDS)
  public Uni<Response> handleGet(@Context HttpHeaders headers,
                                 @PathParam("context") String context,
                                 String payload) {
    logger.info("context: " + context);
    Metadata metadata = null;
    if (allowedContexts.contains(context)) {
      metadata = metadataMap.get(context);
    } else {
      String matchedContext =
          allowedContexts.stream()
              .filter(element -> context.startsWith(element))
              .findFirst()
              .get();
      if(null != matchedContext) {
        metadata = metadataMap.get(matchedContext);
        metadata.setPathParam(getPathParam(context));
      }
    }
    logger.info("metadata: " + metadata);

    Request request = new Request();
    request.setMetadata(metadata);
    request.setPayload(payload);
    request.getRequestHeaders().putAll(headers.getRequestHeaders());

    logger.info("request {}", request);
    Uni<com.jveda.entity.Response> responseUni = restInvoker.execute(request);
    return responseUni
        .onItem()
        .apply(item -> {
          Response.Status status = null;
          if (200 == item.getStatusCode()) {
            status = Response.Status.OK;
          }
          return Response.status(status)
              .entity(item.getResponse())
              .build();
        })
        .onFailure().apply(failure -> {
          return new Throwable(failure.getMessage());
        });
  }

  private boolean isMockRequest(String requestType) {
    return null != requestType && Request.Type.MOCK.name().equals(requestType.toUpperCase());
  }

  private String getPathParam(String context) {
    return Arrays.stream(context.split("/")).reduce((first, second) -> second).get();
  }
}
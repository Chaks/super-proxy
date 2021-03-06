package com.jveda.resources;

import com.jveda.entity.Metadata;
import com.jveda.entity.Request;
import com.jveda.messaging.Sender;
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
import javax.ws.rs.core.Response.Status;
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
  Sender sender;

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
  @Produces(MediaType.TEXT_PLAIN)
  public Response check(@PathParam("context") String context) {
    Status status;
    if (allowedContexts.contains(context)) {
      status = Status.OK;
    } else {
      status = Status.NOT_FOUND;
    }
    return Response.status(status).build();
  }

  @POST
  @Path("{context:.*}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.TEXT_PLAIN)
  @Timed(name = "stats", unit = MetricUnits.MILLISECONDS)
  public Response handle(@Context HttpHeaders headers, @PathParam("context") String context, String payload) {
    logger.info("context: " + context);
    logger.info("meatadataMap: " + metadataMap);
    Status status;
    if (allowedContexts.contains(context)) {
      status = Status.OK;
      Request request = new Request();
      request.getRequestHeaders().put("correlationId", headers.getHeaderString("correlationId"));
      request.setMetadata(metadataMap.get(context));
      logger.info("request: " + request);
      sender.add(request);
    } else {
      status = Status.NOT_FOUND;
    }
    return Response.status(status).build();
  }

}
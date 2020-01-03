package com.jveda.entity;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.control.ActivateRequestContext;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.eclipse.microprofile.opentracing.Traced;
import org.slf4j.Logger;

/**
 * Backend
 */
@ApplicationScoped
public class Backend {

  @Inject
  Logger logger;

  @Transactional
  @ActivateRequestContext
  @Traced
  public void store(Request request, Response response) {
    Service service = new Service();
    service.setContext(request.getMetadata().getContext());
    service.setPayload(request.getPayload());
    service.setResponse(response.getResponse().replaceAll("[\\n\\t ]", ""));
    service.setCorrelationId((String) request.getRequestHeaders().get("correlationId"));
    logger.info("Persisting...");
    service.persistAndFlush();
  }

}
package com.jveda.entity;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.control.ActivateRequestContext;
import javax.inject.Inject;
import javax.transaction.Transactional;

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
  public void store(Request request, Response response) {
    Service service = new Service();
    service.setContext(request.getMetadata().getContext());
    service.setPayload(request.getPayload());
    service.setResponse(response.getResponse().substring(0, 254).replaceAll("[\\n\\t ]", ""));
    logger.info("Persisting...");
    service.persistAndFlush();
  }

}
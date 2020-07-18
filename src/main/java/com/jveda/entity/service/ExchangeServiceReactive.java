package com.jveda.entity.service;

import com.jveda.entity.Exchange;
import com.jveda.entity.dao.ExchangeDaoReactive;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ExchangeServiceReactive {
  @Inject
  ExchangeDaoReactive exchangeDaoReactive;

  public Uni<Void> save(Exchange exchange) {
    return exchangeDaoReactive.updateAsync(exchange);
  }

  public Multi<Exchange> get(String hash) {
    return exchangeDaoReactive.findByIdAsync(hash);
  }
}

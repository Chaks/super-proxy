package com.jveda.entity.service;

import com.jveda.entity.Exchange;
import com.jveda.entity.dao.ExchangeDao;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ExchangeService {
  @Inject
  ExchangeDao exchangeDao;

  public void save(Exchange exchange) {
    exchangeDao.update(exchange);
  }

  public Exchange get(String hash) {
    return exchangeDao.findById(hash).one();
  }
}

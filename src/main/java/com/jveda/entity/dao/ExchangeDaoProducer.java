package com.jveda.entity.dao;


import com.datastax.oss.quarkus.runtime.api.session.QuarkusCqlSession;
import com.jveda.entity.mapper.ExchangeMapper;
import com.jveda.entity.mapper.ExchangeMapperBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

public class ExchangeDaoProducer {
  private final ExchangeDao exchangeDao;
  private final ExchangeDaoReactive exchangeDaoReactive;

  @Inject
  public ExchangeDaoProducer(QuarkusCqlSession quarkusCqlSession) {
    ExchangeMapper exchangeMapper = new ExchangeMapperBuilder(quarkusCqlSession).build();
    exchangeDao = exchangeMapper.exchangeDao();
    exchangeDaoReactive = exchangeMapper.exchangeDaoReactive();
  }

  @Produces
  @ApplicationScoped
  ExchangeDao produceExchangeDao() {
    return exchangeDao;
  }

  @Produces
  @ApplicationScoped
  ExchangeDaoReactive produceExchangeDaoReactive() {
    return exchangeDaoReactive;
  }
}

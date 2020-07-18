package com.jveda.entity.mapper;

import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;
import com.jveda.entity.dao.ExchangeDao;
import com.jveda.entity.dao.ExchangeDaoReactive;

@Mapper
public interface ExchangeMapper {
  @DaoFactory
  ExchangeDao exchangeDao();
  @DaoFactory
  ExchangeDaoReactive exchangeDaoReactive();
}

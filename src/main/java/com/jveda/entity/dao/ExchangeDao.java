package com.jveda.entity.dao;

import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Select;
import com.datastax.oss.driver.api.mapper.annotations.Update;
import com.jveda.entity.Exchange;

@Dao
public interface ExchangeDao {
  @Select
  PagingIterable<Exchange> findById(String hashCode);

  @Update
  void update(Exchange exchange);
}

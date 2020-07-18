package com.jveda.entity.dao;

import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Select;
import com.datastax.oss.driver.api.mapper.annotations.Update;
import com.datastax.oss.quarkus.runtime.api.reactive.mapper.MutinyMappedReactiveResultSet;
import com.jveda.entity.Exchange;
import io.smallrye.mutiny.Uni;

@Dao
public interface ExchangeDaoReactive {
  @Select
  MutinyMappedReactiveResultSet<Exchange> findByIdAsync(String hashCode);

  @Update
  Uni<Void> updateAsync(Exchange exchange);
}

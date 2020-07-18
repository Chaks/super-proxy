package com.jveda.entity;

import com.datastax.oss.driver.api.mapper.annotations.ClusteringColumn;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;

import java.util.Objects;

/**
 * Exchange - RequestResponse or Operation
 */

@Entity
public class Exchange {
  @PartitionKey
  private String hashCode;
  @ClusteringColumn
  private String context;
  private String payload;
  private String response;
  private String correlationId;

  public Exchange() {
  }

  public Exchange(String hashCode,
                  String context,
                  String payload,
                  String response,
                  String correlationId) {
    this.hashCode = hashCode;
    this.context = context;
    this.payload = payload;
    this.response = response;
    this.correlationId = correlationId;
  }

  public String getHashCode() {
    return hashCode;
  }

  public void setHashCode(String hashCode) {
    this.hashCode = hashCode;
  }

  public String getContext() {
    return context;
  }

  public void setContext(String context) {
    this.context = context;
  }

  public String getPayload() {
    return payload;
  }

  public void setPayload(String payload) {
    this.payload = payload;
  }

  public String getResponse() {
    return response;
  }

  public void setResponse(String response) {
    this.response = response;
  }

  public String getCorrelationId() {
    return correlationId;
  }

  public void setCorrelationId(String correlationId) {
    this.correlationId = correlationId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Exchange exchange = (Exchange) o;
    return Objects.equals(hashCode, exchange.hashCode) &&
        Objects.equals(context, exchange.context) &&
        Objects.equals(payload, exchange.payload) &&
        Objects.equals(response, exchange.response) &&
        Objects.equals(correlationId, exchange.correlationId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(hashCode, context, payload, response, correlationId);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Service{");
    sb.append("hashCode='").append(hashCode).append('\'');
    sb.append(", context='").append(context).append('\'');
    sb.append(", payload='").append(payload).append('\'');
    sb.append(", response='").append(response).append('\'');
    sb.append(", correlationId='").append(correlationId).append('\'');
    sb.append('}');
    return sb.toString();
  }

}
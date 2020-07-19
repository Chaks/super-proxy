package com.jveda.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Service
 */
@Entity
public class Service extends PanacheEntity {
  private String context;
  private String payload;
  @Column(columnDefinition = "text")
  private String response;
  private String correlationId;

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
  public String toString() {
    return "Service [context=" + context + ", correlationId=" + correlationId + ", payload=" + payload + ", response="
        + response + "]";
  }

}
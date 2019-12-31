package com.jveda.entity;

import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

/**
 * Service
 */
@Entity
public class Service extends PanacheEntity {
  private String context;
  private String payload;
  private String response;

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

  @Override
  public String toString() {
    return "Service [context=" + context + ", payload=" + payload + ", response=" + response + "]";
  }

}
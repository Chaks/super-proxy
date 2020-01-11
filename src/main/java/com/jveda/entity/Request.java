package com.jveda.entity;

import java.util.HashMap;
import java.util.Map;

import io.quarkus.runtime.annotations.RegisterForReflection;

/**
 * Request
 */
@RegisterForReflection
public class Request {
  private Metadata metadata;
  private Map<String, Object> requestHeaders;
  private String payload;

  public Map<String, Object> getRequestHeaders() {
    if (requestHeaders == null) {
      requestHeaders = new HashMap<>();
    }
    return requestHeaders;
  }

  public String getPayload() {
    return payload;
  }

  public void setPayload(String payload) {
    this.payload = payload;
  }

  public Metadata getMetadata() {
    return metadata;
  }

  public void setMetadata(Metadata metadata) {
    this.metadata = metadata;
  }

  @Override
  public String toString() {
    return "Request [metadata=" + metadata + ", payload=" + payload + ", requestHeaders=" + requestHeaders + "]";
  }

}
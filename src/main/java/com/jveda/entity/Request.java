package com.jveda.entity;

import java.util.Map;

/**
 * Request
 */
public class Request {
  private Metadata metadata;
  private Map<String, Object> requestHeaders;
  private String payload;

  public Map<String, Object> getRequestHeaders() {
    return requestHeaders;
  }

  public void setRequestHeaders(Map<String, Object> requestHeaders) {
    this.requestHeaders = requestHeaders;
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
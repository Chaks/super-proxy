package com.jveda.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Request
 */
public class Request {
  private Metadata metadata;
  private Map<String, Object> requestHeaders;
  private String payload;
  private Type type;

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

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Request{");
    sb.append("metadata=").append(metadata);
    sb.append(", requestHeaders=").append(requestHeaders);
    sb.append(", payload='").append(payload).append('\'');
    sb.append(", type=").append(type);
    sb.append('}');
    return sb.toString();
  }

  public enum Type {
    MOCK
  }

}
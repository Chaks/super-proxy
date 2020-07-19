package com.jveda.entity;

import java.util.Map;

/**
 * Response
 */
public class Response {
  private Map<String, Object> responseHeaders;
  private Integer statusCode;
  private String response;

  public Map<String, Object> getResponseHeaders() {
    return responseHeaders;
  }

  public void setResponseHeaders(Map<String, Object> responseHeaders) {
    this.responseHeaders = responseHeaders;
  }

  public Integer getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(Integer statusCode) {
    this.statusCode = statusCode;
  }

  public String getResponse() {
    return response;
  }

  public void setResponse(String response) {
    this.response = response;
  }

  @Override
  public String toString() {
    return "Response [response=" + response + ", responseHeaders=" + responseHeaders + ", " +
        "statusCode=" + statusCode
        + "]";
  }

}
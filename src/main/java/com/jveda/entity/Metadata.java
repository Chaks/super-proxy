package com.jveda.entity;

/**
 * Metadata
 */
public class Metadata {

  private String hostName;
  private Integer port;
  private boolean ssl;
  private String context;
  private String pathParam;

  public Metadata() {
  }

  public String getHostName() {
    return hostName;
  }

  public void setHostName(String hostName) {
    this.hostName = hostName;
  }

  public Integer getPort() {
    return port;
  }

  public void setPort(Integer port) {
    this.port = port;
  }

  public boolean isSsl() {
    return ssl;
  }

  public void setSsl(boolean ssl) {
    this.ssl = ssl;
  }

  public String getContext() {
    return context;
  }

  public void setContext(String context) {
    this.context = context;
  }

  public String getPathParam() {
    return pathParam;
  }

  public void setPathParam(String pathParam) {
    this.pathParam = pathParam;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Metadata{");
    sb.append("hostName='").append(hostName).append('\'');
    sb.append(", port=").append(port);
    sb.append(", ssl=").append(ssl);
    sb.append(", context='").append(context).append('\'');
    sb.append(", pathParam='").append(pathParam).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
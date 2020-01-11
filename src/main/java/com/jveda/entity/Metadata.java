package com.jveda.entity;

import io.quarkus.runtime.annotations.RegisterForReflection;

/**
 * Metadata
 */
@RegisterForReflection
public class Metadata {

  private String hostName;
  private Integer port;
  private boolean ssl;
  private String context;

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

  @Override
  public String toString() {
    return "Metadata [context=" + context + ", hostName=" + hostName + ", port=" + port + ", ssl=" + ssl + "]";
  }

}
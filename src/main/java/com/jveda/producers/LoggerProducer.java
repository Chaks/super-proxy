package com.jveda.producers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 * Logger
 */
@Named
@Singleton
public class LoggerProducer {

  @Produces
  public Logger getLogger(InjectionPoint injectionPoint) {
    return LoggerFactory.getLogger(injectionPoint.getClass().getCanonicalName());
  }
}
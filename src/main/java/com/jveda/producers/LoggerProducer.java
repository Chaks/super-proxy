package com.jveda.producers;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Named;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
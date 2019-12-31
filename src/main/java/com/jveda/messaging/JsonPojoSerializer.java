package com.jveda.messaging;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

/**
 * JsonPojoSerializer
 */
public class JsonPojoSerializer<T> implements Serializer<T> {
  private final ObjectMapper objectMapper = new ObjectMapper();

  /**
   * Default constructor needed by Kafka
   */
  public JsonPojoSerializer() {
  }

  @Override
  public void configure(Map<String, ?> props, boolean isKey) {
  }

  @Override
  public byte[] serialize(String topic, T data) {
    if (data == null)
      return null;

    try {
      objectMapper.setSerializationInclusion(Include.NON_NULL);
      return objectMapper.writeValueAsBytes(data);
    } catch (Exception e) {
      throw new SerializationException("Error serializing JSON message", e);
    }
  }

  @Override
  public void close() {
  }

}
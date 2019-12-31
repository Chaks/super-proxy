package com.jveda.messaging;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jveda.entity.Request;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

/**
 * JsonPojoDeserializer
 */
public class JsonPojoDeserializer<T> implements Deserializer<T> {
  private ObjectMapper objectMapper = new ObjectMapper();

  private Class<T> tClass;

  /**
   * Default constructor needed by Kafka
   */
  public JsonPojoDeserializer() {
  }

  @SuppressWarnings("unchecked")
  @Override
  public void configure(Map<String, ?> props, boolean isKey) {
    if ("requests".equals(props.get("channel-name"))) {
      tClass = (Class<T>) Request.class;
    }
  }

  @Override
  public T deserialize(String topic, byte[] bytes) {
    if (bytes == null)
      return null;

    T data;
    try {
      data = objectMapper.readValue(bytes, tClass);
    } catch (Exception e) {
      e.printStackTrace();
      throw new SerializationException(e);
    }

    return data;
  }

  @Override
  public void close() {

  }

}
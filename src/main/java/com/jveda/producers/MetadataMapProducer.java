package com.jveda.producers;

import com.jveda.entity.Metadata;

import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * MetadataMapProducer
 */
@Named
@Singleton
public class MetadataMapProducer {

  @Produces
  public Map<String, Metadata> getServiceMap() {
    Jsonb jsonb = JsonbBuilder.create();
    List<Metadata> metadatas = jsonb.fromJson(getContent("/metadata.json"),
        new ArrayList<Metadata>() {
        }.getClass().getGenericSuperclass());

    Map<String, Metadata> serviceMap = metadatas.stream()
        .collect(Collectors.toMap(Metadata::getContext, metadata -> metadata));
    return serviceMap;
  }

  private String getContent(String fileName) {
    String content = null;
    try {
      InputStream inputStream = MetadataMapProducer.class.getResourceAsStream(fileName);
      byte[] tmp = new byte[4096];
      int length = inputStream.read(tmp);
      content = new String(tmp, 0, length, StandardCharsets.UTF_8);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return content;
  }
}
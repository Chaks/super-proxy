package com.jveda.producers;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import com.jveda.entity.Metadata;

/**
 * ServiceMapProducer
 */
@Named
@Singleton
public class MetadataMapProducer {

  @Produces
  public Map<String, Metadata> getServiceMap() {
    Jsonb jsonb = JsonbBuilder.create();
    List<Metadata> metadatas = jsonb.fromJson(getContent("metadata.json"), new ArrayList<Metadata>() {
    }.getClass().getGenericSuperclass());

    Map<String, Metadata> serviceMap = metadatas.stream()
        .collect(Collectors.toMap(Metadata::getContext, metadata -> metadata));
    return serviceMap;
  }

  private String getContent(String file) {
    String content = null;
    try {
      content = new String(Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource(file).toURI())));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return content;
  }
}
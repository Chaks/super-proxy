package com.jveda.service;

import com.jveda.entity.Request;
import com.jveda.entity.Response;
import io.smallrye.mutiny.Uni;

public interface Invoker {
  Uni<Response> execute(Request request);
}

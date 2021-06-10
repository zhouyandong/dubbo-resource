package org.apache.dubbo.demo;

import java.util.concurrent.CompletableFuture;

public interface DongService {

    String echo(String name);

    default CompletableFuture<String> echoAsync(String name) {
        return CompletableFuture.completedFuture(echo(name));
    }

}

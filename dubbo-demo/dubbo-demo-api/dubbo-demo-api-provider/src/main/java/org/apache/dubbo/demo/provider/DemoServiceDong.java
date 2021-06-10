package org.apache.dubbo.demo.provider;

import org.apache.dubbo.demo.DongService;
import org.apache.dubbo.rpc.RpcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class DemoServiceDong implements DongService {

    private static final Logger logger = LoggerFactory.getLogger(DemoServiceDong.class);

    @Override
    public String echo(String name) {
        System.out.println("dong : " + Thread.currentThread());
        logger.info("dong " + name + ", request from consumer: " + RpcContext.getContext().getRemoteAddress());
        return "dong " + name + ", response from provider: " + RpcContext.getContext().getLocalAddress();
    }

    @Override
    public CompletableFuture<String> echoAsync(String name) {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "dong " + name;
        });
        return cf;
    }
}

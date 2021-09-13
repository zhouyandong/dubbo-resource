# dubbo-resource

[![Build Status](https://github.com/apache/dubbo/workflows/Build%20and%20Test/badge.svg?branch=master)](https://github.com/apache/dubbo/actions/workflows/build-and-test.yml?query=branch%3Amaster+)
[![Build Status](https://api.travis-ci.com/apache/dubbo.svg?branch=master)](https://travis-ci.com/github/apache/dubbo)
[![Codecov](https://codecov.io/gh/apache/dubbo/branch/master/graph/badge.svg)](https://codecov.io/gh/apache/dubbo)
![Maven](https://img.shields.io/maven-central/v/org.apache.dubbo/dubbo.svg)
![License](https://img.shields.io/github/license/alibaba/dubbo.svg)
[![Average time to resolve an issue](http://isitmaintained.com/badge/resolution/apache/dubbo.svg)](http://isitmaintained.com/project/apache/dubbo "Average time to resolve an issue")
[![Percentage of issues still open](http://isitmaintained.com/badge/open/apache/dubbo.svg)](http://isitmaintained.com/project/apache/dubbo "Percentage of issues still open")
[![Tweet](https://img.shields.io/twitter/url/http/shields.io.svg?style=social)](https://twitter.com/intent/tweet?text=Apache%20Dubbo%20is%20a%20high-performance%2C%20java%20based%2C%20open%20source%20RPC%20framework.&url=http://dubbo.apache.org/&via=ApacheDubbo&hashtags=rpc,java,dubbo,micro-service)
[![Twitter Follow](https://img.shields.io/twitter/follow/ApacheDubbo.svg?label=Follow&style=social&logoWidth=0)](https://twitter.com/intent/follow?screen_name=ApacheDubbo)
[![Gitter](https://badges.gitter.im/alibaba/dubbo.svg)](https://gitter.im/alibaba/dubbo?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)

使用手册：https://dubbo.gitbooks.io/dubbo-user-book/content/

## Architecture

![Architecture](https://dubbo.apache.org/imgs/architecture.png)


## 基于dubbo进行RPC调用的demo

1.定义服务接口

```java
package org.apache.dubbo.samples.api;

public interface GreetingsService {
    String sayHi(String name);
}
```

2.实现服务接口

```java
package org.apache.dubbo.samples.provider;

import org.apache.dubbo.samples.api.GreetingsService;

public class GreetingsServiceImpl implements GreetingsService {
    @Override
    public String sayHi(String name) {
        return "hi, " + name;
    }
}
```

3.provider启动

```java
package org.apache.dubbo.samples.provider;


import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.samples.api.GreetingsService;

import java.util.concurrent.CountDownLatch;

public class Application {
    private static String zookeeperHost = System.getProperty("zookeeper.address", "127.0.0.1");

    public static void main(String[] args) throws Exception {
        ServiceConfig<GreetingsService> service = new ServiceConfig<>();
        service.setApplication(new ApplicationConfig("first-dubbo-provider"));
        service.setRegistry(new RegistryConfig("zookeeper://" + zookeeperHost + ":2181"));
        service.setInterface(GreetingsService.class);
        service.setRef(new GreetingsServiceImpl());
        service.export();

        System.out.println("dubbo service started");
        new CountDownLatch(1).await();
    }
}
```

4.consumer调用

```java
package org.apache.dubbo.samples.client;


import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.samples.api.GreetingsService;

public class Application {
    private static String zookeeperHost = System.getProperty("zookeeper.address", "127.0.0.1");

    public static void main(String[] args) {
        ReferenceConfig<GreetingsService> reference = new ReferenceConfig<>();
        reference.setApplication(new ApplicationConfig("first-dubbo-consumer"));
        reference.setRegistry(new RegistryConfig("zookeeper://" + zookeeperHost + ":2181"));
        reference.setInterface(GreetingsService.class);
        GreetingsService service = reference.get();
        String message = service.sayHi("dubbo");
        System.out.println(message);
    }
}
```

## dubbo大致实现逻辑

### provider服务暴露

### consumer服务引用

### consumer服务调用

## 代码debug入口

provider:org.apache.dubbo.demo.provider.Application.java

consumer:org.apache.dubbo.demo.consumer.Application.java


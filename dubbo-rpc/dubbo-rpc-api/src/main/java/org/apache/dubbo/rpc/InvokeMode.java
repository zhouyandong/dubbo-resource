/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.dubbo.rpc;

/**
 * 客户端服务调用的方式
 * 同步:调用者线程发送请求后 阻塞等待服务方返回response
 * 异步:调用者线程发送请求完成后向请求上下文写入一个Future 调用者线程不阻塞
 *      当网络层接收到response时网络线程会向Future中注入数据 并通知等待Future完成的线程
 * Future:和异步相差不大 区别是Future方式是 服务方接口返回值是Future类型 而异步方式是调用方自己包装的Future
 */
public enum InvokeMode {

    SYNC, ASYNC, FUTURE;

}

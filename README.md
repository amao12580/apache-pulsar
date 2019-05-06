# 多租户消息队列

研究目标：在多租户使用场景下，解决可靠消息队列的集群方案。
## Apache Pulsar
[Apache Pulsar](https://github.com/apache/pulsar)是一个企业级的多租户分布式消息系统，最初由Yahoo开发并在2016年开源，目前正在Apache基金会下孵化。Pulsar已经在Yahoo的生产环境使用了三年多，主要服务于Mail、Finance、Sports、 Flickr、 the Gemini Ads platform、 Sherpa以及Yahoo的KV存储。

Pulsar之所以能够称为下一代消息队列，主要是因为以下特性:

1.线性扩展。能够丝滑的扩容到成百上千个节点(Kafka扩容需要占用很多系统资源在节点间拷贝数据，而Pulsar完全不用)

2.高吞吐。已经在Yahoo的生产环境中经受了考验，每秒数百万消息

3.低延迟。在大规模的消息量下依然能够保持低延迟(< 5ms)

4.持久化机制。Pulsar的持久化机制构建在[Apache BookKeeper](https://github.com/apache/bookkeeper)之上，提供了写与读之前的IO隔离

5.基于地理位置的复制。Pulsar将多地域/可用区的复制作为首要特性支持。用户只需配置好可用区，消息就会被源源不断的复制到其他可用区。当某一个可用区挂掉或者发生网络分区，Pulsar会在之后不断的重试。

6.部署方式的多样化。既可以运行在裸机，也支持目前例如Docker、K8S的一些容器化方案以及不同的云厂商，同时在本地开发时也只需要一行命令即可启动整个环境。

7.易于监控。原生支持与prometheus集成，[http://ip:8080/metrics/](http://192.168.1.1:8080/metrics/)，监控内容包括：JVM、ZooKeeper、jetty、topic、producer、consumer

Topic支持多种消费模式：exclusive(独占消费)、shared(共享消费)、failover(失效消费)，同时支持Partition

producer支持批量生产-延迟投递模式，自定义路由类型(同一namespace下)，消息压缩，消息发送加密，自动分区，异步发送

consumer支持批量消费-批量确认模式，自动重试，故障转移，死信队列，pattern topics，异步消费，消息清理

安全方面，消息支持端到端的加密，集群与租户的ACL，数据存储隔离

## 协议标准支持情况
1.不支持AMQP协议

2.提供了官方[Connector](https://pulsar.apache.org/docs/zh-CN/io-connectors/)，支持从各类数据源同步数据

## Java客户端的支持情况
1.官方提供了基础Java类库，最新版本为[2.3.1](https://pulsar.apache.org/docs/zh-CN/client-libraries-java/)

### Client端

Client端 主要提供了PulsarClient、Consumer、Producer的定义和消息收发

### Admin端

Admin端 主要提供了tenant、namespace、cluster、broker、function、permission、topic、schema的管理

### spring boot 2.1.4.RELEASE

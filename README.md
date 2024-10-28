# muti-datasource-canal-client

spring-boot + canal-spring-boot-starter + mybatis-plus + dynamic-datasource-spring-boot-starter，多数据源 canal 客户端Demo。

# Quick Start

1.修改配置文件application-pre.yml。

```
# canal-server相关配置，你必须拥有一个可用的canal-server才能填写这些信息。
spring.canal.instances.example.host
spring.canal.instances.example.port
spring.canal.instances.example.user-name
spring.canal.instances.example.password
spring.canal.instances.example.subscribe

# 数据源配置，这里列出了两个，支持多个。
spring.datasource.dynamic.druid.datasource.default.url
spring.datasource.dynamic.druid.datasource.default.username
spring.datasource.dynamic.druid.datasource.default.password

spring.datasource.dynamic.druid.datasource.targetOne.url
spring.datasource.dynamic.druid.datasource.targetOne.username
spring.datasource.dynamic.druid.datasource.targetOne.password
```

2.阅读DemoConsumer.java,修改代码以扩展你的业务。

3.启动项目。

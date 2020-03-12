# a simple demo

使用idea运行

![running](running.png "running")
![eureka](eureka.png "eureka")

- / ，Ribbon负载均衡
- /user/{id} ，1~4返回User对象，其它随机返回或报错
- /delay ，服务调用超时引起熔断
- /err ，异常引起服务熔断

当我看完《深入了解 Spring Cloud 与微服务构建》的时候，我就打算将所学习到的 Spring Cloud 组件使用到项目中做成一个小项目，加深理解。

该项目是在个人 PC 运行，不一定长期启动...（出租房电费太贵了）。**正常运行时间为：8:00 ~ 22:00**<br>
项目Demo：[http://mugglelee.nat300.top/api-b/login.html](http://mugglelee.nat300.top/api-b/login.html)
账号：Tourist  
密码：123456  
（此账号设置了权限，不能对显示的信息删除或者修改，如有疑问，可在评论区与我一起探讨）

## 项目简介：

#### 登录模块：
- 账号登录
- 微信授权登录（由于是个体测试者，只能通过关注我个人的测试公众号才能微信授权，如有需要，可通过微信关注以下公众号）
- 手机号码登录：使用阿里云短信服务。通过帐号登录后可在页面右上方绑定手机号码，以后登录的话可以直接通过 **手机号码+验证码** 登录

![测试号二维码](https://raw.githubusercontent.com/MuggleLee/ManagePlatform/master/%E6%B5%8B%E8%AF%95%E5%8F%B7%E4%BA%8C%E7%BB%B4%E7%A0%81.jpg)

#### 管理后台模块：
- 菜单管理：菜单列表、添加菜单、修改菜单、删除菜单、菜单图标设置
- 角色管理：角色查询、添加角色、修改角色、删除角色、分配菜单、分配权限
- 权限管理：权限查询、添加权限、修改权限、删除权限
- 用户管理：用户查询、添加用户、修改用户、给用户分配角色、重置密码
- 文件管理：上传文件、文件列表、文件删除
- 邮件管理：发送邮件、查询邮件
- 注册中心：查看微服务注册情况
- 监控中心：监控微服务信息
- 接口swagger文档
- ip黑名单：查询、添加、删除ip黑名单
- 日志查询
- 个人信息修改
- 修改密码
- 头像修改


### 开发环境：
系统：Windows10 —— 项目的子项目和运行环境都通过启动 Docker 镜像运行。详情可查看父项目的 docker-compose.yml 配置

### 技术实现：
Spring Cloud (Finchley)、Spring Security、JDK8、Maven、Mysql 5.6、Mybatis-Plus、Redis5、Rabbitmq、Docker

### 项目结构：

├─CommonModel：基础Model<br>
├─CommonUnits：工具包<br>
├─ConfigCenter：配置中心<br>
├─FileCenter：文件上传中心<br>
├─GatewayCenter：网关中心<br>
├─LogCenter：日志中心<br>
├─LogStarter：日志配置<br>
├─ManageBackend：门户中心<br>
├─MonitorCenter：监控中心<br>
├─NotificationCenter：通知中心<br>
├─OauthCenter：权限中心<br>
├─RegisterCenter：注册中心<br>
├─UserCenter：用户中心<br>
├─buildImage：将所有子项目都打包后构建成镜像<br>
├─docker-compose.yml：构建容器<br>
├─Starting Sequence.md：查看子项目的启动顺序<br>


### 技术实现：
Spring Cloud (Finchley)、Spring Security、JDK8、Maven、Mysql 5.6、Mybatis-Plus、Redis5、Rabbitmq、Docker

## **技术选型：**

搭建框架：Spring Boot + Spring Cloud 2.0（Finchley版本）
好处：由于Spring Boot 默认大于配置的原则，可以快速搭建 Spring Cloud 微服务。

#### 注册中心：Eureka 
常用的注册中心有：Eureka，Zookeeper。那为什么选择使用 Eureka ？（没有实际项目支持说法，如有理解错误望帮助我改正...）
我觉得有以下两点：
```
1.Eureka是 Spring Cloud 首选推荐的服务注册和发现组件，可以和其它组件无缝对接；
2.分布式系统 CAP（Consistency，Availability，Partition tolerance）理论指出三种特性只能选其二。
Zookeeper 属于CP，所以对于 Zookeeper 来说，一致性高于可用性，优点是可以保证当前的节点信息是最新的，但当 master 节点故障会导致重新选举，而选举过程太长可能会导致整个zk集群不可用，导致瘫痪；
Eureka 属于AP，使用 Eureka 则不会因为几个节点挂掉而影响其他节点，但缺点也很明显，Eureka不保证强一致性，所以可能其他节点查询的信息并不是最新的。
```
###### ps.常见的服务注册组件还有 Consul，其属于 CA，但对此组件了解不多，因此也不作过多解释...
当前项目我并没有部署集群，也不必要保证当前的节点信息是最新的，另外为了能够与其他 Spring Cloud 组件无缝对接，我选择使用 Eureka。

#### 配置中心：Config
常见可以作为配置中心的组件还有Spring Cloud 提供的 Config 组件、携程开源的 Apollo 组件、阿里开源的 Nacos 的组件。
由于都没有试用过，了解也不深，因此我使用了 Spring Cloud 提供的 Config 组件，因为可以无缝的和其它微服务组件对接。不过以后有时间的话，还是需要了解一下这几个组件的区别和优缺点才行...

#### 网关中心：Zuul
常用的网关有 Nginx、OpenResty、Zuul 和 Spring Cloud Gateway。由于该项目对性能要求不高，所以没有使用高性能的 Nginx 和 Opensty。
而 Spring Cloud Gateway 作为了官方的网关组件，可以与 Spring Cloud 其它组件无缝对接。但现在网上相关的资料还是相对 Zuul 少。虽然 Zuul 是阻塞式，不支持长连接的，但现在网上的相关资料较多，出了问题还是比较容易找到解决办法，因此该项目选择了 Zuul 作为网关组件。
（不过正在考虑接下来将Zuul组件替代为Spring Cloud Gateway，据说配置更简单...）

#### 断路器：Hystrix
Spring Cloud 提供了带有熔断机制的框架——Hystrix。熔断器的作用是当某个服务节点出现故障可以通过快速失败机制保证其他远程调用服务不会导致系统卡死。因此在微服务中，熔断器是十分重要的，而 Spring Cloud 提供的 Hystrix 是当前比较热门的熔断器组件。

#### 监控中心：Spring Boot Admin
Spring Boot Admin 是一个针对 Spring Boot 的 Actuator 接口进行 UI 美化封装的监控工具。可以在列表中浏览所有被监控 Spring Boot 项目的基本信息，详细的 Health 信息、内存信息、JVM 信息、垃圾回收信息、各种配置信息（比如数据源、缓存列表和命中率）等，还可以直接修改 logger 的 level。
甚至根据监控的服务状况发送邮件或者短信通知管理员，譬如某个节点或者某个服务挂了，可以立即通知管理员上线解决问题。

#### 链路：Zipkin
Zipkin 是 Twitter 的一个开源项目，致力于手机分布式系统的链路数据，提供持久化策略，也提供UI页面方便开发人员查看接口，查询服务之间的调用链路信息。

#### 消息队列：RabbitMQ
常见的消息队列有 RabbitMQ、RocketMQ、ActiveMQ、Kafka。在实际项目中如何选型呢？这要根据各种 MQ 的特性和使用场景决定的。


||RabbitMQ|RocketMQ|Kafka|ActiveMQ|
|-|-|-|-|-|
|使用场景|在线业务场景|在线业务场景|大数据和流计算领域|在线业务场景|
|社区活跃度|社区活跃度高|活跃的中文社区|社区活跃度高|社区活跃度很低|
|优点|轻量级的消息队列，非常容易部署和使用，性能稳定|响应快，能达到ms级的响应|拥有强大的性能及吞吐量，兼容性好|存在时间长，有大量的参考文档，可用性高|
|缺点|性能和吞吐量较差，基于Erlang开发，不易进行二次开发|国内流行的消息队列，相对国外流程的 MQ ,在集成周边生态和兼容度会稍微差点|延迟比较高|社区活跃度低，维护越来越少|


#### 数据库：Mysql5.6

#### 持久层框架：Mybatis-Plus
项目一开始是使用 Mybatis 作为持久层框架，项目完成之后看到一些文章介绍 Mybatis 的升级版——Mybatis-Plus。
打开[Mybatis-Plus的官网](https://mybatis.plus/)，最吸引眼球的就是其 slogan ——“为简化开发而生”。原先的 Mybatis 框架虽然能够很灵活的操作 SQL 语句，但弊端是要创建大量的 XML 文件，后期也不容易维护。而Mybatis-Plus不再需要创建XML文件，只需要定义一些接口，直接调用CRUD的方法，也很方便的使用注释自定义SQL语句，热加载、代码生成、分页、性能分析等功能一应俱全。不仅如此，Mybatis-Plus 还有函数式编程的特性，可以发现项目中大量的使用 Stream 流，拼装对象属性和CRUD不再是几十行冗长的代码，而是简简单单的几行甚至一行代码就能搞掂，大大提高了开发效率！更多 Mybatis-Plus 使用参考官方。（力推学习！）

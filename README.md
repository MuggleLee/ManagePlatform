当我看完《深入了解 Spring Cloud 与微服务构建》的时候，我就打算将所学习到的 Spring Cloud 组件使用到项目中做成一个小项目，加深理解。

该项目是在个人 PC 运行，不一定长期启动...（出租房电费太贵了）。**正常运行时间为：8:00 ~ 22:00**
项目Demo：[http://mugglelee.nat300.top/api-b/login.html](http://mugglelee.nat300.top/api-b/login.html)
账号：Tourist  
密码：123456  
（此账号设置了权限，不能对显示的信息删除或者修改，如有疑问，可在评论区与我一起探讨）

## 项目简介：

#### 登录模块：
- 账号登录
- 微信授权登录（由于是个体测试者，只能通过关注我个人的测试公众号才能微信授权，如有需要，可通过微信关注以下公众号）
- 手机号码登录：使用阿里云短信服务。通过帐号登录后可在页面右上方绑定手机号码，以后登录的话可以直接通过 **手机号码+验证码** 登录

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



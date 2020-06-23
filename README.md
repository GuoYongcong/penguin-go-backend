# Penguin GO Backend

## 项目运行
演示地址：http://localhost:8090

用户名：admin 密码：admin

温馨提示：
若曾在演示环境删除数据，可以点击头像菜单 -> 选择 -> 备份还原，恢复到系统默认备份数据。

<br/>
<br/>

## 参与贡献（ddl暂定为 **5月15日**）

1. Fork 本项目
   
2. 新建 Feat_姓名缩写 分支
   
3. 提交代码  
   
4. 新建 Pull Request 到 **dev 分支（开发分支）**  
   
5. 建议同时参与前后的编程工作，降低前后端实现冲突的情况

<br/>
<br/>

## 参考资料

- [廖雪峰的Java教程](https://www.liaoxuefeng.com/wiki/1252599548343744/1255945359327200)  
   
- [朝雨忆轻尘的博客](https://www.cnblogs.com/xifengxiaoma/)
- [Vue.js官网教程](https://cn.vuejs.org/v2/guide/)
- [Element](https://element.eleme.io/#/zh-CN/)
- [Font Awesome](https://fontawesome.com.cn/)

<br/>
<br/>

## 分支说明

- master: 主推版本分支，支持分布式，服务治理（Spring Security + Spring Cloud）

- dev: 开发分支，适时合并到master分支，支持分布式，服务治理（Spring Security + Spring Cloud）

- shiro-cloud: 基于Spring Boot + Shiro + Spring Cloud，支持分布式、服务治理

- shiro: 基于Spring Boot + Shiro，不包含 Spring Cloud，部署环境稍微简单一点

<br/>
<br/>

## 项目介绍

- Penguin GO 是基于 Spring Boot、Spring Cloud、Vue、Element 实现的 Java 快速开发平台。

- 基于此平台搭建实验系统。

<br/>
<br/>

## 功能列表

- 登录系统
  - [x] 账户登录：系统用户登录，系统登录认证（token方式）
  - 账户注册：根据微信小程序的OpenId、UnionId，或者邮箱、学号等进行注册
  - 单点登录（咕咕咕）：利用 OAuth2, 提供统一的单点登录功能
  - 系统登录（咕咕咕）：集成第三方登录功能（QQ、微信、微博）

- 系统管理
  - [x] 用户管理：新建用户，修改用户，删除用户，查询用户
  - [x] 机构管理：新建机构，修改机构，删除机构，查询机构
  - [x] 角色管理：新建角色，修改角色，删除角色，查询角色
  - [x] 菜单管理：新建菜单，修改菜单，删除菜单，查询菜单
  - [x] 字典管理：新建字典，修改字典，删除字典，查询字典
  - [x] 系统日志：记录用户操作日志，查看系统执行日志记录  

- 系统监控
  - [x] 数据监控：定制Druid信息，提供简洁有效的SQL监控
  - [x] 服务监控：集成Spring Boot Admin，实现服务监控

- 服务治理
  - [x] 服务治理：集成Consul注册中心，实现服务注册和发现
  
- 功能辅助
  - 系统首页：展示功能介绍
  - [x] 备份还原：系统备份还原，一键恢复系统初始化数据
  - [x] 主题切换：支持主题切换，自定主题颜色，一键换肤
  - [x] 接口文档：定制在线文档，提供简洁美观的API文档
  - [x] 代码生成：提供代码生成器，最大化的降低代码开发量
  
- Spring Cloud（咕咕咕）
  - [x] 服务消费：集成Ribbon、Feign，服务调用和负载均衡
  - [x] 服务熔断：集成Hystrix、Turbine，实现熔断和监控
  - [x] 服务网关：集成Spring Cloud Zuul，实现API网关
  - [x] 链路追踪：集成Sleuth、Zipkin，实现分布式链路追踪
  - [x] 配置中心：集成Config、Bus，实现分布式配置中心

- 实验项目：主试和被试都可以查看的近期实验列表，可以进行实验筛选查看，实验详情浏览与报名。
  
- 主试实验管理：包含主试管理和实验管理。  
  - 主试管理：管理员对申请成为主试的人进行审核和管理，并对其发布的实验进行审核和管理（与角色管理类似，即所有主试的实验管理模块）。
  - 实验管理：主试可以在此管理自己创建的实验，进行编辑、发布、进度跟踪、管理指定实验的被试等。
  
- 被试实验管理：包含被试管理和实验管理。
  - 被试管理：管理员对申请成为被试的人进行审核和管理，并对其参与的实验进行查看（与角色管理类似，即所有被试的实验管理模块）。
  - 实验管理：被试在此管理自己参与的实验，进行信息编辑，进度跟踪等。
  
- 账号管理：在此可以查看自己的账号类型（主试、被试、管理员等），查看实验统计、编辑个人信息、变更头像、修改昵称等。

- ...

<br/>
<br/>

## 软件架构

### 后端架构

#### 开发环境

- IDE : eclipse 4.13.x
- JDK : JDK11（部署版本为 JDK1.8） 
- Maven : Maven 3.5.x
- MySQL: MySQL 5.7.x

#### 技术选型

- 核心框架：Spring Boot 2.x
- 服务治理：Spring Cloud Finchley
- 安全框架：Spring Security 5.x
- 视图框架：Spring MVC 5.x
- 持久层框架：MyBatis 3.x
- 数据库连接池：Druid 1.x
- 日志管理：SLF4J、Log4j

#### 项目结构

- penguin-common： 公共代码模块，主要放置工具类
- penguin-core： 核心代码模块，主要封装公共业务模块
- penguin-admin： 后台管理模块，包含用户、角色、菜单管理等
- penguin-backup： 系统数据备份备份模块，可选择独立部署
- penguin-monitor： 系统监控服务端，监控Spring Boot服务模块
<br/></br>
- penguin-producer： 服务提供者示例，方便在此基础上搭建模块
- penguin-consumer： 服务消费者示例，方便在此基础上搭建模块
- penguin-hystrix： 服务熔断监控模块，收集汇总熔断统计信息
- penguin-zuul： API服务网关模块，统一管理和转发外部调用请求
<br></br>
- penguin-consul： 注册中心，安装说明目录，内附安装引导说明
- penguin-zipkin： 链路追踪，安装说明目录，内附安装引导说明
- penguin-pom： 聚合模块，仅为简化打包，一键执行打包所有模块
<br></br>
### 前端架构

#### 开发环境

- IDE : Php Storm 2019.2
- NODE: Node 8.9.x
- NPM : NPM 6.4.x

#### 技术选型

- 前端框架：Vue 2.x
- 页面组件：Element 2.x
- 状态管理：Vuex 2.x
- 后台交互：axios 0.18.x
- 图标使用：Font Awesome 4.x

#### 项目结构

- assets： 图标、字体、国际化信息等静态信息
- components： 组件库，对常用组件进行封装
- http： 后台交互模块，统一后台接口请求API
- i18n： 国际化模块，使用Vue i18n进行国际化
- mock： Mock模块，模拟接口调用并返回定制数据
- permission： 权限控制模块，处理权限认证逻辑
- router： 路由管理模块，负责页面各种路由配置
- store： 状态管理模块，提供组件间状态共享
- utils： 工具模块，提供一些通用的工具方法
- views： 页面模块，主要放置各种页面视图组件

<br/>
<br/>

## 安装教程

### 后端安装

1. 下载源码

    - git clone

2. 导入工程

    - 使用 Eclipse 导入 Maven 项目，在此之前请确认已安装 JDK 和 Maven 工具。

3. 编译代码

    - 找到 penguin-pom 工程的 pom.xml，执行 mvn clean install 命令编译一键打包。
    - 一般来说不会有什么问题，如果还是编译不成功，可以按照优先级逐个编译试一试。

4. 导入数据库

    - 新建 penguin 数据库，导入 penguin-admin 工程 doc/penguin.sql 脚本，导入初始化数据库。   
    - 修改 penguin-admin 下 application.yml 中的数据库连接和账号密码为自己的数据库配置。
    - 修改 penguin-backup 下 application.yml 中的数据库连接和账号密码为自己的数据库配置。

5. 启动系统

    - 找到 penguin-consul 工程，根据安装说明安装注册中心，完成后执行 consul agent -dev启动。
    - 找到 penguin-zipkin 工程，根据安装说明安装zipkin，如果不需要链路追踪服务的可以不用。
    - 找到 penguin-monitor 工程下的 PenguinMonitorApplication, 执行 Java 程序，启动项目。
    - 找到 penguin-admin 工程下的 PenguinAdminApplication, 执行 Java 程序，启动项目。
    - 找到 penguin-backup 工程下的 PenguinBackupApplication.java, 执行 Java 程序，启动项目。
    - 包含以上模块已经可以满足权限管理系统的运行了，其他模块根据各自需要选择性启动。
<br></br>
### 前端安装

1. 下载源码

    - git clone 

2. 编译代码

    - 进入项目根目录，执行 npm install, 下载和安装项目相关依赖包。

3. 启动系统

   - 执行 npm run dev 命令，启动项目，通过 http://localhost:8080 访问。

4. 项目打包

    - 执行 npm run build 命令，启动打包，完成之后会生成 dist 目录。

5. Mock 开关

    - 通过修改 src/mock/index.js 中的 openMock 变量，可以一键开启或关闭 Mock 功能。

6. 修改配置

    - 如果想自定义端口（默认是8090），可以修改 config/index.js 下的 port 属性。
    - 后台接口和备份服务器地址配置在 src/utils/global.js，如有修改请做相应变更。
<br></br>
### 部署项目至docker（可选择其它方式）

1. 拉取tomcat和mysql两个镜像

    > sudo docker pull tomcat:latest
    > sudo docker pull mysql:latest

    镜像下载完成之后，通过以下命令查看下载的镜像
    > sudo docker images

2. 创建mysql容器（宿主机映射3306端口至容器3306端口，容器别名为mysql，使用mysql:latest镜像）

    > sudo docker run -p 3306:3306 --name mysql -e MYSQL_ROOT_PASSWORD=123456 -d mysql:latest

    查看正在运行的容器
    > sudo docker ps

    进入容器
    > sudo docker exec -it  [容器别名或容器id]  /bin/bash

    使用navicat远程连接mysql容器，并创建penguin数据库，导入sql文件

3. 创建tomcat容器（宿主机映射8080端口至容器8080端口，容器别名为tomcat，使用tomcat:latest镜像，连接容器至mysql容器，db为容器间连接别名，宿主机文件夹/usr/soft/tomcat/webapps映射至容器文件夹/usr/local/tomcat/webapps）

    > sudo docker run --name tomcat --link mysql:db -p 8080:8080 -v /usr/soft/tomcat/webapps:/usr/local/tomcat/webapps -d tomcat:latest 

    测试容器间连接（先要在容器内安装相应包）
    > ping db

    在容器中安装mysql（用于系统备份还原命令）

    测试外部访问 服务器IP:端口号

    访问tomcat首页成功后，tomcat首页的GUI管理需要修改以下文件才能访问，具体看对应页面报错内容
    > /usr/local/tomcat/conf/tomcat-users.xml
    > cat [路径] 直接展示文件内容  sudo gedit [文件] 用文本编辑器打开 
    > sudo docker cp tomcat:/usr/local/tomcat/...  [路径] 复制容器内容到宿主机

    同时，tomcat的默认端口还有很大概率会冲突，所以要修改以下文件避免冲突
    > /usr/local/tomcat/conf/server.xml

    注意，需要修改application.yml文件中jdbc的访问路径，由于是容器与容器间访问，服务器IP修改成容器间连接别名。且用户名和密码要需要mysql容器中存在，IP允许/有权限
    
    在tomcat的Manager App GUI页面中，部署后端maven clean install生成的war文件，利用的是FTP传输，路径是
    > /usr/local/tomcat/webapps/项目名
    
    前端同时也要修改后台接口和备份接口地址，并部署到路径
    > /usr/local/tomcat/webapps/项目名

4. 测试运行

    在tomcat容器中的bin目录存在许多可运行文件，运行以下命令使tomcat进程在前台启动，直接看到输出

    > ./catalina.sh run

    注意，后端要求的依赖，如java版本，mysql-connector版本需要与docker中版本对应。依赖的冲突是很常见的，具体看输出报错

<br/>
<br/>

## 回到顶部
[回到顶部](#readme)
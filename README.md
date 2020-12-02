**项目说明** 
- hmcloud 是基于禅道二次开发的项目管理研发平台

   工程：
        1、generator:代码生成器 帮助生成大部分业务代码
        2、flyer:项目管理工具 基于禅道二次开发
<br> 

   标准微服务工程结构：
       xxx 业务域服务名
       xxx-service-domian: 业务域应用服务
       xxx-service-restapi: 业务域应用服务restful接口
       xxx-client: 微服务sdk包，封装了restapi的调用，提供给具体的客户端调用、如 app、h5web、pcweb
       
       
       xxx-mobile-ui:
       xxx-pc-ui:
       xxx-api-ui:
       xxx-gateway: 服务网关
   项目目录解析
       --config  服务配置
       --controller  api实现包
       --service  服务层
       --dao 数据库层
       

<br> 
   编码规范：
      1、命名规范：
         1.1变量: java规范、驼峰、语义-接近主流开源命名
         1.2方法
      2、编码技巧： 
      https://github.com/alibaba/druid/tree/master/druid-spring-boot-starter
      3、单元测试
      https://blog.csdn.net/liubenlong007/article/details/85398181
      4、统一日志
      5、数据库设计标准：
        1、必须有字段备注，字段备注规范：常量类 要列举出常量
        2、设计好索引 索引字段会自动标记为查询字段
        3、根据场景建立好唯一索引
           

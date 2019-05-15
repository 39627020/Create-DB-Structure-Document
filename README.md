# Create-DB-Structure

#### 项目介绍
自动生成Oracle或者MySql数据库表结构文档

#### 软件架构
Springboot 2.0.3

#### 安装教程
##### 首先安装oracle ojdbc driver
在src/main/lib下有jar包，根据自己的情况替换jar包位置
```
mvn install:install-file -Dfile=D:\git\Create-DB-Structure\structure\src\main\lib\ojdbc6-11.2.0.3.jar -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0.3 -Dpackaging=jar
```
根据实际情况修改application.properties中的设置项
1. database.type 1:Mysql 2:Oracle 3:Postgres
2. database.name 对象数据库名称
3. 修改数据库连接字符串driver_class-name、url、username、password
4. 输出位置output.path
#### 使用说明

1. 运行项目，访问http://127.0.0.1:8080/create, 在当前目录会生成一个数据库名称.xlsx文件。
第一页是索引，后面是每个表的定义。
生成的效果
![输入图片说明](https://images.gitee.com/uploads/images/2018/1010/112933_0955305c_609629.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2018/0921/164337_5e7b539c_609629.png "屏幕截图.png")  
![输入图片说明](https://images.gitee.com/uploads/images/2018/0921/164420_768954b7_609629.png "屏幕截图.png")

1. Fork 本项目
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request

#### TODO List
1. Postgres默认值

#### 码云特技

1. 使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2. 码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3. 你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4. [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5. 码云官方提供的使用手册 [http://git.mydoc.io/](http://git.mydoc.io/)
6. 码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
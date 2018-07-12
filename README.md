# Create-DB-Structure

#### 项目介绍
自动生成Oracle或者MySql数据库表结构文档

#### 软件架构
Springboot 2.0.3

#### 安装教程

1. 根据实际情况修改application.properties中的数据库连接字符串
2. database.type 1:Mysql 2:Oracle
3. database.name 对象数据库名称

#### 使用说明

1. 运行项目，访问http://127.0.0.1:8080/create, 在当前目录会生成一个数据库名称.xlsx文件。
第一页是索引，后面是每个表的定义。
生成的效果
![输入图片说明](https://images.gitee.com/uploads/images/2018/0712/173809_0d1fe137_609629.png "structure1.png")

![输入图片说明](https://images.gitee.com/uploads/images/2018/0712/173828_7e62d762_609629.png "structure2.png")
#### 参与贡献

1. Fork 本项目
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request


#### 码云特技

1. 使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2. 码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3. 你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4. [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5. 码云官方提供的使用手册 [http://git.mydoc.io/](http://git.mydoc.io/)
6. 码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
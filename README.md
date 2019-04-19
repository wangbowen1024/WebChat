# WebChat  
基于WebSocket和Java实现的简易Web多人在线聊天平台

[点击体验](http://webchat.wangbowen.cn) http://webchat.wangbowen.cn 

## 运行环境

* JDK1.8

* TOMCAT9

* MySQL5.7

* Redis

注意先要创建数据库，SQL语句已给出。然后需要手动创建配置文件到resource目录下：

---------------------------------------------
文件名 druid.properties （文件内容如下）

driverClassName=com.mysql.jdbc.Driver

url=jdbc:mysql://localhost:3306/webchat

username=root（自己的数据库用户名）

password=XXXXX(自己的数据库密码)

initialSize=5

maxActive=10

maxWait=3000

---------------------------------------------

打war包后在本地可以直接运行，若要在服务器上运行，记得将代码中的localhost修改为自己服务器公网的IP地址或域名。


## 实现
* 前端 ：html + css + js + jq + bootstrap
* 后端 ：JavaEE
* 通信协议 ：WebSocket


## 浏览器推荐
* Chrome
* Edge






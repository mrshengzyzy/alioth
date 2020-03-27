# alioth

一个微信公众号后台项目，开箱即用。
<http://zyzy.cool>


## 快速开始

1. 重命名 `application.properties.sample` 为 `application.properties` 并更改配置
2. 跟 `Dockerfile` 放到同一目录
3. 执行 `docker build -t alioth:test .` 打包镜像，`Dockerfile` 将从其中配置的仓库重新拉取代码
4. 执行 `docker run -it -d -p 80:8080 alioth` 启动容器


## 如何部署
推荐使用 Dockerfile，或者编译之后直接部署


## 微信公众号

![qrcode](https://github.com/mrshengzyzy/alioth/blob/master/src/main/resources/static/img/qrcode.jpg?raw=true)

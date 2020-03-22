# 联盟机器人(微信公众号)

## 功能说明

### 淘宝客联盟
* 支持商品精确搜索
* 支持商品模糊搜索(返回销量最高的三种商品)

## 更新说明
* 2020-03-18 项目初建
* 2020-03-20 更新淘宝客机器人，添加404页面

## 特殊说明

项目需要淘宝联盟的SDK,请把下载好的SDK放在lib目录下，并修改pom.xml文件中的依赖。

>pom.xml文件示例

``` xml
<dependency>
    <groupId>taobao-sdk-java</groupId> ##随便命名
    <artifactId>taobao-sdk-java</artifactId> ##随便命名
    <version>1.0.0</version> ##随便命名
    <scope>system</scope>
    <systemPath>${project.basedir}/src/main/resources/lib/淘宝联盟的sdk全名</systemPath>
</dependency>
```


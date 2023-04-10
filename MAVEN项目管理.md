# MAVEN项目管理

## 一、依赖范围

### 1.什么是依赖范围

* 依赖范围：依赖的jar包在maven项目生命周期里的作用范围，用`<scope></scope>`标签表示。

### 二、都有哪些依赖范围

| 依赖范围 | 含义                                                         | 例子                                                         |
| -------- | :----------------------------------------------------------- | ------------------------------------------------------------ |
| compile  | 编译、运行、测试、打包都依赖的jar包                          | 开发项目时对spring-core的依赖                                |
| provided | 只在编译和运行时有效，打包时不会包含设定此范围的jar包        | servlet-api容器相关的依赖，运行时的web容器已经自带servlet容器，如果打包进去就会和web容器中的相冲突 |
| runtime  | 只在运行时有效，但是打包时会将对应的jar包包含进来            | jdbc驱动在编译时不需要参与，但是运行时需要**具体的**第三方实现的jar包 |
| test     | 只在测试的时候有效，在编译和运行以及打包时都不会使用         | 测试用的junit依赖                                            |
| system   | 本地jar包，作用范围和provided一致，需要配合systemPath指定本地依赖的路径才能使用 |                                                              |



## 二、父子项目依赖

* 含义：Maven为了合理、有效地管理依赖jar包，建立了一种项目之间的依赖继承关系。

* 作用：父子项目可以合理有效的复用依赖jar包，便于管理各项目的jar包的版本。
* 缺陷：父子项目之间的系统集成性能较差。

### 1. 父项目依赖

1. 打包方式

   ~~~
   <packaging>pom</packaging>
   ~~~

2. 基本依赖

   ~~~
   <dependencies></dependencies>
   ~~~

3. 依赖管理：即依赖容器，**在子项目中使用的时候才会引入**

   ~~~
   <dependencyManagement></dependencyManagement>
   ~~~

### 2. 子项目依赖

​		继承依赖:

~~~
<parent>
	<groupId></groupId>
	<artifactId></artifactId>
	<version></version>
	<relativePath>[父项目路径]/pom.xml</relativePath>
</parent>
~~~

1. 自动继承父项目的groupId
2. 自动引入父项目的基本依赖
3. 使用父项目的依赖容器时，无需版本号`<version></version>`

## 三、聚合项目管理

构建一个父级项目，用模块的方式创建子项目。

 优点：聚合项目之间的整体性较高，便于系统集成和维护
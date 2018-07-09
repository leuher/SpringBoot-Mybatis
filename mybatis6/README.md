# SpringBoot整合Mybatis (六) Generator

流程：

### 1.添加依赖(Druid、Mapper)  
  通用Mapper，用于创建自定义MyMapper接口并继承该接口
```
    <dependencies>
        <!-- 支持JDBC数据库 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>
        <!-- 支持全栈式Web开发，包括Tomcat和spring-webmvc -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!-- 添加Mybatis依赖包 -->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>1.3.2</version>
        </dependency>
        <!--通用mapper-->
        <dependency>
            <groupId>tk.mybatis</groupId>
            <artifactId>mapper-spring-boot-starter</artifactId>
            <version>2.0.2</version>
        </dependency>


        <!-- MySQL的JDBC驱动包，用JDBC连接MySQL数据库时必须使用该jar包 -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
        <!-- Druid依赖 -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <version>1.1.9</version>
        </dependency>
        <!-- 支持常规的测试依赖，包括JUnit、Hamcrest、Mockito以及spring-test模块 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
```

### 2.添加Mybatis-generator依赖  
  指定Mybatis-generator配置文件位置 **src/main/resources/mybatis-generator.xml**
  ```
      <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>

            <plugin>
                <groupId>org.mybatis.generator</groupId>
                <artifactId>mybatis-generator-maven-plugin</artifactId>
                <version>1.3.6</version>
                <dependencies>
                    <!--配置这个依赖主要是为了等下在配置mybatis-generator.xml的时候可以不用配置classPathEntry这样的一个属性，避免代码的耦合度太高-->
                    <dependency>
                        <groupId>mysql</groupId>
                        <artifactId>mysql-connector-java</artifactId>
                        <version>5.1.29</version>
                    </dependency>
                    <dependency>
                        <groupId>tk.mybatis</groupId>
                        <artifactId>mapper</artifactId>
                        <version>4.0.0</version>
                    </dependency>
                </dependencies>
                <executions>
                    <execution>
                        <id>Generate MyBatis Artifacts</id>
                        <phase>package</phase>
                        <goals>
                            <goal>generate</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <!--允许移动生成的文件 -->
                    <verbose>true</verbose>
                    <!-- 是否覆盖 -->
                    <overwrite>true</overwrite>
                    <!-- 自动生成的配置 -->
                    <configurationFile>src/main/resources/mybatis-generator.xml</configurationFile>
                </configuration>
            </plugin>
        </plugins>
    </build>
    ```
  
### 3.创建数据库
```
CREATE TABLE `test_generator` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `author` varchar(20) DEFAULT NULL COMMENT '作者',
  `title` varchar(100) DEFAULT NULL COMMENT '描述',
  `url` varchar(100) DEFAULT NULL COMMENT '链接',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;
```

### 4.修改配置文件:application.yml
```
## 数据库访问配置
spring:
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8
    username: root
    password: root

# 下面为连接池的补充设置，应用到上面所有数据源中
    druid:
      # 初始化大小，最小，最大
      initial-size: 1
      min-idle: 1
      max-active: 20
      # 配置获取连接等待超时的时间
      max-wait: 60000
      # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
      time-between-eviction-runs-millis: 60000
      # 配置一个连接在池中最小生存的时间，单位是毫秒
      min-evictable-idle-time-millis: 300000
      validation-query: SELECT 'x'
      test-while-idle: true
      test-on-borrow: false
      test-on-return: false
      async-init: true
      # 打开PSCache，并且指定每个连接上PSCache的大小，mysql可以设置为false
      pool-prepared-statements: false
      max-pool-prepared-statement-per-connection-size: 20
      # 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
      filters: stat,wall

# Mybatis
mybatis:
  # 指定bean所在包
  type-aliases-package: com.ray.mybatis6.entity
  # 指定映射文件
  mapper-locations: classpath:mapper/*.xml

# Mapper
mapper:
  # 通过 mappers 配置过的接口才能真正调用
  mappers: com.ray.mybatis6.util.MyMapper
  # 是否判断字符串类型 !=''
  not-empty: false
  # 取回主键的方式
  identity: MYSQL
```

### 5.通用Mapper设置  
  这里实现一个自己的接口,继承通用的mapper  
  注意：这个接口不能被扫描到，不能跟dao这个存放mapper文件放在一起。
  ```
  package com.ray.mybatis6.util;

  import tk.mybatis.mapper.common.Mapper;
  import tk.mybatis.mapper.common.MySqlMapper;

  /**
   * @author Ray
   * @date 2018/7/8 0008
   * 自定义MyMapper，用于继承
   */
  public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
    //FIXME 特别注意，该接口不能被扫描到，否则会出错
  }
  ```

### 6.修改SpringBoot启动类  
  MapperScan注解指定扫描的mapper路径  
  注意：应导入 tk.mybatis.spring.annotation.MapperScan ，不然会报错--[错误说明](https://blog.csdn.net/q343509740/article/details/80960180)
  ```
  package com.ray.mybatis6;

  import com.ray.mybatis6.util.MyMapper;
  import org.springframework.boot.SpringApplication;
  import org.springframework.boot.autoconfigure.SpringBootApplication;
  import org.springframework.transaction.annotation.EnableTransactionManagement;
  import tk.mybatis.spring.annotation.MapperScan;

  @SpringBootApplication
  // 开启注解事务管理
  @EnableTransactionManagement  // 等同于xml配置方式的 <tx:annotation-driven />
  @MapperScan(basePackages = "com.ray.mybatis6.dao", markerInterface = MyMapper.class)
  public class Mybatis6Application {

    public static void main(String[] args) {
        SpringApplication.run(Mybatis6Application.class, args);
    }
  }
  ```
  
  ### 7.Mybatis-Generator配置  
    核心代码-Generator配置文件
    ```
    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE generatorConfiguration
        PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
    <!-- 具体配置内容 -->
    <generatorConfiguration>
    <!--加载配置文件，为下面读取数据库信息准备,通过${key}获取值，这里使用yml无法使用properties-->
    <!--<properties resource="application.properties"/>-->

    <!-- context 子元素有严格的配置顺序 -->
    <context id="Mysql" targetRuntime="MyBatis3Simple" defaultModelType="flat">

        <plugin type="tk.mybatis.mapper.generator.MapperPlugin">
            <!--配置后生成的 Mapper 接口都会自动继承该接口-->
            <property name="mappers" value="com.ray.mybatis6.util.MyMapper" />
            <!--caseSensitive默认false，当数据库表名区分大小写时，可以将该属性设置为true-->
            <property name="caseSensitive" value="true"/>
        </plugin>

        <!-- 屏蔽生成自动注释 -->
        <commentGenerator>
            <property name="javaFileEncoding" value="UTF-8"/>
            <property name="suppressDate" value="true"/>
            <property name="suppressAllComments" value="true"/>
        </commentGenerator>

        <!--指定数据库连接信息-->
        <jdbcConnection driverClass="com.mysql.jdbc.Driver"
                        connectionURL="jdbc:mysql://localhost:3306/test"
                        userId="root"
                        password="root">
        </jdbcConnection>

        <!--指定JDBC类型和Java类型如何转换-->
        <javaTypeResolver>
            <property name="forceBigDecimals" value="false"/>
        </javaTypeResolver>

        <!--控制生成的实体类-->
        <javaModelGenerator targetPackage="com.ray.mybatis6.entity" targetProject="src/main/java">
            <property name="enableSubPackages" value="true"/>
            <property name="trimStrings" value="true"/>
        </javaModelGenerator>

        <!--生成映射文件存放位置-->
        <sqlMapGenerator targetPackage="mapper" targetProject="src/main/resources">
            <property name="enableSubPackages" value="true"/>
        </sqlMapGenerator>

        <!--生成Mapper接口-->
        <javaClientGenerator type="XMLMAPPER" targetPackage="com.ray.mybatis6.dao" targetProject="src/main/java">
            <property name="enableSubPackages" value="true"/>
        </javaClientGenerator>

        <!--生成对应表及类名
        去掉Mybatis Generator生成的一堆 example
        -->
        <table tableName="TEST_GENERATOR"
               domainObjectName="TestGenerator"
               enableCountByExample="false"
               enableUpdateByExample="false"
               enableDeleteByExample="false"
               enableSelectByExample="false"
               selectByExampleQueryId="false">
            <generatedKey column="id" sqlStatement="Mysql" identity="true"/>
        </table>
    </context>
    </generatorConfiguration>
    ```
  

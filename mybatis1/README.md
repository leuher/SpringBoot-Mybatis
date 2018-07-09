# SpringBoot整合Mybatis (一) 简单CRUD

### 1.添加依赖  
  JDBC、Web、Mybatis、Mysql、Test
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

        <!-- MySQL的JDBC驱动包，用JDBC连接MySQL数据库时必须使用该jar包 -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
        <!-- 支持常规的测试依赖，包括JUnit、Hamcrest、Mockito以及spring-test模块 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
```

### 2.创建数据库  
  用于简单测试
```
CREATE TABLE user(
	id INT(10) NOT NULL AUTO_INCREMENT COMMENT '编号',
	name VARCHAR(50) NOT NULL COMMENT '用户名',
	PRIMARY KEY(id)
)ENGINE=INNODB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;
```

### 3.修改配置文件   
  此处使用的是yml后缀配置文件，yml配置文件在写的时候层次感强，而且最大限度减少代码量。
  ```
  #配置mysql连接
  spring:
    datasource:
     driver-class-name: com.mysql.jdbc.Driver
     url: jdbc:mysql://localhost:3306/test?userUnicode=true&characterEncoding=UTF-8
     username: root
     password: root

  #配置mybatis
  mybatis:
    #配置xml映射路径
    mapper-locations: classpath:mapper/*.xml
    #配置实体类的别名
    type-aliases-package: com.ray.mybatis.entity
    configuration:
      #开启驼峰命名法
      map-underscore-to-camel-case: true
  ```

### 4.在SpringBoot启动类添加@MapperScan注解  
  或者直接在Mapper类上添加注解@Mapper，建议使用@MapperScan，不然在每个Mapper都添加注解挺麻烦的。
  ```
  @SpringBootApplication
  @MapperScan("com.ray.mybatis.mapper")
  public class MybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }
  }
  ```
  
### 5.在SpringBoot 中使用 RESTful  
  表现在Controller控制层(GET-查、POST-增、PUT-改、DELETE-删)
  ```
      /**
     * 根据id获取用户信息
     * localhost:8080/user/2
     */
    @GetMapping("/user/{id}")
    public User selectById(@PathVariable("id")Integer id){
        User user = userMapper.selectById(id);
        return user;
    }
  ```

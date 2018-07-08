# SpringBoot-Mybatis


### 1. 认识Mybatis
  MyBatis 是一款优秀的持久层框架，它支持定制化 SQL、存储过程以及高级映射。MyBatis 避免了几乎所有的 JDBC 代码和手动设置参数以及获取结果集。MyBatis 可以使用简单的 XML 或注解来配置和映射原生信息，将接口和 Java 的 POJOs(Plain Old Java Objects,普通的 Java对象)映射成数据库中的记录。
 
### 2. 添加依赖
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
        <!-- 分页插件 -->
        <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper-spring-boot-starter</artifactId>
            <version>1.2.5</version>
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
 
### 3. 修改配置文件
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
    
# 后续添加Mapper、PageHelper、Druid
 ```
 
 ### 4. 编写实体类
  略.
 
### 5. 编写数据层和对应的Mapper.xml
  略.

### 6. 编写控制层
  略.
  
---  
# 文档说明
### 1. SpringBoot整合Mybatis (一) 简单CRUD
[SpringBoot整合Mybatis (一) 简单CRUD](https://blog.csdn.net/q343509740/article/details/80948422)
  在Springboot启动类中添加对mapper包扫描@MapperScan或者直接在Mapper类上添加注解@Mapper，建议使用@MapperScan，不然在每个Mapper都添加注解挺麻烦的。
  ```
  @SpringBootApplication
  @MapperScan("com.ray.mybatis.mapper")
  public class MybatisApplication {
 
    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }
  }

  ```

### 2. SpringBoot整合Mybatis (二) 一对一
[SpringBoot整合Mybatis (二) 一对一](https://blog.csdn.net/q343509740/article/details/80949159)
  association - 关联元素处理“有一个”类型的关系。
  ```
      <resultMap id="IDCardOfUserMap" type="iDCard">
        <id property="cardId" column="cardid"/>
        <result property="uid" column="uid"/>
        <!-- 一对一 -->
        <!-- property-映射到列结果的字段或属性 | javaType-Java 类名 -->
        <association property="user" javaType="user">
            <id property="id" column="id"/>
            <result property="name" column="name"/>
        </association>
    </resultMap>
  ```
   
### 3. SpringBoot整合Mybatis (三) 一对多
[SpringBoot整合Mybatis (三) 一对多](https://blog.csdn.net/q343509740/article/details/80949809)
  collection - 集合元素的作用几乎和关联是相同的。
  ```
    <resultMap id="phoneMap" type="user">
        <id property="id" column="id"/>
        <result property="name" column="name"/>
        <!-- 一对多 【ofType是映射到list集合属性中pojo的类型】 -->
        <collection property="phones" ofType="phone">
            <id property="id" column="pid"/>
            <result property="name" column="pname"/>
            <result property="number" column="number"/>
            <result property="uid" column="uid"/>
        </collection>
    </resultMap>
  ```
   
### 4. SpringBoot整合Mybatis (四) 多对多
[SpringBoot整合Mybatis (四) 多对多](https://blog.csdn.net/q343509740/article/details/80952203)
  个人对 [多对多] 的理解：就是多个一对多的组合。
  ```
    <resultMap id="comboMap" type="user" extends="userMap">
        <!-- 一对多 -->
        <collection property="userCombos" ofType="userCombo">
            <result property="price" column="price"/>
            <result property="uid" column="uid"/>
            <result property="cid" column="cid"/>
            <!-- 一对多 -->
            <collection property="combos" ofType="combo">
                <id property="id" column="cid"/>
                <result property="name" column="cname"/>
            </collection>
        </collection>
    </resultMap>
  ```

### 5. SpringBoot整合Mybatis (五) 多对多
[SpringBoot整合Mybatis (五) 多对多](https://blog.csdn.net/q343509740/article/details/80952979)
  个人对 [多对多] 的理解：就是多个一对多的组合。
  ```
      <resultMap id="userMap" type="combo" extends="comboMap">
        <!-- 一对多 【ofType是映射到list集合属性中pojo的类型】 -->
        <collection property="userCombos" ofType="userCombo">
            <result property="price" column="price"/>
            <!-- 一对多 -->
            <collection property="users" ofType="user">
                <id property="id" column="uid"/>
                <result property="name" column="uname"/>
                <!-- 一对多 【ofType是映射到list集合属性中pojo的类型】 -->
                <collection property="phones" ofType="phone">
                    <id property="id" column="pid"/>
                    <result property="name" column="pname"/>
                    <result property="number" column="number"/>
                </collection>
            </collection>
        </collection>
    </resultMap>
  ```

### 6. SpringBoot整合Mybatis (六) Generator
[SpringBoot整合Mybatis (六) Generator](https://blog.csdn.net/q343509740/article/details/80958468)
  MyBatis Generator (MBG) 是一个Mybatis的代码生成器 MyBatis 和 iBATIS. 他可以生成Mybatis各个版本的代码，和iBATIS 2.2.0版本以后的代码。
  配置文件
  ```
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
  ```
  
  Mybatis-Generator配置
  [mybatis-generator.xml](https://github.com/q343509740/SpringBoot-Mybatis/blob/master/mybatis6/src/main/resources/mybatis-generator.xml)

### 7. SpringBoot整合Mybatis (七) Mapper
[SpringBoot整合Mybatis (七) Mapper](https://blog.csdn.net/q343509740/article/details/80961550)
  通用 Mapper 是为了解决 MyBatis 使用中 90% 的基本操作，使用它可以很方便的进行开发，可以节省开发人员大量的时间。
  配置文件
  ```
   # Mapper
   mapper:
    # 通过 mappers 配置过的接口才能真正调用
    mappers: com.ray.mybatis6.util.MyMapper
    # 是否判断字符串类型 !=''
    not-empty: false
    # 取回主键的方式
    identity: MYSQL
  ```
  
  简单调用(继承自定义Mapper接口，且该自定义Mapper接口不能被扫描)
  ```
  public interface CityMapper extends MyMapper<City> {
  }
  ```

### 8. SpringBoot整合Mybatis (八) PageHelper
[SpringBoot整合Mybatis (八) PageHelper](https://blog.csdn.net/q343509740/article/details/80963424)
  如果你也在用 MyBatis，建议尝试该分页插件，这一定是最方便使用的分页插件。分页插件支持任何复杂的单表、多表分页。
  配置文件
  ```
  # PageHelper
  pagehelper:
   # 指定分页插件使用哪种方言
   helper-dialect: mysql
   # 分页合理化参数
   reasonable: true
    # 支持通过 Mapper 接口参数来传递分页参数
    support-methods-arguments: true
  ```
  
  简单调用(动态page)
  ```
  PageHelper.startPage(page,5);
  List<City> cities = cityService.selectAll();
  ```

---
# 相关资料
### 1.Mybatis 3 | 简介
[Mybatis 3 | 简介](http://www.mybatis.org/mybatis-3/zh/index.html)

### 2.Mybatis 通用 Mapper4
[通用 Mapper4](https://gitee.com/free/Mapper/wikis/Home)

### 3.MyBatis 分页插件 PageHelper
[分页插件 PageHelper](https://pagehelper.github.io/)

### 4.MyBatis Generator | 简介
[MyBatis Generator](http://mbg.cndocs.ml/)

---
本文结束  
这是 [本人博客](https://blog.csdn.net/q343509740) 的链接。

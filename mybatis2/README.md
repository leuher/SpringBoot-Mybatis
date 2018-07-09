# SpringBoot整合Mybatis (二) 一对一  

流程：

### 1.创建数据库
```
CREATE TABLE id_card(
	uid INT(10) NOT NULL COMMENT '用户编号',
	cardid BIGINT(18) NOT NULL Comment '身份证'
)ENGINE=INNODB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;
```

### 2.创建实体类和数据操作类
  略.
  
### 3.创建映射xml文件  
  association - 关联元素处理“有一个”类型的关系。比如，在我们的示例中，一个用户有一个身份证。关联映射就工作于这种结果之上。
```
    <!-- 用户表与身份证表（一对一）映射 -->
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

### 4.创建数据控制类
  略.

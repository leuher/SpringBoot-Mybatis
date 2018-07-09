# SpringBoot整合Mybatis (三) 一对多

流程：

### 1.创建数据库
```
CREATE TABLE phone(
	id INT(10) NOT NULL AUTO_INCREMENT COMMENT '编号',
	name VARCHAR(50) NULL COMMENT '手机名称',
	number BIGINT(11) NULL COMMENT '手机号码',
	uid INT(10) NOT NULL COMMENT '用户编号',
	PRIMARY KEY(id)
)ENGINE=INNODB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;
```

### 2.创建实体类和数据操作类
  略.
  
### 3.创建映射xml文件  
  collection - 集合元素的作用几乎和关联是相同的。比如，在我们的示例中，一个用户有一个身份证，但一个身份证同时能拥有多个电话号码。
```
    <!-- 注意：当多个表的字段名一样的时候，查询需要用别名，否则查询结果不理想 -->
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

### 4.创建数据控制类
  略.

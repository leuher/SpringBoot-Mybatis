# SpringBoot整合Mybatis (四) 多对多

流程：

### 1.创建数据库
```
CREATE TABLE user(
	id INT(10) NOT NULL AUTO_INCREMENT COMMENT '编号',
	name VARCHAR(50) NOT NULL COMMENT '用户名',
	PRIMARY KEY(id)
)ENGINE=INNODB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;
```
```
CREATE TABLE combo(
	id INT(10) NOT NULL AUTO_INCREMENT COMMENT '编号',
	name VARCHAR(50) NOT NULL COMMENT '运营商名称',
	PRIMARY KEY(id)
)ENGINE=INNODB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;
```
```
CREATE TABLE user_combo(
	uid INT(10) NULL COMMENT '用户编号',
	cid INT(10) NULL COMMENT '运营商编号',
	price INT(10) NULL COMMENT '价格'
)ENGINE=INNODB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;
```

### 2.创建实体类和数据操作类
  略.
  
### 3.创建映射xml文件  
  collection - 集合元素的作用几乎和关联是相同的。
```
    <!-- 注意：继承userMap -->
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

### 4.创建数据控制类
  略.

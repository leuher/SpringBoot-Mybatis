# SpringBoot整合Mybatis (五) 多对多

流程：

### 1.修改UserCombo实体类(Combo类）  
  添加集合users、combos
  ```
  public class UserCombo {

    private int uid;
    private int cid;
    private int price;

    private List<User> users;
    private List<Combo> combos;
      ……
    }
  ```
  
### 2.创建映射xml文件  
  collection - 集合元素的作用几乎和关联是相同的。
```
    <!-- 注意：继承comboMap -->
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

### 4.创建数据控制类
  略.

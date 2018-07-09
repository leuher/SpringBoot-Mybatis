# SpringBoot整合Mybatis (七) Mapper

流程：

### 1.创建数据库
```
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='市级信息';

-- 城市信息
INSERT INTO `city` VALUES ('1', '石家庄', '河北');
INSERT INTO `city` VALUES ('2', '北京', '北京');
INSERT INTO `city` VALUES ('3', '珠海', '广东');
INSERT INTO `city` VALUES ('4', '恩施', '湖北');
```

### 2.修改mybatis-generator.xml配置文件  
  生成对应entity、dao、mapper
```
       <!--生成对应表及类名
        去掉Mybatis Generator生成的一堆 example
        -->
        <table tableName="CITY"
               domainObjectName="City"
               enableCountByExample="false"
               enableUpdateByExample="false"
               enableDeleteByExample="false"
               enableSelectByExample="false"
               selectByExampleQueryId="false">
            <generatedKey column="id" sqlStatement="Mysql" identity="true"/>
        </table>
```
  
### 3.查看CityMapper
  已继承自定义的MyMapper，包含各种方法
```
package com.ray.mybatis6.dao;

import com.ray.mybatis6.entity.City;
import com.ray.mybatis6.util.MyMapper;

public interface CityMapper extends MyMapper<City> {
}
```

### 4.定义通用Service接口  
  一般操作数据库都在Service中进行，不可避免的就要写出大量重复的CRUD方法，如果能有一个通用的Service，肯定也会减少很多工作量。
  ```
  /**
 * @author Ray
 * @date 2018/7/8 0008
 * 通用service接口
 */
  @Service
  public interface IService<T> {

    T selectByKey(Object key);

    List<T> selectAll();

    int save(T entity);

    int delete(Object key);

    int update(T entity);

    //TODO 其他...
  }
  ```
  
### 5.数据管理CityService  
  实现通用Service接口，调用通用Mapper方法。
  ```
  /**
 * @author Ray
 * @date 2018/7/8 0008
 * 实现service
 */
@Service
public class CityService implements IService<City> {

    @Autowired
    private CityMapper cityMapper;

    @Override
    public City selectByKey(Object key) {
        return cityMapper.selectByPrimaryKey(key);
    }

    @Override
    public List<City> selectAll() {
        return cityMapper.selectAll();
    }

    @Override
    public int save(City entity) {
        return cityMapper.insert(entity);
    }

    @Override
    public int delete(Object key) {
        return cityMapper.deleteByPrimaryKey(key);
    }

    @Override
    public int update(City entity) {
        if(entity.getId() != null){
            return cityMapper.updateByPrimaryKey(entity);
        }
        return 0;
    }

}
  ```

### 6.数据控制层CityController  
  略.

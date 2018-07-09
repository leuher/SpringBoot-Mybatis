# SpringBoot整合Mybatis (八) PageHelper

流程：

### 1.新增数据库数据
```
-- 城市信息
INSERT INTO `city` VALUES ('1', '石家庄', '河北');
INSERT INTO `city` VALUES ('2', '北京', '北京');
INSERT INTO `city` VALUES ('3', '珠海', '广东');
INSERT INTO `city` VALUES ('4', '恩施', '湖北');
INSERT INTO `city` VALUES ('5', '丰顺', '广东');
INSERT INTO `city` VALUES ('6', '广州', '广东');
INSERT INTO `city` VALUES ('7', '佛山', '广东');
INSERT INTO `city` VALUES ('8', '江门', '广东');
INSERT INTO `city` VALUES ('9', '肇庆', '广东');
INSERT INTO `city` VALUES ('10', '惠州', '广东');
INSERT INTO `city` VALUES ('11', '汕头', '广东');
INSERT INTO `city` VALUES ('12', '潮州', '广东');
INSERT INTO `city` VALUES ('13', '石家庄', '河北');
INSERT INTO `city` VALUES ('14', '北京', '北京');
INSERT INTO `city` VALUES ('15', '珠海', '广东');
INSERT INTO `city` VALUES ('16', '恩施', '湖北');
```

### 2.添加依赖  
```
        <!-- 分页插件 -->
        <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper-spring-boot-starter</artifactId>
            <version>1.2.5</version>
        </dependency>
```
  
### 3.修改配置文件application.yml
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

# PageHelper
pagehelper:
  # 指定分页插件使用哪种方言
  helper-dialect: mysql
  # 分页合理化参数
  reasonable: true
  # 支持通过 Mapper 接口参数来传递分页参数
  support-methods-arguments: true
```

### 4.PageHelperTest测试类  
  分页信息简单说明，注意分页核心代码(固定值)，必须在查询语句前。
  ```
  /**
 * @author Ray
 * @date 2018/7/8 0008
 * 分页信息说明
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PageHelperTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PageHelperTest.class);

    @Autowired
    private CityMapper cityMapper;

    @Test
    public void test(){
        PageHelper.startPage(1,5); // 分页核心代码 (页，行)
        PageInfo<City> cityPageInfo = new PageInfo<>(this.cityMapper.selectAll());
        LOGGER.info("[分页信息] - [{}]", cityPageInfo);
        LOGGER.info("[当前页号-PageNum] - [{}]", cityPageInfo.getPageNum());
        LOGGER.info("[页面大小-PageSize] - [{}]", cityPageInfo.getPageSize());
        LOGGER.info("[起始行号-StartRow] - [{}]", cityPageInfo.getStartRow());
        LOGGER.info("[终止行号-EndRow] - [{}]", cityPageInfo.getEndRow());
        LOGGER.info("[总结果数-Total] - [{}]", cityPageInfo.getTotal());
        LOGGER.info("[总页数-Pages] - [{}]", cityPageInfo.getPages());
        LOGGER.info("[分页列表-List] - [{}]", cityPageInfo.getList());
    }
}

  ```

### 5.数据控制类CityController  
  添加如下代码，动态传入page值，推荐每页10条数据(此处数据量少，改成5条数据)
  ```
      /**
     * 查询所有城市信息[分页查询]
     * 动态改变页数，固定每页行数
     * localhost:8080/cities/3
     */
    @GetMapping("/cities/{page}")
    public List<City> selectPage(@PathVariable("page") int page){
        PageHelper.startPage(page,5);
        List<City> cities = cityService.selectAll();
        return cities;
    }
  ```

# jpa-basic-spring-boot-starter
[中文](./ZH_CN.md) | [English](./README.md)
* Jpa Quick Design
### use
1. Add dependency
     ```xml
        <dependency>
            <groupId>com.github.uinios</groupId>
            <artifactId>jpa-basic-spring-boot-starter</artifactId>
            <version>1.5.1</version>
        </dependency>
      ```
----------   
2. examples
* Entity class
```java
         @Entity
         @Getter
         @Setter
         @DynamicInsert
         @DynamicUpdate
         @Table(name = "sys_user")
         @JsonIgnoreProperties(ignoreUnknown = true, value = {"handler", "hibernateLazyInitializer"})
         public class User implements Serializable {
         
             @Id
             @GenericGenerator(name = "uuid2", strategy = "uuid2")
             @GeneratedValue(generator = "uuid2")
             @Column(columnDefinition = "char(36)")
             private String userId;
             private String loginName;
             private String password;
       }
       
```
---------
> Repository
```java
//JpaSpecificationExecutor Provide complex queries
public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
    
}
```
--------
> service
  * Provide single table CURD operation
```java
public interface UserService extends BaseService<User, String> {
}
```
--------
```java
@Service
@Slf4j
public class UserServiceImpl extends BaseServiceImpl<User, String> implements UserService {
    
}
```
-------
> Test
```java
@SpringBootTest
class MainApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
       User user = new User();
       user.setLoginName("test");
       user.setpPassword("test");

       userService.save(user);

       userService.page(1, 2,user);

       userService.findById("test");
       //...
    }
}
```


# swagger2-springmvc-example

大家都知道springmvc是JaveWeb里最流行的mvc框架，swagger也是最流行的文档生成工具，两个最流行的结合起来也只需要仅仅几分钟而已

#**目录结构**
![目录结构](http://img.blog.csdn.net/20170225104304740?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXFfMzIxOTgyNzc=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

#**POM**

```
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.jk</groupId>
    <artifactId>swagger2-springmvc-example</artifactId>
    <packaging>war</packaging>
    <version>1.0</version>
    <name>SpringMVC Swagger Webapp</name>
    <url>http://maven.apache.org</url>

    <properties>
        <springfox-version>2.3.0</springfox-version>
        <spring-version>4.2.4.RELEASE</spring-version>
        <servlet-api-version>3.1.0</servlet-api-version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
        </dependency>
        <!-- Jackson JSON Processor -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.6.4</version>
        </dependency>
        <dependency>
            <groupId>org.codehaus.jackson</groupId>
            <artifactId>jackson-mapper-asl</artifactId>
            <version>1.9.13</version>
        </dependency>
        <!--Spring dependencies -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>${spring-version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context-support</artifactId>
            <version>${spring-version}</version>
        </dependency>
        <!--SpringFox dependencies-->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>${springfox-version}</version>
        </dependency>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>${servlet-api-version}</version>
            <scope>provided</scope>
        </dependency>
    </dependencies>

</project>
```

#**web.xml**

```
<web-app id="WebApp_ID" version="2.4"
         xmlns="http://java.sun.com/xml/ns/j2ee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://java.sun.com/xml/ns/j2ee
	http://java.sun.com/xml/ns/j2ee/web-app_2_4.xsd">

    <display-name>Spring Web MVC Application</display-name>

    <!-- springmvc -->
    <servlet>
        <servlet-name>mvc-dispatcher</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>mvc-dispatcher</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>/WEB-INF/mvc-dispatcher-servlet.xml</param-value>
    </context-param>

    <!-- 字符过滤器 -->
    <filter>
        <filter-name>encodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
        <init-param>
            <param-name>forceEncoding</param-name>
            <param-value>true</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>encodingFilter</filter-name>
        <url-pattern>/v2/api/*</url-pattern>
    </filter-mapping>

    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>

</web-app>
```
#**mvc-dispatcher-servlet.xml**
这个文件要自己在WEB-INF文件夹下新建

```
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="
        http://www.springframework.org/schema/beans     
        http://www.springframework.org/schema/beans/spring-beans-4.1.xsd
        http://www.springframework.org/schema/context 
        http://www.springframework.org/schema/context/spring-context-4.1.xsd
        http://www.springframework.org/schema/mvc
        http://www.springframework.org/schema/mvc/spring-mvc-4.1.xsd">
    <!--扫描包-->
    <context:component-scan base-package="com.jk"/>
    <!--注解驱动-->
    <mvc:annotation-driven/>
    <!--地址转发-->
    <mvc:resources mapping="/swagger-ui/**" location="WEB-INF/swagger-ui-2.1.3/"/>
    <mvc:resources mapping="index.html" location="WEB-INF/swagger-ui-2.1.3/"/>
    <mvc:redirect-view-controller path="/" redirect-url="index.html"/>
</beans>
```

#**index.html**
swagger-ui-2.1.3这个文件夹是从https://github.com/swagger-api/swagger-ui项目中的dist文件夹拷贝过来的，想要最新版可以自己拷贝，还要改之前mvc-dispatcher-servlet.xml中地址转发的文件名
index.html这个文件在swagger-ui-2.1.3文件夹下，只需要改默认的url，该项目在35行左右，改为`地址：端口/项目名/v2/api-docs`
示例

```
//          这里填项目地址最后以v2/api-docs结尾
//          因为我是用idea跑的，所以8080/不用加项目名，真正部署是需要加的
        url = "http://localhost:8080/v2/api-docs";
```

#**ApplicationSwaggerConfig**
这个是swagger2的配置文件

```
/**
 * Swagger描述
 * 下面两个注解可以让其被spring扫描到
 */
@Configuration
@EnableSwagger2
public class ApplicationSwaggerConfig {

    @Bean
    public Docket configSpringfoxDocketForAll() {
        return new Docket(DocumentationType.SWAGGER_2)
                .produces(Sets.newHashSet("application/json", "application/xml"))
                .consumes(Sets.newHashSet("application/json", "application/xml"))
                .protocols(Sets.newHashSet("http"/*, "https"*/))
                .forCodeGeneration(true)
                .select().paths(regex(".*"))
                .build()
                .apiInfo(apiInfo());
    }

    //页面头部api信息配置
    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "SpringMVC REST API文档",
                "使用Swagger UI构建SpringMVC REST风格的可视化文档",
                "1.0.0",
                "http://localhost:8080/v2/api-docs",
                "jkgeekjack@163.com",
                "Apache License",
                "http://www.apache.org/licenses/LICENSE-2.0.html"
        );
        return apiInfo;
    }

}
```

#**TestController**
这个controller用来测试，只展示最简单的用法，详细请看swagger文档

```
@Api(value = "测试API", description = "测试API")
@RestController
@RequestMapping("/test")
public class TestController {
    @RequestMapping(value = "/hello")
    public String hello(String name) {
        return "hello "+name;
    }

}
```

#**效果图**
![这里写图片描述](http://img.blog.csdn.net/20170225105945139?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXFfMzIxOTgyNzc=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)


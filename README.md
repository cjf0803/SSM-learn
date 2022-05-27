# 一、什么是SSM框架？

SSM框架是spring、spring MVC 、和mybatis框架的整合，是标准的MVC模式。标准的SSM框架有四层，分别是dao层（mapper），service层，controller层和View层。使用spring实现业务对象管理，使用spring MVC负责请求的转发和视图管理，mybatis作为数据对象的持久化引擎。

#### 1）持久层：dao层（mapper）层

作用：主要是做数据持久层的工作，负责与数据库进行联络的一些任务都封装在此。

Dao层首先设计的是接口，然后再Spring的配置文件中定义接口的实现类。
然后可以在模块中进行接口的调用来进行数据业务的处理。（不在关心接口的实现类是哪个类）
数据源的配置以及有关数据库连接的参数都在Spring的配置文件中进行配置。

#### 2）业务层：Service层

作用：Service层主要负责业务模块的逻辑应用设计。

先设计接口然后再设计实类，然后再在Spring的配置文件中配置其实现的关联。（业务逻辑层的实现具体要调用到自己已经定义好的Dao的接口上）这样就可以在应用中调用Service接口来进行业务处理。
建立好Dao之后再建立service层，service层又要在controller层之下，因为既要调用Dao层的接口又要提供接口给controller层。每个模型都有一个service接口，每个接口分别封装各自的业务处理的方法。

#### 3）表现层：Controller层（Handler层）

作用：负责具体的业务模块流程的控制。

配置也同样是在Spring的配置文件里面进行，
调用Service层提供的接口来控制业务流程。
业务流程的不同会有不同的控制器，在具体的开发中可以将我们的流程进行抽象的归纳，设计出可以重复利用的子单元流程模块。

#### 4）View层

作用：主要和控制层紧密结合，主要负责前台jsp页面的表示。


##### 各层之间的联系

DAO层，Service层这两个层次都可以单独开发，互相的耦合度很低，完全可以独立进行，这样的一种模式在开发大项目的过程中尤其有优势，Controller，View层因为耦合度比较高，因而要结合在一起开发，但是也可以看作一个整体独立于前两个层进行开发。这样，在层与层之前我们只需要知道接口的定义，调用接口即可完成所需要的逻辑单元应用，一切显得非常清晰简单。

### 1.Spring

Spring里面的IOC容器和AOP是我们平时使用最多的。

##### 1）IOC（控制反转）

它可以装载bean，也是一种降低对象之间耦合关系的设计思想。（比如租房子。以前租房子需要一个房子一个房子找，费时费力，然后现在加入一个房屋中介，把你需要的房型告诉中介，就可以直接选到需要的房子，中介就相当于spring容器。）

##### 2）AOP（面向切面）

是面向对象开发的一种补充，它允许开发人员在不改变原来模型的基础上动态的修改模型以满足新的需求，如：动态的增加日志、安全或异常处理等。AOP使业务逻辑各部分间的耦合度降低，提高程序可重用性，提高开发效率。

1.横切关注点：从每个方法中抽取出来的同一类非核心业务代码。
2.切面：封装横切信息点的类，每个关注点体现为一个通知方法。
3.通知：切面必须要完成的各个具体工作，也就是切面里的一个个方法。
4.目标：被通知的对象，也就是被通知方法所作用的对象。
5.代理：像目标对象应用通知之后所创建的代理对象。
6.连接点：横切关注点在程序代码中的具体体现，对应用程序执行的某个特定位置。（通俗来讲就是一个个的方法）
7.切入点：切入点就是定位连接点的方式。每个通知上的切入点表达式找到对应的连接点，执行通知之后连接点也就变成了切入点。

![](https://github.com/cjf0803/SSM-learn/raw/main/images/01.png)

### 2.Spring MVC

<复杂版>
1、 用户发送请求至前端控制器DispatcherServlet。
2、 DispatcherServlet收到请求调用HandlerMapping处理器映射器。
3、 处理器映射器找到具体的处理器(可以根据xml配置、注解进行查找)，生成处理器对象及处理器拦截器(如果有则生成)一并返回给DispatcherServlet。
4、 DispatcherServlet调用HandlerAdapter处理器适配器。
5、 HandlerAdapter经过适配调用具体的处理器(Controller，也叫后端控制器)。
6、 Controller执行完成返回ModelAndView。
7、 HandlerAdapter将controller执行结果ModelAndView返回给DispatcherServlet。
8、 DispatcherServlet将ModelAndView传给ViewReslover视图解析器。
9、 ViewReslover解析后返回具体View。
10、DispatcherServlet根据View进行渲染视图（即将模型数据填充至视图中）。
11、 DispatcherServlet响应用户。
![](https://github.com/cjf0803/SSM-learn/raw/main/images/02.png)

#### <简单版>

1.客户端发送请求到DispacherServlet（分发器）
2.由DispacherServlet控制器查询HanderMapping，找到处理请求的Controller
3.Controller调用业务逻辑处理后，返回ModelAndView
4.DispacherSerclet查询视图解析器，找到ModelAndView指定的视图
5.视图负责将结果显示到客户端

![](https://github.com/cjf0803/SSM-learn/raw/main/images/03.png)

### 3.Mybatis （核心是SqlSession）

mybatis是对jdbc的封装，它让数据库底层操作变的透明。mybatis的操作都是围绕一个sqlSessionFactory实例展开的。mybatis通过配置文件关联到各实体类的Mapper文件，Mapper文件中配置了每个类对数据库所需进行的sql语句映射。在每次与数据库交互时，通过sqlSessionFactory拿到一个sqlSession，再执行sql命令。

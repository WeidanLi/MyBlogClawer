## 一、介绍
萌生出来的写爬虫的心里，是有一次我在写Springjpa的小Demo的时候，苦于没有数据，想了想博客这种数据类型能够符合我的需求，所以想要通过爬虫把我博客里面的数据爬出来（其实可以通过数据库，但是自我感觉不优雅）。
找了找以前自己笔记中比较出名的Java爬虫框架，排在第一位的就是这个框架了`Gecco`，据我以前了解到这是一款只要定义好`vo`类配以`jq`选择器风格的注解就可以拿到我们自己想要的逻辑，一时兴起拿起键盘就开始看了。

官方介绍: Gecco是一款用java语言开发的轻量化的易用的网络爬虫。Gecco整合了jsoup、httpclient、fastjson、spring、htmlunit、redission等优秀框架，让您只需要配置一些jquery风格的选择器就能很快的写出一个爬虫。Gecco框架有优秀的可扩展性，框架基于开闭原则进行设计，对修改关闭、对扩展开放。

项目主页: https://github.com/xtuhcy/gecco/blob/master/README_CN.md

## 二、关注点

据官方文档介绍，`Gecco`有6个引擎，但是其实我们关注点并不需要那么多，据我开发过程中来看，其实我们的关注点大致有以下三点: 

1. `SpiderBean` 即我介绍里面所说的`vo`，配以简单的注解即可在`Pipeline`中获取到了数据。
2. `Pipeline` 通过实现`Pipeline<vo>`接口，实现接口中的方法拿到数据，我们需要的进一步逻辑也在这一步完成。
3. `Downloader` 顾名思义就是下载了，当我们需要下载我们需要的数据（图片、视频）的时候，就需要用到这个引擎

## 三、开发
### 下载Demo包
1.通过Git下载作者配置的Demo: https://gitee.com/xiaomaoguai/gecco-demo/tree/master/gecco-java-demo

### 修改开发习惯所需要的配置
2.我需要用到一些`jar`包，所以直接在`pom.xml`文件中增加: 
```xml
<!-- 通用Mapper -->
<dependency>
    <groupId>tk.mybatis</groupId>
    <artifactId>mapper</artifactId>
    <version>3.3.0</version>
</dependency>

<!-- orika映射框架 -->
<dependency>
    <groupId>ma.glasnost.orika</groupId>
    <artifactId>orika-core</artifactId>
    <version>1.4.5</version>
</dependency>

<!-- Jackson Json处理工具包 -->
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.8.9</version>
</dependency>
```

3.修改`Spring`配置文件`applicationContext.xml`，去除`MyBatis`配置Dao扫描，新增`tkmybatis`配置
```xml
<!-- MyBatis 动态实现 -->
<bean id="mapperScannerConfigurer" class="tk.mybatis.spring.mapper.MapperScannerConfigurer">
    <!-- 对Dao 接口动态实现，需要知道接口在哪 -->
    <property name="basePackage" value="cn.liweidan.crawer.mapper" />
</bean>
```

### 开发Gecco类
4.开始开发`Gecco`所需要的`SpiderBean`以及`Pipeline`
主要以我现在这个博客为主，当然如果看到这个教程的时候我的主题已经换掉了，那可能就不适用了。

1）首先对项目进行分包
```
MyBlogClawer
     |------Main 开发代码
     |       |----java  java代码
     |             |----cn
     |                  |----liweidan
     |                           |----crawer
     |                                  |----mapper MyBatis接口
     |                                  |----pipeline pipeline类
     |                                  |----po 数据库PO类
     |                                  |----service 业务层
     |                                  |----utils 工具类
     |                                  |----vo 视图类 即SpiderBean类
     |                                  |----CrawlerMain.java 爬虫启动类
     |       |------resource 配置文件
     |------Test 用于存放测试类的包

```

2）开发主页vo
首先，我们最先进入的就是主页了，也就是`http://c.liweidan.cn`，所以开始我定义了`IndexVo`类，用来获取右边栏所有的分类: 
- 这个类需要实现`HtmlBean`来作为标志
- 类级别加上注解`@Gecco`指定匹配的`url`以及`pipelines`
- 定义这个页面上元素，比如主页上的分类
```java
@Gecco(matchUrl = "http://c.liweidan.cn/", // 匹配地址
        pipelines = {"consolePipeline", "indexPipeline"}) // 这个类特有的Pipeline
public class IndexVo implements HtmlBean {// 需要实现HtmlBean接口，不需要实现方法

    @HtmlField(cssPath = "#categories-4 > ul > li > a")// 通过配置@HtmlField指定jq选择器来获取
    private List<CategoryVo> categoryVoList;

    /** Setter & Getter... */
}
```

- 获取`Selector`方式
1. 定义分类存储的时候需要获取`selector`，这个时候我们可以使用Chrome自带的开发者工具来很方便的获取这个值，如图:
![](http://oy1sy8x27.bkt.clouddn.com/java/crawer/cate-selector.png)
2. 拷贝出来的`Selector`: `#categories-4 > ul > li.cat-item.cat-item-22 > a`
3. 当然拷贝出来不是立即可以使用，要通过观察，是否含有其他分类中没有的类型或者，在这里`li`部分后面的分类是可以不要的。
所以我精简为`#categories-4 > ul > li > a`，注意这里要精确到`a`标签部分，因为取出来才是复数的

2）开发分类vo
因为在主页的`vo`里，我们使用到了`CategoryVo`，所以我们需要开发这个`vo`类。因为我所需要的数据不多，只要拿到`title`以及`url`就够了。所以我只是定义了这两个属性。
```java
public class CategoryVo implements HtmlBean {

    @Text
    @HtmlField(cssPath = "a")
    private String title;

    @Href(click = true)
    @HtmlField(cssPath = "a")
    private String url;

    /** Getter & Setter... */
}
```
在这个类里面: 
- 不需要`@Gecco`注解，因为这不是一个页面。
- 两个属性都是`a`标签的内容，所以包含了`text`和`href`两个注解。跟我们平时前端开发习惯是很吻合的。
- `@Href(click = true)`默认`click`是等于`false`，如果我们想要获取里面的信息，就要点击进去看，故设置为`true`（这一步很重要）

3）开发分类文章页
因为我们在上面的`url`设置了点击，所以我们又需要一个类来获取分类文章页的信息（即点击`URL`以后弹出来的页面）。
这一步跟第一步的`IndexVo`类似
```java
@Gecco(matchUrl = "http://c.liweidan.cn/category/{firstCate}/{secondCate}/", pipelines = {"consolePipeline"})
public class CategoryVoPage implements HtmlBean {

    @HtmlField(cssPath = ".post > div > h2 > a")
    private List<BlogLessVo> blogLessVoList;// 代表该页面上，每一块文章预览模块的信息

    /** Setter & Getter... */
}
```
这一步的`matchUrl`是通过`{}`占位符来动态的模拟请求数据，当我们需要获取里面的值的时候，只需要定义属性名一样的比如`firstCate`加上`@RequestParam`即可获取。

`BlogLessVo`类依然跟`CategoryVo`类一致，只包含了`title`和`url`，`url`点击进去就是文章页面了，所以设置`click=true`

```java
public class BlogLessVo implements HtmlBean {

    @Href(click = true)
    @HtmlField(cssPath = "a")
    private String url;

    @Text
    @HtmlField(cssPath = "a")
    private String shortName;
    
    /** Setter & Getter... */
}
```

来到这里我们知道，下一步就是博文的详情页了。

所以我们需要一个`BlogDetailVo`类。

```java
@Gecco(matchUrl = "http://c.liweidan.cn/{y}/{m}/{d}/{shortName}/", pipelines = {"blogDetailPipeline"})
public class BlogDetailVo implements HtmlBean {

    @HtmlField(cssPath = ".post > div > h2")
    private String title;

    @HtmlField(cssPath = ".post > div > div")
    private String content;

    @Text
    @HtmlField(cssPath = ".post > div > div.clearfix.entry-meta > ul > li:nth-child(3) > span > a")
    private String cateName;
    
    /** Setter & Getter... */
}
```

`matchUrl`可以看出来是通过年月日以及文章名字来显示的，所以我们只需要定义几个占位符即可匹配到URL

3）开发`Pipeline`类

因为我只需要两个分类和博文两个数据，所以我在可以获取这些数据的地方，通过`@Gecco`注解设置了`pipelines`属性

可以看到在上面的`vo`中，我只在`IndexVo`以及最后的`BlogDetailVo`中定义了`Pipeline`。所以现在来开发这两个`Pipeline`类：

IndexPipeline：
```java
@Service
public class IndexPipeline implements Pipeline<IndexVo> {

    @Autowired
    private BlogService blogService;

    @Override
    public void process(IndexVo indexVo) {
        List<CategoryInfo> categoryInfos =
                BeanMapper.mapList(indexVo.getCategoryVoList(),
                        BeanMapper.getType(CategoryVo.class), BeanMapper.getType(CategoryInfo.class));
        blogService.setCategoryInfoList(categoryInfos);
    }

}
```

BlogDetailPipeline：
```java
@Service
public class BlogDetailPipeline implements Pipeline<BlogDetailVo> {

    @Autowired
    private BlogService blogService;

    @Override
    public void process(BlogDetailVo blogDetailVo) {
        blogService.saveBlog(blogDetailVo);
    }
}
```

开发过程中我们只需要通过实现`Pipeline<VO>`接口，传入`vo`泛型属性，实现`process`方法就可以完成我们自己的逻辑。传递的参数就是页面获取的参数。

这里做了两件事情
- `VO`转`PO`，当然偷懒的话可以不用转
- 业务层完成插入的逻辑。

## 四、总结
所以其实我们只要定义一个初始页面作为一个入口，然后在页面相对应的url中如果需要点击查看，设置`@Href(click = true)`就可以继续进行页面爬取。

所有操作中的URL，框架会通过`matchUrl`自动匹配我们所定义的VO类，调用Pipeline进行业务逻辑。

开发也比较简单，基本就是:页面VO匹配URL -> 页面元素VO -> 继续页面VO匹配 -> ... 这个过程无限循环。

GitHub: https://github.com/WeidanLi/MyBlogClawer
MyBlog: http://c.liweidan.cn/
公众号: 
![](http://oy1sy8x27.bkt.clouddn.com/common/qrcode_for_gh_e19175bf4802_258.jpg)











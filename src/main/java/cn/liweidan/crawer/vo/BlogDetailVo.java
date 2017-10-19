package cn.liweidan.crawer.vo;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.HtmlBean;

/**
 * <p>Desciption:</p>
 * CreateTime : 2017/10/18 下午5:52
 * Author : Weidan
 * Version : V1.0
 */
@Gecco(matchUrl = "http://c.liweidan.cn/{y}/{m}/{d}/{shortName}/", pipelines = {"blogDetailPipeline"})
public class BlogDetailVo implements HtmlBean {

    @HtmlField(cssPath = ".post > div > h2")
    private String title;

    @HtmlField(cssPath = ".post > div > div")
    private String content;

    @Text
    @HtmlField(cssPath = ".post > div > div.clearfix.entry-meta > ul > li:nth-child(3) > span > a")
    private String cateName;

    @Override
    public String toString() {
        return "BlogDetailVo{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", cateName='" + cateName + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }
}

package cn.liweidan.crawer.vo;

import com.geccocrawler.gecco.annotation.Href;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.HtmlBean;

/**
 * <p>Desciption:</p>
 * CreateTime : 2017/10/18 下午5:47
 * Author : Weidan
 * Version : V1.0
 */
public class BlogLessVo implements HtmlBean {

    @Href(click = true)
    @HtmlField(cssPath = "a")
    private String url;

    @Text
    @HtmlField(cssPath = "a")
    private String shortName;

    @Override
    public String toString() {
        return "BlogLessVo{" +
                "url='" + url + '\'' +
                ", shortName='" + shortName + '\'' +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}

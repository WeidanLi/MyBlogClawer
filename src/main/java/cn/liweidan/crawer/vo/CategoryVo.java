package cn.liweidan.crawer.vo;

import com.geccocrawler.gecco.annotation.Href;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.HtmlBean;

/**
 * <p>Desciption:</p>
 * CreateTime : 2017/10/18 下午5:24
 * Author : Weidan
 * Version : V1.0
 */
public class CategoryVo implements HtmlBean {

    @Text
    @HtmlField(cssPath = "a")
    private String title;

    @Href(click = true)
    @HtmlField(cssPath = "a")
    private String url;

    @Override
    public String toString() {
        return "CategoryVo{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

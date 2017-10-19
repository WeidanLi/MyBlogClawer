package cn.liweidan.crawer.vo;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.spider.HtmlBean;

import java.util.List;

/**
 * <p>Desciption:</p>
 * CreateTime : 2017/10/18 下午5:40
 * Author : Weidan
 * Version : V1.0
 */
@Gecco(matchUrl = "http://c.liweidan.cn/category/{firstCate}/{secondCate}/", pipelines = {"consolePipeline"})
public class CategoryVoPage implements HtmlBean {

    @HtmlField(cssPath = ".post > div > h2 > a")
    private List<BlogLessVo> blogLessVoList;

    public List<BlogLessVo> getBlogLessVoList() {
        return blogLessVoList;
    }

    public void setBlogLessVoList(List<BlogLessVo> blogLessVoList) {
        this.blogLessVoList = blogLessVoList;
    }
}

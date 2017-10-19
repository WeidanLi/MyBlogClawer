package cn.liweidan.crawer.vo;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.spider.HtmlBean;

import java.util.List;

/**
 * <p>Desciption:</p>
 * CreateTime : 2017/10/18 下午5:22
 * Author : Weidan
 * Version : V1.0
 */
@Gecco(matchUrl = "http://c.liweidan.cn/", // 匹配地址
        pipelines = {"consolePipeline", "indexPipeline"}) // 这个类特有的Pipeline
public class IndexVo implements HtmlBean {// 需要实现HtmlBean接口，不需要实现方法

    @HtmlField(cssPath = "#categories-4 > ul > li > a")
    private List<CategoryVo> categoryVoList;

    public List<CategoryVo> getCategoryVoList() {
        return categoryVoList;
    }

    public void setCategoryVoList(List<CategoryVo> categoryVoList) {
        this.categoryVoList = categoryVoList;
    }
}

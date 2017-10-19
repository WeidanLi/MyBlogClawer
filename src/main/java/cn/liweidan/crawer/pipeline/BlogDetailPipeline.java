package cn.liweidan.crawer.pipeline;

import cn.liweidan.crawer.service.BlogService;
import cn.liweidan.crawer.vo.BlogDetailVo;
import com.geccocrawler.gecco.pipeline.Pipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>Desciption:</p>
 * CreateTime : 2017/10/18 下午5:55
 * Author : Weidan
 * Version : V1.0
 */
@Service
public class BlogDetailPipeline implements Pipeline<BlogDetailVo> {

    @Autowired
    private BlogService blogService;

    @Override
    public void process(BlogDetailVo blogDetailVo) {
        blogService.saveBlog(blogDetailVo);
    }
}

package cn.liweidan.crawer.pipeline;

import cn.liweidan.crawer.po.CategoryInfo;
import cn.liweidan.crawer.service.BlogService;
import cn.liweidan.crawer.utils.BeanMapper;
import cn.liweidan.crawer.vo.CategoryVo;
import cn.liweidan.crawer.vo.IndexVo;
import com.geccocrawler.gecco.pipeline.Pipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Desciption:</p>
 * CreateTime : 2017/10/18 下午5:23
 * Author : Weidan
 * Version : V1.0
 */
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

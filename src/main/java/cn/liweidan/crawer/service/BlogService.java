package cn.liweidan.crawer.service;

import cn.liweidan.crawer.mapper.BlogMapper;
import cn.liweidan.crawer.mapper.CategoryMapper;
import cn.liweidan.crawer.po.BlogInfo;
import cn.liweidan.crawer.po.CategoryInfo;
import cn.liweidan.crawer.vo.BlogDetailVo;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>Desciption:</p>
 * CreateTime : 2017/10/18 下午6:08
 * Author : Weidan
 * Version : V1.0
 */
@Service
public class BlogService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private BlogMapper blogMapper;

    /** 存放网站所有的目录 */
    List<CategoryInfo> categoryInfoList;

    /** 存放名字与目录的对应关系 */
    Map<String, CategoryInfo> categoryInfoMap;

    public void setCategoryInfoList(List<CategoryInfo> categoryInfoList) {
        this.categoryInfoList = categoryInfoList;
        categoryInfoMap = Maps.newHashMapWithExpectedSize(categoryInfoList.size());
        for (CategoryInfo categoryInfo : categoryInfoList) {
            categoryInfo.setId(UUID.randomUUID().toString());
            categoryInfoMap.put(categoryInfo.getTitle(), categoryInfo);
        }
        this.save(categoryInfoList);
    }

    private void save(List<CategoryInfo> categoryInfoList) {
        for (CategoryInfo categoryInfo : categoryInfoList) {
            CategoryInfo exam = new CategoryInfo();
            exam.setTitle(categoryInfo.getTitle());

            if (categoryMapper.selectCount(exam) < 1) {
                categoryMapper.insertSelective(categoryInfo);
            }
        }
    }

    public void saveBlog(BlogDetailVo blogDetailVo) {

        String cateId = categoryInfoMap.get(blogDetailVo.getCateName()).getId();

        BlogInfo exam = new BlogInfo();
        exam.setTitle(blogDetailVo.getTitle());

        if (blogMapper.selectCount(exam) < 1) {
            BlogInfo blogInfo = new BlogInfo();
            blogInfo.setCateId(cateId);
            blogInfo.setContent(blogDetailVo.getContent());
            blogInfo.setId(UUID.randomUUID().toString());
            blogInfo.setTitle(blogDetailVo.getTitle());
            blogMapper.insertSelective(blogInfo);
        }
    }
}

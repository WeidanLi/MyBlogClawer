package cn.liweidan.crawer.po;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>Desciption:博文信息PO类</p>
 * CreateTime : 2017/10/18 下午1:53
 * Author : Weidan
 * Version : V1.0
 */
@Table(name = "blog")
public class BlogInfo {

    @Id
    private String id;
    private String title;
    private String content;
    private String cateId;

    @Override
    public String toString() {
        return "BlogInfo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", cateId=" + cateId +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }
}

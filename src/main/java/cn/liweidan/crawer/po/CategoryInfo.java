package cn.liweidan.crawer.po;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>Desciption:</p>
 * CreateTime : 2017/10/18 下午5:58
 * Author : Weidan
 * Version : V1.0
 */
@Table(name = "category")
public class CategoryInfo {

    @Id
    private String id;

    private String title;

    @Override
    public String toString() {
        return "CategoryInfo{" +
                "id=" + id +
                ", title='" + title + '\'' +
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
}

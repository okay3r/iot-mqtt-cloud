package cloud.entity;

import java.io.Serializable;

/**
 * 设备类别
 * category
 * @author 
 */
public class Category implements Serializable {
    private Integer id;

    private String categoryName;

    private String categoryRemark;

    private static final long serialVersionUID = 1L;

    public Category() {
    }

    public Category(Integer id, String categoryName, String categoryRemark) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryRemark = categoryRemark;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", categoryRemark='" + categoryRemark + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryRemark() {
        return categoryRemark;
    }

    public void setCategoryRemark(String categoryRemark) {
        this.categoryRemark = categoryRemark;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
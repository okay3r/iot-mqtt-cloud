package yx.graduation.elec.pojo;

import javax.persistence.Column;
import javax.persistence.Id;

public class Category {
    @Id
    private Long id;

    /**
     * 类别名称
     */
    @Column(name = "category_name")
    private String categoryName;

    /**
     * 类别备注
     */
    @Column(name = "category_remark")
    private String categoryRemark;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取类别名称
     *
     * @return category_name - 类别名称
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * 设置类别名称
     *
     * @param categoryName 类别名称
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * 获取类别备注
     *
     * @return category_remark - 类别备注
     */
    public String getCategoryRemark() {
        return categoryRemark;
    }

    /**
     * 设置类别备注
     *
     * @param categoryRemark 类别备注
     */
    public void setCategoryRemark(String categoryRemark) {
        this.categoryRemark = categoryRemark;
    }
}
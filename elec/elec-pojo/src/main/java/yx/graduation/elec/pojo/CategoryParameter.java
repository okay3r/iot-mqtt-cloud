package yx.graduation.elec.pojo;

import javax.persistence.*;

@Table(name = "category_parameter")
public class CategoryParameter {
    @Id
    private Integer id;

    /**
     * 类别id
     */
    @Column(name = "category_id")
    private Long categoryId;

    /**
     * 类别名称
     */
    @Column(name = "category_name")
    private String categoryName;

    /**
     * 参数id
     */
    @Column(name = "param_id")
    private Long paramId;

    /**
     * 参数名称
     */
    @Column(name = "param_name")
    private String paramName;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取类别id
     *
     * @return category_id - 类别id
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * 设置类别id
     *
     * @param categoryId 类别id
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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
     * 获取参数id
     *
     * @return param_id - 参数id
     */
    public Long getParamId() {
        return paramId;
    }

    /**
     * 设置参数id
     *
     * @param paramId 参数id
     */
    public void setParamId(Long paramId) {
        this.paramId = paramId;
    }

    /**
     * 获取参数名称
     *
     * @return param_name - 参数名称
     */
    public String getParamName() {
        return paramName;
    }

    /**
     * 设置参数名称
     *
     * @param paramName 参数名称
     */
    public void setParamName(String paramName) {
        this.paramName = paramName;
    }
}
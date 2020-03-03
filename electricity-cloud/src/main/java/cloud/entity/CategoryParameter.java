package cloud.entity;

import java.io.Serializable;

/**
 * category_parameter
 * @author 
 */
public class CategoryParameter implements Serializable {
    private Integer id;

    private Integer categoryId;

    private Integer parameterId;

    private static final long serialVersionUID = 1L;

    public CategoryParameter() {
    }

    public CategoryParameter(Integer id, Integer categoryId, Integer parameterId) {
        this.id = id;
        this.categoryId = categoryId;
        this.parameterId = parameterId;
    }

    @Override
    public String toString() {
        return "CategoryParameter{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", parameterId=" + parameterId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getParameterId() {
        return parameterId;
    }

    public void setParameterId(Integer parameterId) {
        this.parameterId = parameterId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
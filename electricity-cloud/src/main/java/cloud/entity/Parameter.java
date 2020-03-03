package cloud.entity;

import java.io.Serializable;

/**
 * parameter
 * @author 
 */
public class Parameter implements Serializable {
    private Integer id;

    private String parameterName;

    private String parameterUnit;

    private static final long serialVersionUID = 1L;

    public Parameter() {
    }

    public Parameter(Integer id, String parameterName, String parameterUnit) {
        this.id = id;
        this.parameterName = parameterName;
        this.parameterUnit = parameterUnit;
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "id=" + id +
                ", parameterName='" + parameterName + '\'' +
                ", parameterUnit='" + parameterUnit + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getParameterUnit() {
        return parameterUnit;
    }

    public void setParameterUnit(String parameterUnit) {
        this.parameterUnit = parameterUnit;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}

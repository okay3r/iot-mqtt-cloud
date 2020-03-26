package yx.graduation.elec.pojo;

import javax.persistence.*;

public class Parameter {
    @Id
    private Long id;

    @Column(name = "parameter_name")
    private String parameterName;

    @Column(name = "parameter_unit")
    private String parameterUnit;

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
     * @return parameter_name
     */
    public String getParameterName() {
        return parameterName;
    }

    /**
     * @param parameterName
     */
    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    /**
     * @return parameter_unit
     */
    public String getParameterUnit() {
        return parameterUnit;
    }

    /**
     * @param parameterUnit
     */
    public void setParameterUnit(String parameterUnit) {
        this.parameterUnit = parameterUnit;
    }
}
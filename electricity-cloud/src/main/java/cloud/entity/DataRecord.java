package cloud.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * data_record
 * @author 
 */
public class DataRecord implements Serializable {
    private Integer id;

    private Integer deviceId;

    private Integer value;

    private Integer parameterId;

    private Date time;

    private static final long serialVersionUID = 1L;

    public DataRecord() {
    }

    public DataRecord(Integer id, Integer deviceId, Integer value, Integer parameterId, Date time) {
        this.id = id;
        this.deviceId = deviceId;
        this.value = value;
        this.parameterId = parameterId;
        this.time = time;
    }

    @Override
    public String toString() {
        return "DataRecord{" +
                "id=" + id +
                ", deviceId=" + deviceId +
                ", value=" + value +
                ", parameterId=" + parameterId +
                ", time=" + time +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getParameterId() {
        return parameterId;
    }

    public void setParameterId(Integer parameterId) {
        this.parameterId = parameterId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
package cloud.entity;

import java.io.Serializable;

/**
 * 报警值
 * alarm
 * @author 
 */
public class Alarm implements Serializable {
    private Integer id;

    private Integer deviceId;

    private Integer parameterId;

    //上限
    private Integer up;

    //下限
    private Integer down;

    private static final long serialVersionUID = 1L;

    public Alarm() {
    }

    public Alarm(Integer id, Integer deviceId, Integer parameterId, Integer up, Integer down) {
        this.id = id;
        this.deviceId = deviceId;
        this.parameterId = parameterId;
        this.up = up;
        this.down = down;
    }

    @Override
    public String toString() {
        return "Alarm{" +
                "id=" + id +
                ", deviceId=" + deviceId +
                ", parameterId=" + parameterId +
                ", up=" + up +
                ", down=" + down +
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

    public Integer getParameterId() {
        return parameterId;
    }

    public void setParameterId(Integer parameterId) {
        this.parameterId = parameterId;
    }

    public Integer getUp() {
        return up;
    }

    public void setUp(Integer up) {
        this.up = up;
    }

    public Integer getDown() {
        return down;
    }

    public void setDown(Integer down) {
        this.down = down;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
package ccuiot.iotc.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_operation")
public class UserOperation {
    /**
     * id
     */
    @Id
    private Long id;

    /**
     * 操作指令
     */
    private String op;

    /**
     * 目标ip
     */
    private String address;

    /**
     * 操作时间
     */
    private Date time;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取操作指令
     *
     * @return op - 操作指令
     */
    public String getOp() {
        return op;
    }

    /**
     * 设置操作指令
     *
     * @param op 操作指令
     */
    public void setOp(String op) {
        this.op = op;
    }

    /**
     * 获取目标ip
     *
     * @return address - 目标ip
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置目标ip
     *
     * @param address 目标ip
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取操作时间
     *
     * @return time - 操作时间
     */
    public Date getTime() {
        return time;
    }

    /**
     * 设置操作时间
     *
     * @param time 操作时间
     */
    public void setTime(Date time) {
        this.time = time;
    }
}
package ccu.iot.cloud.entity;

import java.util.Date;

public class OperationRecord {
    private Long id;
    private Integer userId;
    private Integer nodeId;
    private String op;
    private Boolean isSuccess;
    private Date time;

    public OperationRecord() {
    }

    public OperationRecord(Long id, Integer userId, Integer nodeId, String op, Boolean isSuccess, Date time) {
        this.id = id;
        this.userId = userId;
        this.nodeId = nodeId;
        this.op = op;
        this.isSuccess = isSuccess;
        this.time = time;
    }

    @Override
    public String toString() {
        return "OperationRecord{" +
                "id=" + id +
                ", userId=" + userId +
                ", nodeId=" + nodeId +
                ", op='" + op + '\'' +
                ", isSuccess=" + isSuccess +
                ", time=" + time +
                '}';
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}

package ccu.iot.cloud.entity;

import java.util.Date;

public class MqttNode {
    private Integer id;
    private String nodeName;
    private String nodeRemark;
    private Date nodeCreateTime;
    private Date nodeUpdateTime;

    public MqttNode() {
    }

    public MqttNode(Integer id, String nodeName, String nodeRemark, Date nodeCreateTime, Date nodeUpdateTime) {
        this.id = id;
        this.nodeName = nodeName;
        this.nodeRemark = nodeRemark;
        this.nodeCreateTime = nodeCreateTime;
        this.nodeUpdateTime = nodeUpdateTime;
    }

    @Override
    public String toString() {
        return "MqttNode{" +
                "id=" + id +
                ", nodeName='" + nodeName + '\'' +
                ", nodeRemark='" + nodeRemark + '\'' +
                ", nodeCreateTime=" + nodeCreateTime +
                ", nodeUpdateTime=" + nodeUpdateTime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeRemark() {
        return nodeRemark;
    }

    public void setNodeRemark(String nodeRemark) {
        this.nodeRemark = nodeRemark;
    }

    public Date getNodeCreateTime() {
        return nodeCreateTime;
    }

    public void setNodeCreateTime(Date nodeCreateTime) {
        this.nodeCreateTime = nodeCreateTime;
    }

    public Date getNodeUpdateTime() {
        return nodeUpdateTime;
    }

    public void setNodeUpdateTime(Date nodeUpdateTime) {
        this.nodeUpdateTime = nodeUpdateTime;
    }
}

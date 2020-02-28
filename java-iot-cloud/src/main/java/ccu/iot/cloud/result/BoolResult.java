package ccu.iot.cloud.result;

public class BoolResult<T> {

    private Boolean isOk;
    private String msg;
    private T data;

    public BoolResult() {
    }

    public BoolResult(Boolean isOk, String msg, T data) {
        this.isOk = isOk;
        this.msg = msg;
        this.data = data;
    }

    public Boolean getOk() {
        return isOk;
    }

    public void setOk(Boolean ok) {
        isOk = ok;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

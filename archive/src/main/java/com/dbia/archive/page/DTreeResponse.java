package com.dbia.archive.page;

/** response返回类*/
public class DTreeResponse {
    /** 状态类*/
    private Status status;
    /** 数据*/
    private Object data;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DTreeResponse{" +
                "status=" + status +
                ", data=" + data +
                '}';
    }
}

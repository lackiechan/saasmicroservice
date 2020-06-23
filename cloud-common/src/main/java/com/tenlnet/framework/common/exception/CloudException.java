package com.tenlnet.framework.common.exception;

/**
 * @Author: lackiechan
 * @Email: 2734858720@qq.com
 * @Date: 2019-08-26 00:27
 */
public class CloudException extends RuntimeException{
    private String msg;
    private int code = 500;

    public CloudException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public CloudException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public CloudException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public CloudException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

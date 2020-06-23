package com.tenlnet.framework.common.api;

/**
 * @Author: lackiechan
 * @Email: 2734858720@qq.com
 * @Date: 2019-07-28 23:09
 */
public class ApiCommonResponse {
    private int code;
    private String msg;

    public ApiCommonResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

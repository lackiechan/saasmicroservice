package com.tenlnet.framework.common.api;

import java.io.Serializable;

/**
 * @Author: lackiechan
 * @Email: 2734858720@qq.com
 * @Date: 2019-07-29 01:11
 */

public class Result implements Serializable {
    protected int code;
    protected String msg;

    public Result() {

    }

    public Result(int code, String msg) {
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

    public static Result ok(){
        return new Result(0,"成功");
    }

    public static Result ok(String msg){

        return new Result(0,msg);
    }

    public static Result error(){
        return new Result(-1,"请求失败");
    }

    public static Result error(int code,String msg){
        return new Result(-1,msg);
    }

    public static Result error(String msg){

        return new Result(-1,msg);
    }
}

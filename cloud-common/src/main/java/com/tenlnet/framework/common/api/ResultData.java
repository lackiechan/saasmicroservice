package com.tenlnet.framework.common.api;

import java.io.Serializable;

/**
 * @Author: lackiechan
 * @Email: 2734858720@qq.com
 * @Date: 2019-07-29 01:11
 */
public class ResultData<T> extends  Result implements Serializable {
    protected T data;
    protected T list;

    public ResultData() {
        super();
    }

    public ResultData(int code, String msg, T data) {
        super(code, msg);
        this.data = data;
    }

    public ResultData(int code, String msg) {
        super(code, msg);
    }

    public T getData() {
        return data;
    }

    public ResultData setData(T data) {
        this.data = data;

        return this;
    }

    public ResultData setLit(T list) {
        this.list = list;

        return this;
    }

    public static ResultData ok(){
        return new ResultData(0,"成功");
    }

    public static ResultData error(int code,String msg){
        return new ResultData(-1,msg);
    }

    public static ResultData error(String msg){

        return new ResultData(-1,msg);
    }

}

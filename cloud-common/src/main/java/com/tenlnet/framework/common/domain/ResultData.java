package com.tenlnet.framework.common.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 * @Author: lackiechan
 * @Email: 2734858720@qq.com
 * @Date: 2019-07-26 14:48
 */
public class ResultData<T> extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;


	private T data;

	public ResultData() {
	}

	public ResultData(Integer code, String msg) {
		put("code", 0);
		put("msg", msg);
	}
	
	public static ResultData error() {
		return error(500, "未知异常，请联系管理员");
	}
	
	public static ResultData error(String msg) {
		return error(500, msg);
	}
	
	public static ResultData error(int code, String msg) {
		ResultData r = new ResultData();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static ResultData ok(String msg) {
		ResultData r = new ResultData();
		r.put("msg", msg);
		return r;
	}
	
	public static ResultData ok(Map<String, Object> map) {
		ResultData r = new ResultData();
		r.putAll(map);
		return r;
	}
	
	public static ResultData ok() {
		return new ResultData(0,"请求成功");
	}

	@Override
	public ResultData put(String key, Object value) {
		super.put(key, value);

		return this;
	}

	public T getData() {
		return data;
	}

	public ResultData<T> setData(T data) {
		this.data = data;

		return this;
	}
}

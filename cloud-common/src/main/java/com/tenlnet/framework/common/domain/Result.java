package com.tenlnet.framework.common.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 * @Author: lackiechan
 * @Email: 2734858720@qq.com
 * @Date: 2019-07-26 14:48
 */
public class Result extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	public Result() {
	}

	public Result(Integer code,String msg) {
		put("code", 0);
		put("msg", msg);
	}
	
	public static Result error() {
		return error(500, "未知异常，请联系管理员");
	}
	
	public static Result error(String msg) {
		return error(500, msg);
	}
	
	public static Result error(int code, String msg) {
		Result r = new Result();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static Result ok(String msg) {
		Result r = new Result();
		r.put("msg", msg);
		return r;
	}
	
	public static Result ok(Map<String, Object> map) {
		Result r = new Result();
		r.putAll(map);
		return r;
	}
	
	public static Result ok() {
		return new Result(0,"请求成功");
	}

	@Override
	public Result put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}

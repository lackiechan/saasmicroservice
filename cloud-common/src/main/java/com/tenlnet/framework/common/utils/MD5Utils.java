package com.tenlnet.framework.common.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @Author: lackiechan
 * @Email: 2734858720@qq.com
 * @Date: 2019-08-03 18:35
 */
public class MD5Utils {
    public static String MD5(String str){
       return DigestUtils.md5Hex(str);
    }
}

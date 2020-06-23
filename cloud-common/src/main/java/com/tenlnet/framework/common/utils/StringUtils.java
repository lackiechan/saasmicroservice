package com.tenlnet.framework.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @Author: lackiechan
 * @Email: 2734858720@qq.com
 * @Date: 2019-08-03 18:13
 */
public class StringUtils extends org.apache.commons.lang.StringUtils {
    public static String InputStreamToString(InputStream inputStream){
        try {
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }

            String str = result.toString(StandardCharsets.UTF_8.name());

            return str;
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }finally {
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}

package com.tenlnet.framework.monitor.service.impl;


import com.tenlnet.framework.monitor.service.NotifyService;
import com.tenlnet.framework.common.utils.HttpUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lackiechan
 * @Email: 2734858720@qq.com
 * @Date: 2019-08-15 01:57
 */
@Service
public class NotifyServiceImpl implements NotifyService {

    @Override
    public boolean sendNofity(String phone,String title, String content, String remark) {
        Map<String,String> params=new HashMap<>();
        params.put("token","sj234uo234njksjdfjlf");
        params.put("phone",phone);
        params.put("title",title);
        params.put("content",content);
        params.put("remark",remark);

        String url="https://mp.mhealth100.com/ip-healthmanager-manager-web/notify/sendNotifyToUser";
        HttpUtils.URLGet(url,params,"utf-8");

        return true;
    }

    @Override
    public boolean sendZenTaoNofity(String phone,String title, String content, String remark) {
        return sendNofity( phone,  "服务监控:"+title,content, remark);
    }
}

package com.tenlnet.framework.monitor.service;

/**
 * @Author: lackiechan
 * @Email: 2734858720@qq.com
 * @Date: 2019-08-15 01:56
 */
public interface NotifyService {
    /**
     * 给指定对手机号发送通知
     * @param phone
     * @param content
     * @param remark
     * @return
     */
    public boolean sendNofity(String phone, String title, String content, String remark);

    /**
     * 给指定对手机号发送通知
     * @param phone
     * @param content
     * @param remark
     * @return
     */
    public boolean sendZenTaoNofity(String phone, String title, String content, String remark);
}

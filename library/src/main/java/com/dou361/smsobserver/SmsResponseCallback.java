package com.dou361.smsobserver;

/**
 * ========================================
 * <p/>
 * 版 权：dou361.com 版权所有 （C） 2015
 * <p/>
 * 作 者：陈冠明
 * <p/>
 * 个人网站：http://www.dou361.com
 * <p/>
 * 版 本：1.0
 * <p/>
 * 创建日期：2016/6/14 21:11
 * <p/>
 * 描 述：短信接收器回调接口
 * <p/>
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
public interface SmsResponseCallback {

    /**
     * 返回短信内容
     *
     * @param smsContent
     * @see [类、类#方法、类#成员]
     */
    void onCallbackSmsContent(String smsContent);
}

package com.dou361.smsobserver;

import android.os.Handler;
import android.os.Message;

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
 * 创建日期：2016/6/14 21:09
 * <p/>
 * 描 述：短信处理
 * <p/>
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
public class SmsHandler extends Handler {

    private SmsResponseCallback mCallback;

    /***
     * 短信过滤器
     */
    private SmsFilter smsFilter;

    public SmsHandler(SmsResponseCallback callback) {
        this.mCallback = callback;
    }

    public SmsHandler(SmsResponseCallback callback, SmsFilter smsFilter) {
        this(callback);
        this.smsFilter = smsFilter;
    }

    /***
     * 设置短信过滤器
     *
     * @param smsFilter 短信过滤器
     */
    public void setSmsFilter(SmsFilter smsFilter) {
        this.smsFilter = smsFilter;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        if (msg.what == SmsObserver.MSG_RECEIVED_CODE) {
            String[] smsInfos = (String[]) msg.obj;
            if (smsInfos != null && smsInfos.length == 2 && mCallback != null) {
                if (smsFilter == null) {
                    smsFilter = new ContentSmsFilter();
                }
                mCallback.onCallbackSmsContent(smsFilter.filter(smsInfos[0], smsInfos[1]));
            }
        }
    }
}

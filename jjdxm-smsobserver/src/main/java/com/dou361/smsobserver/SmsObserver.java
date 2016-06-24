package com.dou361.smsobserver;

import android.app.Activity;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

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
 * 创建日期：2016/6/14 21:10
 * <p/>
 * 描 述：短信接收观察者
 * <p/>
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
public class SmsObserver extends ContentObserver {

    private Context mContext;
    public static final int MSG_RECEIVED_CODE = 1001;
    private SmsHandler mHandler;

    /***
     * 构造器
     *
     * @param context
     * @param callback  短信接收器
     * @param smsFilter 短信过滤器
     */
    public SmsObserver(Activity context, SmsResponseCallback callback, SmsFilter smsFilter) {
        this(new SmsHandler(callback, smsFilter));
        this.mContext = context;
    }

    public SmsObserver(Activity context, SmsResponseCallback callback) {
        this(new SmsHandler(callback));
        this.mContext = context;
    }

    public SmsObserver(SmsHandler handler) {
        super(handler);
        this.mHandler = handler;
    }

    /***
     * 设置短信过滤器
     *
     * @param smsFilter
     */
    public void setSmsFilter(SmsFilter smsFilter) {
        mHandler.setSmsFilter(smsFilter);
    }

    /***
     * 注册短信变化观察者
     *
     * @see [类、类#方法、类#成员]
     */
    public void registerSMSObserver() {
        Uri uri = Uri.parse("content://sms");
        if (mContext != null) {
            mContext.getContentResolver().registerContentObserver(uri,
                    true, this);
        }
    }

    /***
     * 注销短信变化观察者
     *
     * @see [类、类#方法、类#成员]
     */
    public void unregisterSMSObserver() {
        if (mContext != null) {
            mContext.getContentResolver().unregisterContentObserver(this);
        }
        if (mHandler != null) {
            mHandler = null;
        }
    }

    @Override
    public void onChange(boolean selfChange, Uri uri) {
        super.onChange(selfChange, uri);
        if (uri.toString().equals("content://sms/raw")) {
            return;
        }
        Uri inboxUri = Uri.parse("content://sms/inbox");//收件箱
        try {
            Cursor c = mContext.getContentResolver().query(inboxUri, null, null,
                    null, "date desc");
            if (c != null) {
                if (c.moveToFirst()) {
                    String address = c.getString(c.getColumnIndex("address"));
                    String body = c.getString(c.getColumnIndex("body"));
                    if (mHandler != null) {
                        mHandler.obtainMessage(MSG_RECEIVED_CODE, new String[]{address, body})
                                .sendToTarget();
                    }
                    Log.i(getClass().getName(), "发件人为：" + address + " " + "短信内容为：" + body);
                }
                c.close();
            }
        } catch (SecurityException e) {
            Log.e(getClass().getName(), "获取短信权限失败", e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

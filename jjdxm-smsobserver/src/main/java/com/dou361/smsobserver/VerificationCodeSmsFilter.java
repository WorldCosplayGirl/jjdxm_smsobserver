package com.dou361.smsobserver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
 * 描 述：短信验证码过滤器
 * <p/>
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
public class VerificationCodeSmsFilter implements SmsFilter {
    /**
     * 需要过滤的发短信的人
     */
    private String filterAddress;

    public VerificationCodeSmsFilter(String filterAddress) {
        this.filterAddress = filterAddress;
    }

    @Override
    public String filter(String address, String smsContent) {
        if (address.startsWith(filterAddress)) {
            Pattern pattern = Pattern.compile("(\\d{4,8})");//匹配4-8位的数字
            Matcher matcher = pattern.matcher(smsContent);
            if (matcher.find()) {
                return matcher.group(0);
            }
        }
        return null;
    }
}

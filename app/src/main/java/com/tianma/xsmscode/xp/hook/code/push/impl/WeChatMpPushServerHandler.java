package com.tianma.xsmscode.xp.hook.code.push.impl;

import android.content.Context;

import com.tianma.xsmscode.data.db.entity.SmsMsg;
import com.tianma.xsmscode.xp.hook.code.push.PushServerHandler;

import de.robv.android.xposed.XSharedPreferences;

/**
 * 推送到指定服务上 - 微信公众号
 */
public class WeChatMpPushServerHandler extends PushServerHandler {

    /**
     * 推送到指定服务上
     * @param mPluginContext
     * @param mPhoneContext
     * @param mSmsMsg
     * @param xsp
     */
    @Override
    public void push(Context mPluginContext, Context mPhoneContext, SmsMsg mSmsMsg, XSharedPreferences xsp) {

    }
}

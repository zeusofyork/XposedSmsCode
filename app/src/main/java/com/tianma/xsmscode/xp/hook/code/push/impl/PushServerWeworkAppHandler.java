package com.tianma.xsmscode.xp.hook.code.push.impl;

import android.content.Context;

import com.tianma.xsmscode.data.db.entity.SmsMsg;
import com.tianma.xsmscode.xp.hook.code.push.PushServerHandler;

import java.io.IOException;

import de.robv.android.xposed.XSharedPreferences;

/**
 * 推送到指定服务上 - 企业微信app
 */
public class PushServerWeworkAppHandler extends PushServerHandler {

    /**
     * 推送到指定服务上
     *
     * @param mPluginContext
     * @param mPhoneContext
     * @param mSmsMsg
     * @param xsp
     */
    @Override
    public void push(Context mPluginContext, Context mPhoneContext, SmsMsg mSmsMsg, XSharedPreferences xsp) throws IOException {

    }
}

package com.tianma.xsmscode.xp.hook.code.action.impl;

import android.content.Context;
import android.os.Bundle;

import com.tianma.xsmscode.common.utils.XLog;
import com.tianma.xsmscode.data.db.entity.SmsMsg;
import com.tianma.xsmscode.xp.hook.code.action.CallableAction;
import com.tianma.xsmscode.xp.hook.code.push.PushServerHandler;
import com.tianma.xsmscode.xp.hook.code.push.PushTypeEnum;

import java.util.Objects;

import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedHelpers;

/**
 * 推送通知到其他服务上
 */
public class PushAction extends CallableAction {

    private PushTypeEnum pushType;

    public PushAction(Context pluginContext, Context phoneContext, SmsMsg smsMsg, XSharedPreferences xsp, PushTypeEnum pushTypeEnum) {
        super(pluginContext, phoneContext, smsMsg, xsp);
        this.pushType = pushTypeEnum;
    }

    @Override
    public Bundle action() {

        XLog.d("PushAction Start: %s ", pushType.name());
        try {
            PushServerHandler handler = PushServerHandler.get(pushType.name());
            if (null == handler) {
                XLog.e("Unknown PushAction Type: %s", pushType.name());
                return null;
            }
            XLog.i("开始执行handler.push方法");
            handler.push(mPluginContext, mPhoneContext, mSmsMsg, xsp);
            XLog.i("执行handler.push方法完成");
        } catch (Exception e) {
            XLog.e("push异常: %s", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}

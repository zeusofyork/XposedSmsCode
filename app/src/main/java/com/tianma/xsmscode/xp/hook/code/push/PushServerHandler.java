package com.tianma.xsmscode.xp.hook.code.push;

import android.content.Context;

import com.tianma.xsmscode.common.utils.XLog;
import com.tianma.xsmscode.common.utils.XSPUtils;
import com.tianma.xsmscode.data.db.entity.SmsMsg;
import com.tianma.xsmscode.xp.hook.code.push.impl.PushServerWeworkAppHandler;
import com.tianma.xsmscode.xp.hook.code.push.impl.WeChatMpPushServerHandler;
import com.tianma.xsmscode.xp.hook.code.push.impl.PushServerWeworkRobotHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.robv.android.xposed.XSharedPreferences;

public abstract class PushServerHandler {

    private static final Map<String, PushServerHandler> handlers = new HashMap<>(2);

    static {
        resetHandlers();
    }

    private static void resetHandlers() {
        XLog.d("重置handlers map数据");
        handlers.clear();
        handlers.put(PushTypeEnum.WeChat_MP.name(), new WeChatMpPushServerHandler());
        handlers.put(PushTypeEnum.Wework_Webhook.name(), new PushServerWeworkRobotHandler());
        handlers.put(PushTypeEnum.Wework_App.name(), new PushServerWeworkAppHandler());
    }

    public static PushServerHandler get(String pushTypeName) {
        if (handlers.isEmpty()) {
            resetHandlers();
        }
        XLog.d("handlers.size = %s", handlers.size());
        XLog.d("handlers.key = %s", handlers.keySet().toString());
        XLog.d("handlers.pushTypeName = %s", pushTypeName);
        PushServerHandler handler = handlers.get(pushTypeName);
        if (null == handler) {
            XLog.d("未匹配到数据: %s", pushTypeName);
            return null;
        }
        XLog.d("匹配到的数据: %s", handler.getClass().getName());
        return handler;
    }

    /**
     * 推送到指定服务上
     *
     * @param mPluginContext
     * @param mPhoneContext
     * @param mSmsMsg
     * @param xsp
     */
    public abstract void push(Context mPluginContext, Context mPhoneContext, SmsMsg mSmsMsg, XSharedPreferences xsp) throws IOException;


    public static String getPushServerUrl(XSharedPreferences xsp, String key, String baseUrl) {
        String pushServerUrl = XSPUtils.getProperty(xsp, key, "").replace(baseUrl, "");
        if (!pushServerUrl.startsWith("/")) {
            return "/" + pushServerUrl;
        }
        return pushServerUrl;
    }
}

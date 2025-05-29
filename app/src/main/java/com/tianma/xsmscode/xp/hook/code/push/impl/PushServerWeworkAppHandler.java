package com.tianma.xsmscode.xp.hook.code.push.impl;

import android.content.Context;

import com.tianma.xsmscode.common.constant.PrefConst;
import com.tianma.xsmscode.common.utils.DateUtil;
import com.tianma.xsmscode.common.utils.GsonUtil;
import com.tianma.xsmscode.common.utils.XLog;
import com.tianma.xsmscode.common.utils.XSPUtils;
import com.tianma.xsmscode.data.db.entity.SmsMsg;
import com.tianma.xsmscode.data.http.ApiConst;
import com.tianma.xsmscode.data.http.entity.ShuaiPushMessageReq;
import com.tianma.xsmscode.data.http.service.ServiceGenerator;
import com.tianma.xsmscode.data.http.service.WeworkService;
import com.tianma.xsmscode.xp.hook.code.push.PushServerHandler;

import java.io.IOException;
import java.util.Date;

import de.robv.android.xposed.XSharedPreferences;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;

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

        WeworkService weworkService = ServiceGenerator.getInstance().createService(ApiConst.SHUAI_PUSH_BASE_URL, WeworkService.class);

        String url = super.getPushServerUrl(xsp, PrefConst.KEY_PUSH_SERVER_SHUAIPUSH_NAME, ApiConst.SHUAI_PUSH_BASE_URL);

        ShuaiPushMessageReq messageReq = getShuaiPushMessageReq(
                XSPUtils.getProperty(xsp, PrefConst.KEY_PUSH_SERVER_SHUAIPUSH_TO_USER, "@all"),
                XSPUtils.getProperty(xsp, PrefConst.KEY_PUSH_SERVER_SHUAIPUSH_MESSAGE_TYPE, "text"),
                mSmsMsg);

        String content = GsonUtil.toJson(messageReq);

        XLog.d("PushServerWeworkAppHandler#url: %s", url);
        XLog.d("PushServerWeworkAppHandler#request: %s", content);

        Response<ResponseBody> bodyResponse = weworkService.sendOfApp(url,
                RequestBody.create(MediaType.parse("application/json; charset=utf-8"), content))
                .execute();

        XLog.d("PushServerWeworkAppHandler#response: %s", GsonUtil.toJson(bodyResponse));
    }

    private ShuaiPushMessageReq getShuaiPushMessageReq(String toUser, String messageType, SmsMsg mSmsMsg) {
        ShuaiPushMessageReq messageReq = new ShuaiPushMessageReq();
        messageReq.setPlatform(null);
        messageReq.setMessageType(messageType);
        messageReq.setToUser(toUser);
        String message = "验证码通知: " + mSmsMsg.getSmsCode() +
                "\n 发送号码: " + mSmsMsg.getSender() +
                "\n 服务方: " + mSmsMsg.getCompany() +
                "\n 时间: " + DateUtil.formatDateTime(new Date(mSmsMsg.getDate())) +
                "\n\n" + mSmsMsg.getBody();
        messageReq.setMessage(message);
        return messageReq;
    }
}

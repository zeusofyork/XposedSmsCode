package com.tianma.xsmscode.xp.hook.code.push.impl;

import android.content.Context;


import com.tianma.xsmscode.common.utils.DateUtil;
import com.tianma.xsmscode.common.utils.GsonUtil;
import com.tianma.xsmscode.common.utils.XLog;
import com.tianma.xsmscode.common.utils.XSPUtils;
import com.tianma.xsmscode.data.db.entity.SmsMsg;
import com.tianma.xsmscode.data.http.ApiConst;
import com.tianma.xsmscode.data.http.entity.WeworkWebhookReq;
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
 * 推送到指定服务上 - 企业微信机器人
 */
public class PushServerWeworkRobotHandler extends PushServerHandler {

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

        WeworkService weworkService = ServiceGenerator.getInstance()
                .createService(ApiConst.WEWORK_BASE_URL, WeworkService.class);
        String pushServerCpUrl = getPushServerCpUrl(xsp);

        WeworkWebhookReq req = generateReqEntity(mSmsMsg);
        String json = GsonUtil.toJson(req);

        XLog.d("WeworkPushServerHandler#url: %s", pushServerCpUrl);
        XLog.d("WeworkPushServerHandler#push: %s", json);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Response<ResponseBody> bodyResponse = weworkService.sendOfWebhook(pushServerCpUrl, requestBody).execute();

        XLog.d("WeworkPushServerHandler#push response: %s", GsonUtil.toJson(bodyResponse));
    }

    private String getPushServerCpUrl(XSharedPreferences xsp) {
        String pushServerCpUrl = XSPUtils.pushServerCpName(xsp);
        pushServerCpUrl = pushServerCpUrl.replace(ApiConst.WEWORK_BASE_URL, "");
        if (!pushServerCpUrl.startsWith("/")) {
            pushServerCpUrl = "/" + pushServerCpUrl;
        }
        return pushServerCpUrl;
    }

    private WeworkWebhookReq generateReqEntity(SmsMsg sms) {
        String content = "验证码通知: <font color=\"warning\">" + sms.getSmsCode() + "</font>" +
                "\n 发送号码: <font color=\"comment\">" + sms.getSender() + "</font>" +
                "\n 服务方: <font color=\"comment\">" + sms.getCompany() + "</font>" +
                "\n 时间: <font color=\"comment\">" + DateUtil.formatDateTime(new Date(sms.getDate())) + "</font>" +
                "\n\n<font color=\"comment\">" + sms.getBody() + "</font>";

        WeworkWebhookReq req = new WeworkWebhookReq();
        req.setMsgtype("markdown");
        req.setMarkdown(new WeworkWebhookReq.Markdown(content));

        return req;
    }
}

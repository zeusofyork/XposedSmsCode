package com.tianma.xsmscode.common.utils;

import com.google.gson.Gson;

public class GsonUtil {

    private static final Gson gson = new Gson();

    public static String toJson(Object obj) {

        return gson.toJson(obj);
    }

    public static <T> T toBean(String json, Class<T> clazz) {

        return gson.fromJson(json, clazz);
    }
}

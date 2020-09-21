package com.jeffrey.utils.json;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class GsonUtil {

    private static Logger logger = LoggerFactory.getLogger(GsonUtil.class);

    private static Gson undsGson = new GsonBuilder().serializeNulls().setPrettyPrinting().disableHtmlEscaping().setDateFormat("yyyy-MM-dd HH:mm:ss")
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapter(new TypeToken<Map<String, Object>>() {}.getType(), new GsonTypeAdapter())
            .create();

    private static Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss")
            .registerTypeAdapter(new TypeToken<Map<String, Object>>() {}.getType(), new GsonTypeAdapter())
            .create();

    public static Gson buildGson() {
        return gson;
    }

    public static Gson buildUndsGson() {
        return undsGson;
    }

    public static String toJson(Object object) {
        String json = null;
        if (gson != null) {
            json = gson.toJson(object);
        }
        return json;
    }

    public static <T> T jsonToBean(String json, Class<T> clazz) {
        T bean = null;
        try {
            bean = gson.fromJson(json, clazz);
        } catch (JsonSyntaxException e) {
            logger.debug(e.getMessage());
        }
        return bean;

    }

}

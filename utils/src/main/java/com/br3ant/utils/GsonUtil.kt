package com.br3ant.utils;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *     copyright: datedu
 *     @author : br3ant
 *     e-mail : xxx@xx
 *     time   : 2020/4/28
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class GsonUtil {
    private static Gson gson;
    private volatile static GsonUtil mInstance;

    public GsonUtil() {
        gson = new GsonBuilder().registerTypeAdapter(Integer.class, new IntegerDefault0Adapter())
                .registerTypeAdapter(int.class, new IntegerDefault0Adapter())
                .registerTypeAdapter(Double.class, new DoubleDefault0Adapter())
                .registerTypeAdapter(double.class, new DoubleDefault0Adapter())
                .registerTypeAdapter(Long.class, new LongDefault0Adapter())
                .registerTypeAdapter(long.class, new LongDefault0Adapter())
                .registerTypeAdapter(boolean.class, new BooleanDefault0Adapter())
                .registerTypeAdapter(Boolean.class, new BooleanDefault0Adapter())
                .registerTypeAdapter(JsonObject.class, new JsonObjectDefault0Adapter())
                .create();
    }

    /**
     * 单例模式的使用
     */
    public static GsonUtil getInstance() {
        if (mInstance == null) {
            synchronized (GsonUtil.class) {
                if (mInstance == null) {
                    mInstance = new GsonUtil();
                }
            }
        }
        return mInstance;
    }

    public Gson getGson() {
        return gson;
    }

    public static String jsonCreate(Object object) {
        return getInstance()._jsonCreate(object);
    }

    public static Map<String, Object> json2Map(String jsonString) {
        try {
            return getInstance()._json2Map(jsonString);
        } catch (Exception e) {
            Log.e("json2Map", e.toString());
            return new HashMap<>();
        }
    }

    public static <T> List<T> json2List(String jsonString, Class<T> cls) {
        try {
            return getInstance()._json2List(jsonString, cls);
        } catch (Exception e) {
            Log.e("json2List", e.toString());
            return new ArrayList<>();
        }
    }

    public static <T> T json2Bean(String jsonString, Class<T> cls) {
        try {
            return getInstance()._json2Bean(jsonString, cls);
        } catch (Exception e) {
            Log.e("json2Bean", e.toString());
            return null;
        }
    }

    public static <T> List<T> jsonRecreateList(List<?> list, Class<T> cls) {
        return GsonUtil.json2List(GsonUtil.jsonCreate(list), cls);
    }
    /*--------------------------------------*/

    /**
     * 将Json字符串转化为List<T>对象
     *
     * @param jsonString Json字符串
     * @param cls        类型 即实体类
     * @return List<T>对象
     */
    private <T> List<T> _json2List(String jsonString, Class<T> cls) {
        if (TextUtils.isEmpty(jsonString)) {
            return null;
        }
        List<T> list = new ArrayList<>();
        JsonArray array = new JsonParser().parse(jsonString).getAsJsonArray();
        if (gson != null) {
            for (final JsonElement elem : array) {
                list.add(gson.fromJson(elem, cls));
            }
        }

        return list;
    }

    /**
     * 解析json转为bean
     *
     * @param jsonString json数据
     * @return bean
     */
    private <T> T _json2Bean(String jsonString, Class<T> cls) {
        T t = null;
        if (gson != null) {
            JsonReader reader = new JsonReader(new StringReader(jsonString));
            reader.setLenient(true);
            t = gson.fromJson(reader, cls);
        }
        return t;
    }

    /**
     * 将Json字符串转化为Map<String, Object>对象
     *
     * @param jsonString Json字符串
     * @return Map对象
     */
    private Map<String, Object> _json2Map(String jsonString) {
        Map<String, Object> map = new HashMap<>();
        if (gson != null) {
            map = gson.fromJson(jsonString,
                    new TypeToken<Map<String, Object>>() {
                    }.getType());
        }
        return map;
    }

    /**
     * 转成json
     *
     * @param object 对象
     * @return json
     */
    private String _jsonCreate(Object object) {
        String gsonString = null;
        if (gson != null) {
            gsonString = gson.toJson(object);
        }
        return gsonString;
    }

    static class IntegerDefault0Adapter implements JsonSerializer<Integer>, JsonDeserializer<Integer> {
        @Override
        public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            try {
                if (json.getAsString().equals("") || json.getAsString().equals("null")) {//定义为int类型,如果后台返回""或者null,则返回0
                    return 0;
                }
            } catch (Exception ignore) {
            }
            try {
                return json.getAsInt();
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override
        public JsonElement serialize(Integer src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src);
        }
    }

    class DoubleDefault0Adapter implements JsonSerializer<Double>, JsonDeserializer<Double> {
        @Override
        public Double deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            try {
                if (json.getAsString().equals("") || json.getAsString().equals("null")) {//定义为double类型,如果后台返回""或者null,则返回0.00
                    return 0.00;
                }
            } catch (Exception ignore) {
            }
            try {
                return json.getAsDouble();
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override
        public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src);
        }
    }

    class LongDefault0Adapter implements JsonSerializer<Long>, JsonDeserializer<Long> {
        @Override
        public Long deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            try {
                if (json.getAsString().equals("") || json.getAsString().equals("null")) {//定义为long类型,如果后台返回""或者null,则返回0
                    return 0L;
                }
            } catch (Exception ignore) {
            }
            try {
                return json.getAsLong();
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override
        public JsonElement serialize(Long src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src);
        }
    }

    class BooleanDefault0Adapter implements JsonSerializer<Boolean>, JsonDeserializer<Boolean> {
        @Override
        public Boolean deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            try {
                if (json.getAsInt() == 1) {
                    return true;
                } else if (json.getAsInt() == 0) {
                    return false;
                }
            } catch (Exception ignore) {
            }
            try {
                return json.getAsBoolean();
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override
        public JsonElement serialize(Boolean src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src);
        }
    }

    class JsonObjectDefault0Adapter implements JsonDeserializer<JsonObject> {
        @Override
        public JsonObject deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            try {
                if (TextUtils.isEmpty(json.getAsString())) {
                    return null;
                }
            } catch (Exception ignore) {
            }
            try {
                return json.getAsJsonObject();
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

    }
}
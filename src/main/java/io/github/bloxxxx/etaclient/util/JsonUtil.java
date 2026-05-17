package io.github.bloxxxx.etaclient.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public final class JsonUtil {
    private JsonUtil() {}

    public static @Nullable JsonObject getAsJsonObject(JsonObject object, String key) {
        JsonElement element = object.get(key);
        if (element == null) return null;
        if (!element.isJsonObject()) return null;
        return element.getAsJsonObject();
    }
    public static Optional<JsonObject> getAsJsonObjectOp(JsonObject object, String key) {
        return Optional.ofNullable(getAsJsonObject(object, key));
    }
    public static JsonObject getAsJsonObjectDef(JsonObject object, String key, JsonObject def) {
        return getAsJsonObjectOp(object, key).orElse(def);
    }
    public static JsonObject getAsJsonObjectDefEmpty(JsonObject object, String key) {
        return getAsJsonObjectDef(object, key, new JsonObject());
    }

    public static @Nullable JsonArray getAsJsonArray(JsonObject object, String key) {
        JsonElement element = object.get(key);
        if (element == null) return null;
        if (!element.isJsonArray()) return null;
        return element.getAsJsonArray();
    }
    public static Optional<JsonArray> getAsJsonArrayOp(JsonObject object, String key) {
        return Optional.ofNullable(getAsJsonArray(object, key));
    }
    public static JsonArray getAsJsonArrayDef(JsonObject object, String key, JsonArray def) {
        return getAsJsonArrayOp(object, key).orElse(def);
    }
    public static JsonArray getAsJsonArrayDefEmpty(JsonObject object, String key) {
        return getAsJsonArrayDef(object, key, new JsonArray());
    }

    public static @Nullable JsonPrimitive getAsPrimitive(JsonObject object, String key) {
        JsonElement element = object.get(key);
        if (element == null) return null;
        if (!element.isJsonPrimitive()) return null;
        return element.getAsJsonPrimitive();
    }
    public static Optional<JsonPrimitive> getAsPrimitiveOp(JsonObject object, String key) {
        return Optional.ofNullable(getAsPrimitive(object, key));
    }

    public static @Nullable String getAsString(JsonObject object, String key) {
        JsonPrimitive primitive = getAsPrimitive(object, key);
        if (primitive == null) return null;
        if (!primitive.isString()) return null;
        return primitive.getAsString();
    }
    public static Optional<String> getAsStringOp(JsonObject object, String key) {
        return Optional.ofNullable(getAsString(object, key));
    }
    public static String getAsStringDef(JsonObject object, String key, String def) {
        return getAsStringOp(object, key).orElse(def);
    }

    public static @Nullable Double getAsDouble(JsonObject object, String key) {
        JsonPrimitive primitive = getAsPrimitive(object, key);
        if (primitive == null) return null;
        if (!primitive.isNumber()) return null;
        return primitive.getAsDouble();
    }
    public static Optional<Double> getAsDoubleOp(JsonObject object, String key) {
        return Optional.ofNullable(getAsDouble(object, key));
    }
    public static double getAsDoubleDef(JsonObject object, String key, double def) {
        return getAsDoubleOp(object, key).orElse(def);
    }

    public static @Nullable Integer getAsInt(JsonObject object, String key) {
        JsonPrimitive primitive = getAsPrimitive(object, key);
        if (primitive == null) return null;
        if (!primitive.isNumber()) return null;
        return primitive.getAsInt();
    }
    public static Optional<Integer> getAsIntOp(JsonObject object, String key) {
        return Optional.ofNullable(getAsInt(object, key));
    }
    public static int getAsIntDef(JsonObject object, String key, int def) {
        return getAsIntOp(object, key).orElse(def);
    }

    public static @Nullable Boolean getAsBoolean(JsonObject object, String key) {
        JsonPrimitive primitive = getAsPrimitive(object, key);
        if (primitive == null) return null;
        if (!primitive.isBoolean()) return null;
        return primitive.getAsBoolean();
    }
    public static Optional<Boolean> getAsBooleanOp(JsonObject object, String key) {
        return Optional.ofNullable(getAsBoolean(object, key));
    }
    public static boolean getAsBooleanDef(JsonObject object, String key, boolean def) {
        return getAsBooleanOp(object, key).orElse(def);
    }
}

package com.unicar.util.router;

import com.google.gson.Gson;
import spark.Request;

public class BodyParser {
    public static <T> T bodyTyped(Request request, Class<T> tClass) {
        final Gson gson = new Gson();
        return gson.fromJson(request.body(), tClass);
    }
}

package extensions.spark.Request;

import com.google.gson.Gson;
import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.This;
import spark.Request;

@Extension
public class RequestExtension {

    public static <T> T bodyTyped(@This Request thiz, Class<T> tClass) {
        final Gson gson = new Gson();
        return gson.fromJson(thiz.body(), tClass);
    }
}

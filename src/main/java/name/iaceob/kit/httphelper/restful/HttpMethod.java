package name.iaceob.kit.httphelper.restful;

/**
 * Created by cox on 2015/11/27.
 */
public enum HttpMethod {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE");
    private final String method;
    HttpMethod(String method) {
        this.method = method;
    }
    public String getMethod() {
        return this.method;
    }
}

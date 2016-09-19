package name.iaceob.kit.httphelper.restful;

/**
 * Created by cox on 2015/11/27.
 */
public enum HttpMethod {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    PATCH("PATCH"),
    DELETE("DELETE"),
    COPY("COPY"),
    HEAD("HEAD"),
    OPTIONS("OPTIONS"),
    LINK("LINK"),
    UNLINK("UNLINK"),
    PURGE("PURGE"),
    LOCK("LOCK"),
    UNLOCK("UNLOCK"),
    PROPFIND("PROPFIND"),
    VIEW("VIEW"),
    ;
    private final String name;
    HttpMethod(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

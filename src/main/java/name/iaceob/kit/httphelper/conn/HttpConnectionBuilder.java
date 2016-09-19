package name.iaceob.kit.httphelper.conn;

import name.iaceob.kit.httphelper.config.HttpConfig;
import name.iaceob.kit.httphelper.entity.ProxyEntity;

/**
 * Created by cox on 2015/11/27.
 */
@Deprecated
public class HttpConnectionBuilder {

    public static HttpConnectionBuilder create() {
        return new HttpConnectionBuilder();
    }

    private ProxyEntity proxy;
    private HttpConfig config;

    public HttpConfig getConfig() {
        return this.config;
    }

    public HttpConnectionBuilder setConfig(HttpConfig config) {
        this.config = config;
        return this;
    }

    public ProxyEntity getProxy() {
        return this.proxy;
    }

    public HttpConnectionBuilder setProxy(ProxyEntity proxy) {
        this.proxy = proxy;
        return this;
    }

    // =====
    public HttpConnection build() {
        return new HttpConnection(this.config, this.proxy);
    }


}

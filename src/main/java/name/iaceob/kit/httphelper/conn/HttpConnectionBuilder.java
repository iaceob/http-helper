package name.iaceob.kit.httphelper.conn;

import name.iaceob.kit.httphelper.auth.ProxyAuthenticator;
import name.iaceob.kit.httphelper.entity.ProxyEntity;
import org.apache.http.client.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.*;

/**
 * Created by cox on 2015/11/27.
 */
public class HttpConnectionBuilder {

    private static final Logger log = LoggerFactory.getLogger(HttpConnectionBuilder.class);

    private void d() throws IOException {
        HttpURLConnection conn = null;
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.0.0.1", 8080));
        Authenticator.setDefault(new ProxyAuthenticator("zx", "pwd"));
        conn = (HttpURLConnection) new java.net.URL("").openConnection(proxy);
        conn.setRequestProperty("", "");
    }

    private ProxyEntity proxy;
    private HttpClient config;

    public static HttpConnectionBuilder create() {
        return new HttpConnectionBuilder();
    }

    public HttpClient getConfig() {
        return config;
    }

    public void setConfig(HttpClient config) {
        this.config = config;
    }

    public ProxyEntity getProxy() {
        return proxy;
    }

    public void setProxy(ProxyEntity proxy) {
        this.proxy = proxy;
    }



    // =====
    public HttpConnection build() {
        return null;
    }


}

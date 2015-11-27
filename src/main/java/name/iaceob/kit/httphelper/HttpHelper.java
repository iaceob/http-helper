package name.iaceob.kit.httphelper;

import name.iaceob.kit.httphelper.entity.ProxyEntity;
import name.iaceob.kit.httphelper.restful.HttpMethod;
import name.iaceob.kit.httphelper.trust.TrustAnyHostnameVerifier;
import name.iaceob.kit.httphelper.trust.TrustAnyTrustManager;

import javax.net.ssl.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.util.Map;

/**
 * Created by cox on 2015/11/27.
 */
public class HttpHelper {

    private HttpHelper() {}

    private SSLSocketFactory sslSocketFactory = initSSLSocketFactory();
    private TrustAnyHostnameVerifier trustAnyHostnameVerifier = new TrustAnyHostnameVerifier();

    private SSLSocketFactory initSSLSocketFactory() {
        try {
            TrustManager[] e = new TrustManager[]{new TrustAnyTrustManager()};
            SSLContext sslContext = SSLContext.getInstance("TLS", "SunJSSE");
            sslContext.init((KeyManager[])null, e, new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (Exception var2) {
            throw new RuntimeException(var2);
        }
    }

    private HttpURLConnection genHttpConnection(String url, HttpMethod method, Map<String, String> headers,
                                                ProxyEntity proxy) throws IOException {
        URL _url = new URL(url);
        HttpURLConnection conn = (HttpURLConnection)_url.openConnection();
        if(conn instanceof HttpsURLConnection) {
            ((HttpsURLConnection)conn).setSSLSocketFactory(sslSocketFactory);
            ((HttpsURLConnection)conn).setHostnameVerifier(trustAnyHostnameVerifier);
        }

        conn.setRequestMethod(method.getMethod());
        conn.setDoOutput(true);
        conn.setDoInput(true);

    }


}

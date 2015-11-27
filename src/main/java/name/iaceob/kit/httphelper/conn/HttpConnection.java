package name.iaceob.kit.httphelper.conn;

import name.iaceob.kit.httphelper.auth.ProxyAuthenticator;
import name.iaceob.kit.httphelper.common.HttpConst;
import name.iaceob.kit.httphelper.common.IdentifyCharset;
import name.iaceob.kit.httphelper.config.HttpConfig;
import name.iaceob.kit.httphelper.entity.HttpEntity;
import name.iaceob.kit.httphelper.entity.ProxyEntity;
import name.iaceob.kit.httphelper.helper.HttpReq;
import name.iaceob.kit.httphelper.trust.TrustAnyHostnameVerifier;
import name.iaceob.kit.httphelper.trust.TrustAnyTrustManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.Iterator;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/**
 * Created by cox on 2015/11/27.
 */
public class HttpConnection {

    private static final Logger log = LoggerFactory.getLogger(HttpConnection.class);
    private static final SSLSocketFactory sslSocketFactory = initSSLSocketFactory();
    private static final TrustAnyHostnameVerifier trustAnyHostnameVerifier = new TrustAnyHostnameVerifier();

    private HttpConfig config;
    private ProxyEntity proxy;


    public HttpConnection(HttpConfig config, ProxyEntity proxy) {
        this.config = config;
        this.proxy = proxy;
    }

    private static SSLSocketFactory initSSLSocketFactory() {
        try {
            TrustManager[] e = new TrustManager[]{new TrustAnyTrustManager()};
            SSLContext sslContext = SSLContext.getInstance("TLS", "SunJSSE");
            sslContext.init((KeyManager[]) null, e, new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (Exception var2) {
            throw new RuntimeException(var2);
        }
    }

    private HttpURLConnection getConnection(HttpReq req) throws IOException {
        Proxy proxy = null;
        if (this.proxy != null) {
            proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(this.proxy.getHost(), this.proxy.getPort()));
            if (this.proxy.getAccount() != null && !"".equals(this.proxy.getAccount()) &&
                    this.proxy.getPassword() != null && !"".equals(this.proxy.getPassword())) {
                Authenticator.setDefault(new ProxyAuthenticator(this.proxy.getAccount(), this.proxy.getPassword()));
            }
        }

        URL _url = new URL(this.buildUrlWithQueryString(req.getUrl(), req.getParas(), this.config.getCharset()));
        HttpURLConnection conn = proxy == null ? (HttpURLConnection) _url.openConnection() : (HttpURLConnection) _url.openConnection(proxy);
        if (conn instanceof HttpsURLConnection) {
            ((HttpsURLConnection) conn).setSSLSocketFactory(sslSocketFactory);
            ((HttpsURLConnection) conn).setHostnameVerifier(trustAnyHostnameVerifier);
        }

        conn.setRequestMethod(req.getMethod().getMethod());
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setConnectTimeout(HttpConst.DEF_TIMEOUT);
        conn.setReadTimeout(HttpConst.DEF_SOTIMEOUT);
        if (this.config != null) {
            conn.setConnectTimeout(this.config.getConnectTimeout() != null ? this.config.getConnectTimeout() : HttpConst.DEF_TIMEOUT);
            conn.setReadTimeout(this.config.getReadTimeout() != null ? this.config.getReadTimeout() : HttpConst.DEF_SOTIMEOUT);
        }
        if (req.getHeaders() != null && !req.getHeaders().isEmpty()) {
            Iterator i$ = req.getHeaders().entrySet().iterator();

            while (i$.hasNext()) {
                Map.Entry entry = (Map.Entry) i$.next();
                conn.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
            }
        }
        return conn;
    }


    private String buildUrlWithQueryString(String url, Map<String, String> queryParas, Charset charset) {
        if (queryParas != null && !queryParas.isEmpty()) {
            StringBuilder sb = new StringBuilder(url);
            boolean isFirst;
            if (!url.contains("?")) {
                isFirst = true;
                sb.append("?");
            } else {
                isFirst = false;
            }

            String key;
            String value;
            for (Iterator i$ = queryParas.entrySet().iterator(); i$.hasNext(); sb.append(key).append("=").append(value)) {
                Map.Entry entry = (Map.Entry) i$.next();
                if (isFirst) {
                    isFirst = false;
                } else {
                    sb.append("&");
                }

                key = (String) entry.getKey();
                value = (String) entry.getValue();
                if (value != null && !"".equals(value)) {
                    try {
                        value = URLEncoder.encode(value, charset.displayName());
                    } catch (UnsupportedEncodingException var9) {
                        throw new RuntimeException(var9);
                    }
                }
            }

            return sb.toString();
        } else {
            return url;
        }
    }

    private String readResponseString(HttpURLConnection conn, Charset charset) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader e = null;
        charset = this.config.getAutoDetectCharset() ? HttpConst.DEF_CONTENT_CHARSET : charset;

        try {
            inputStream = conn.getInputStream();

            if (conn.getHeaderField("Content-Encoding")!=null && conn.getHeaderField("Content-Encoding").equals("gzip")){
                e = new BufferedReader(new InputStreamReader(new GZIPInputStream(inputStream), charset));
            } else {
                e = new BufferedReader(new InputStreamReader(inputStream, charset));
            }
            String line = null;

            while((line = e.readLine()) != null) {
                sb.append(line).append("\n");
            }

            String var5 = sb.toString();
            return var5;
        } catch (Exception var14) {
            throw new RuntimeException(var14);
        } finally {
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException var13) {
                    var13.printStackTrace();
                }
            }
            if (e!=null) {
                try {
                    e.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

        }
    }

    public HttpEntity exec(HttpReq req) throws IOException {
        HttpURLConnection conn = null;
        HttpEntity entity = new HttpEntity();
        try {
            String e;
            conn = this.getConnection(req);
            conn.connect();
            e = this.readResponseString(conn, this.config.getCharset());
            if (this.config.getAutoDetectCharset()) {
                e = new String(e.getBytes(HttpConst.DEF_CONTENT_CHARSET), IdentifyCharset.identify(e));
            }
            // conn.gethe
            entity.setHtml(e);
        } catch (Exception var8) {
            throw new RuntimeException(var8);
        } finally {
            if(conn != null) {
                conn.disconnect();
            }
        }
        return entity;
    }

}

package name.iaceob.kit.httphelper.conn;

import name.iaceob.kit.httphelper.common.HttpConst;
import name.iaceob.kit.httphelper.config.HttpConf;
import name.iaceob.kit.httphelper.data.HttpData;
import name.iaceob.kit.httphelper.http.HttpStatus;
import name.iaceob.kit.httphelper.proxy.HttpProxy;
import name.iaceob.kit.httphelper.http.HttpHeader;
import name.iaceob.kit.httphelper.http.HttpMap;
import name.iaceob.kit.httphelper.http.HttpPara;
import name.iaceob.kit.httphelper.req.HttpRequest;
import name.iaceob.kit.httphelper.restful.HttpMethod;
import name.iaceob.kit.httphelper.trust.TrustAnyHostnameVerifier;
import name.iaceob.kit.httphelper.trust.TrustAnyTrustManager;

import javax.net.ssl.*;
import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;


/**
 * Created by iaceob on 2016/9/19.
 */
public class HttpConn {

    // private static final Logger log = LoggerFactory.getLogger(HttpConn.class);
    private static final SSLSocketFactory sslSocketFactory = initSSLSocketFactory();
    private static final TrustAnyHostnameVerifier trustAnyHostnameVerifier = new TrustAnyHostnameVerifier();

    private HttpConf conf;

    public HttpConn(HttpConf conf) {
        this.conf = conf;
    }


    private static SSLSocketFactory initSSLSocketFactory() {
        try {
            TrustManager[] e = new TrustManager[]{new TrustAnyTrustManager()};
            // SSLContext sslContext = SSLContext.getInstance("TLS", "SunJSSE");
            SSLContext sslContext = SSLContext.getInstance("TLSv1.1", "SunJSSE");
            sslContext.init((KeyManager[]) null, e, new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (Exception var2) {
            throw new RuntimeException(var2);
        }
    }

    /**
     * 构造 GET 链接
     *
     * @param url     链接
     * @param paras   参数
     * @param charset 编码
     * @return String
     */
    private String buildUrlWithQueryString(String url, List<HttpPara> paras, Charset charset) {
        if (paras == null || paras.isEmpty()) return url;
        StringBuilder sb = new StringBuilder(url);
        Boolean isFirst = !url.contains("?");
        if (isFirst)
            sb.append('?');

        String key;
        String value;
        for (HttpPara para : paras) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append('&');
            }
            key = para.getKey();
            value = para.getValue().toString();
            if (value == null || "".equals(value)) continue;
            try {
                value = URLEncoder.encode(value, charset.displayName());
            } catch (UnsupportedEncodingException var9) {
                throw new RuntimeException(var9);
            }
            sb.append(key).append('=').append(value);
        }
        return sb.toString();
    }

//    private String buildUrlWithQueryString(String url, Map<String, String> queryParas, Charset charset) {
//        if (queryParas != null && !queryParas.isEmpty()) {
//            StringBuilder sb = new StringBuilder(url);
//            boolean isFirst;
//            if (!url.contains("?")) {
//                isFirst = true;
//                sb.append("?");
//            } else {
//                isFirst = false;
//            }
//
//            String key;
//            String value;
//            for (Iterator i$ = queryParas.entrySet().iterator(); i$.hasNext(); sb.append(key).append("=").append(value)) {
//                Map.Entry entry = (Map.Entry) i$.next();
//                if (isFirst) {
//                    isFirst = false;
//                } else {
//                    sb.append("&");
//                }
//
//                key = (String) entry.getKey();
//                value = (String) entry.getValue();
//                if (value != null && !"".equals(value)) {
//                    try {
//                        value = URLEncoder.encode(value, charset.displayName());
//                    } catch (UnsupportedEncodingException var9) {
//                        throw new RuntimeException(var9);
//                    }
//                }
//            }
//
//            return sb.toString();
//        } else {
//            return url;
//        }
//    }

    private String readResponseString(InputStream inputStream, Boolean gzip, Charset charset) {
        StringBuilder sb = new StringBuilder();
        BufferedReader e = null;
        // charset = this.config.getAutoDetectCharset() ? HttpConst.DEF_CONTENT_CHARSET : charset;
        charset = this.conf.getAutoDetectCharset() ? HttpConst.DEF_CONTENT_CHARSET : charset;

        try {
            e = gzip ?
                    new BufferedReader(new InputStreamReader(new GZIPInputStream(inputStream), charset)) :
                    new BufferedReader(new InputStreamReader(inputStream, charset));

            String line = null;
            while ((line = e.readLine()) != null) {
                sb.append(line).append("\n");
            }

            return sb.toString();
        } catch (Exception var14) {
            throw new RuntimeException(var14);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException var13) {
                    var13.printStackTrace();
                }
            }
            if (e != null) {
                try {
                    e.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

        }
    }

    private HttpURLConnection getConn(HttpRequest request) throws IOException {

        /*
        获取代理信息
         */
        HttpProxy httpProxy = this.conf.getProxy();
        Proxy proxy = null;
        if (httpProxy != null)
            proxy = this.conf.getProxy().gen();

        /*
        构建连接
         */
        URL _url = new URL(this.buildUrlWithQueryString(request.getUrl(), request.getParas(), this.conf.getCharset()));
        /*
        根据是否有代理情况构建 HttpURLConnection
         */
        HttpURLConnection conn = proxy == null ? (HttpURLConnection) _url.openConnection() : (HttpURLConnection) _url.openConnection(proxy);

        /*
        是否 SSL
         */
        if (conn instanceof HttpsURLConnection) {
            ((HttpsURLConnection) conn).setSSLSocketFactory(sslSocketFactory);
            ((HttpsURLConnection) conn).setHostnameVerifier(trustAnyHostnameVerifier);
        }

        /*
        请求相关设定
         */
        conn.setRequestMethod(request.getMethod().getName());
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setConnectTimeout(this.conf.getConnectTimeout());
        conn.setReadTimeout(this.conf.getReadTimeout());

        /*
        没有请求头直接返回连接对象
         */
        if (request.getHeaders() == null || request.getHeaders().isEmpty())
            return conn;

        /*
        将请求头写入到连接中
         */
        Iterator i$ = HttpMap.toMap(request.getHeaders()).entrySet().iterator();

        while (i$.hasNext()) {
            Map.Entry entry = (Map.Entry) i$.next();
            conn.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
        }
        return conn;
    }

    public HttpData exec(HttpRequest request) {
        HttpURLConnection conn = null;
        try {
            String e;
            conn = this.getConn(request);
            conn.connect();

            if (request.getMethod() != HttpMethod.GET && request.getData() != null) {
                OutputStream os = conn.getOutputStream();
                os.write(request.getData().getBytes(this.conf.getCharset()));
                os.flush();
                os.close();
            }

            Integer responseCode = conn.getResponseCode();
            InputStream inputStream = responseCode >= HttpStatus.SC_BAD_REQUEST ? conn.getErrorStream() : conn.getInputStream();

            e = this.readResponseString(inputStream,
                    conn.getHeaderField("Content-Encoding") != null && conn.getHeaderField("Content-Encoding").equals("gzip"),
                    this.conf.getCharset());

            Charset charset = this.conf.getCharset();
            if (this.conf.getAutoDetectCharset()) {
                // TODO 编码识别
                // charset = IdentifyCharset.identify(e, conn.getHeaderField(HttpConst.CONTENT_TYPE));
                e = new String(e.getBytes(HttpConst.DEF_CONTENT_CHARSET), charset);
            }
            URL respUrl = conn.getURL();

            // log.debug("URL: {}", respUrl);
            // log.debug("Content: {}", e);

            return this.genData(responseCode, respUrl, conn, charset, e);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    private HttpData genData(Integer responseCode, URL responseUrl, URLConnection conn, Charset charset, String content) {
        HttpData data = new HttpData();
        data.setUrl(responseUrl.toString());
        data.setContent(content);
        data.setResponseCode(responseCode);
        data.setUri(responseUrl.getPath());
        data.setDomain(responseUrl.getHost());
        data.setProtocol(responseUrl.getProtocol());
        data.setHost(responseUrl.getProtocol() + "://" + responseUrl.getHost());
        data.setPath(responseUrl.getPath());
        data.setCharset(charset);

        List<HttpHeader> headers = new ArrayList<>();
        List<HttpCookie> cks = new ArrayList<>();

        Map<String, List<String>> respHeaders = conn.getHeaderFields();
        System.out.println(respHeaders.toString());
        List<String> hvals;
        for (String key : respHeaders.keySet()) {
            if (key == null || "".equals(key))
                continue;
            hvals = respHeaders.get(key);
            // 如果头信息只有一个, 则返回单个头信息插入到 HttpData
            if (hvals.size() == 1) {
                headers.add(new HttpHeader(key, hvals.get(0)));
                continue;
            }
            // Cookie 的单独处理
            hvals.stream().filter(hval -> key.equals(HttpConst.SET_COOKIE)).forEach(hval -> {
                cks.addAll(HttpCookie.parse(hval));
            });
            // 其他的(包括 Cookie)多个值 Header 参数直接写入到头对象中
            headers.addAll(hvals.stream().map(hval -> new HttpHeader(key, hval)).collect(Collectors.toList()));
        }


        data.setHeaders(headers.toArray(new HttpHeader[headers.size()]));
        data.setCookie(cks.toArray(new HttpCookie[cks.size()]));
        return data;
    }


    @Override
    public String toString() {
        return "HttpConn{" +
                "conf=" + conf +
                '}';
    }
}

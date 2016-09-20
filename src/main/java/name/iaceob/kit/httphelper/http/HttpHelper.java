package name.iaceob.kit.httphelper.http;

import name.iaceob.kit.httphelper.common.HttpConst;
import name.iaceob.kit.httphelper.config.HttpConf;
import name.iaceob.kit.httphelper.conn.HttpConn;
import name.iaceob.kit.httphelper.data.HttpData;
import name.iaceob.kit.httphelper.kit.Http;
import name.iaceob.kit.httphelper.proxy.HttpProxy;
import name.iaceob.kit.httphelper.req.HttpRequest;
import name.iaceob.kit.httphelper.restful.HttpMethod;

import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by iaceob on 2016/9/20.
 */
public class HttpHelper implements Http {

    public HttpHelper() {
    }

    public static HttpHelper instance() {
        return new HttpHelper();
    }

    @Override
    public HttpData connect(HttpMethod method, String url, List<HttpPara> paras, String data, List<HttpHeader> headers, Charset charset, HttpProxy proxy) {
        if (method == null)
            throw new IllegalArgumentException("Method can not be null");
        if (url == null || "".equals(url))
            throw new IllegalArgumentException("Url can not be null");

        HttpRequest request = new HttpRequest(url, method);
        HttpConf conf = new HttpConf();

        if (paras != null && !paras.isEmpty())
            for (HttpPara para : paras)
                request.setParas(para);

        if (data != null && !"".equals(data))
            request.setData(data);

        if (headers != null && !headers.isEmpty())
            for (HttpHeader header : headers)
                request.setHeader(header);

        if (charset == null)
            charset = HttpConst.DEF_CHARSET;
        conf.setCharset(charset);

        if (proxy != null)
            conf.setProxy(proxy);

        conf.setConnectTimeout(HttpConst.DEF_TIMEOUT);
        conf.setReadTimeout(HttpConst.DEF_SOTIMEOUT);
        conf.setAutoDetectCharset(false);

        HttpConn conn = new HttpConn(conf);
        return conn.exec(request);
    }

    @Override
    public HttpData connect(HttpMethod method, String url, List<HttpPara> paras, List<HttpHeader> headers, Charset charset) {
        return this.connect(method, url, paras, null, headers, charset, null);
    }

    @Override
    public HttpData connect(HttpMethod method, String url, List<HttpPara> paras, List<HttpHeader> headers) {
        return this.connect(method, url, paras, null, headers, null, null);
    }

    @Override
    public HttpData connect(HttpMethod method, String url, List<HttpHeader> headers) {
        return this.connect(method, url, null, null, headers, null, null);
    }

    @Override
    public HttpData connect(HttpMethod method, String url) {
        return this.connect(method, url, null, null, null, null, null);
    }

    @Override
    public HttpData get(String url, List<HttpPara> paras, List<HttpHeader> headers, Charset charset, HttpProxy proxy) {
        return this.connect(HttpMethod.GET, url, paras, null, headers, charset, proxy);
    }

    @Override
    public HttpData get(String url, List<HttpPara> paras, List<HttpHeader> headers, Charset charset) {
        return this.connect(HttpMethod.GET, url, paras, null, headers, charset, null);
    }

    @Override
    public HttpData get(String url, List<HttpPara> paras, List<HttpHeader> headers) {
        return this.connect(HttpMethod.GET, url, paras, null, headers, null, null);
    }

    @Override
    public HttpData get(String url, List<HttpHeader> headers) {
        return this.connect(HttpMethod.GET, url, null, null, headers, null, null);
    }

    @Override
    public HttpData get(String url) {
        return this.connect(HttpMethod.GET, url, null, null, null, null, null);
    }

    @Override
    public HttpData post(String url, List<HttpPara> paras, String data, List<HttpHeader> headers, Charset charset, HttpProxy proxy) {
        return this.connect(HttpMethod.POST, url, paras, data, headers, charset, proxy);
    }

    @Override
    public HttpData post(String url, String data, List<HttpHeader> headers, Charset charset) {
        return this.connect(HttpMethod.POST, url, null, data, headers, charset, null);
    }

    @Override
    public HttpData post(String url, String data, List<HttpHeader> headers) {
        return this.connect(HttpMethod.POST, url, null, data, headers, null, null);
    }

    @Override
    public HttpData post(String url, String data) {
        return this.connect(HttpMethod.POST, url, null, data, null, null, null);
    }

    @Override
    public HttpData post(String url, List<HttpHeader> headers) {
        return this.connect(HttpMethod.POST, url, null, null, headers, null, null);
    }

    @Override
    public HttpData post(String url) {
        return this.connect(HttpMethod.POST, url, null, null, null, null, null);
    }

    @Override
    public HttpData put(String url, List<HttpPara> paras, String data, List<HttpHeader> headers, Charset charset, HttpProxy proxy) {
        return this.connect(HttpMethod.PUT, url, paras, data, headers, charset, proxy);
    }

    @Override
    public HttpData put(String url, List<HttpPara> paras, String data, List<HttpHeader> headers, Charset charset) {
        return this.connect(HttpMethod.PUT, url, paras, data, headers, charset, null);
    }

    @Override
    public HttpData put(String url, List<HttpPara> paras, String data, List<HttpHeader> headers) {
        return this.connect(HttpMethod.PUT, url, paras, data, headers, null, null);
    }

    @Override
    public HttpData put(String url, List<HttpPara> paras, String data) {
        return this.connect(HttpMethod.PUT, url, paras, data, null, null, null);
    }

    @Override
    public HttpData put(String url, List<HttpPara> paras, List<HttpHeader> headers) {
        return this.connect(HttpMethod.PUT, url, paras, null, headers, null, null);
    }

    @Override
    public HttpData put(String url, List<HttpHeader> headers) {
        return this.connect(HttpMethod.PUT, url, null, null, headers, null, null);
    }

    @Override
    public HttpData put(String url, String data, List<HttpHeader> headers, Charset charset) {
        return this.connect(HttpMethod.PUT, url, null, data, headers, charset, null);
    }

    @Override
    public HttpData put(String url, String data, List<HttpHeader> headers) {
        return this.connect(HttpMethod.PUT, url, null, data, headers, null, null);
    }

    @Override
    public HttpData put(String url, String data) {
        return this.connect(HttpMethod.PUT, url, null, data, null, null, null);
    }

    @Override
    public HttpData put(String url) {
        return this.connect(HttpMethod.PUT, url, null, null, null, null, null);
    }

    @Override
    public HttpData delete(String url, List<HttpPara> paras, String data, List<HttpHeader> headers, Charset charset, HttpProxy proxy) {
        return this.connect(HttpMethod.DELETE, url, paras, data, headers, charset, proxy);
    }

    @Override
    public HttpData delete(String url, List<HttpPara> paras, String data, List<HttpHeader> headers, Charset charset) {
        return this.connect(HttpMethod.DELETE, url, paras, data, headers, charset, null);
    }

    @Override
    public HttpData delete(String url, List<HttpPara> paras, String data, List<HttpHeader> headers) {
        return this.connect(HttpMethod.DELETE, url, paras, data, headers, null, null);
    }

    @Override
    public HttpData delete(String url, List<HttpPara> paras, String data) {
        return this.connect(HttpMethod.DELETE, url, paras, data, null, null, null);
    }

    @Override
    public HttpData delete(String url, List<HttpPara> paras, List<HttpHeader> headers) {
        return this.connect(HttpMethod.DELETE, url, paras, null, headers, null, null);
    }

    @Override
    public HttpData delete(String url, List<HttpHeader> headers) {
        return this.connect(HttpMethod.DELETE, url, null, null, headers, null, null);
    }

    @Override
    public HttpData delete(String url, String data, List<HttpHeader> headers, Charset charset) {
        return this.connect(HttpMethod.DELETE, url, null, data, headers, charset, null);
    }

    @Override
    public HttpData delete(String url, String data, List<HttpHeader> headers) {
        return this.connect(HttpMethod.DELETE, url, null, data, headers, null, null);
    }

    @Override
    public HttpData delete(String url, String data) {
        return this.connect(HttpMethod.DELETE, url, null, data, null, null, null);
    }

    @Override
    public HttpData delete(String url) {
        return this.connect(HttpMethod.DELETE, url, null, null, null, null, null);
    }
}

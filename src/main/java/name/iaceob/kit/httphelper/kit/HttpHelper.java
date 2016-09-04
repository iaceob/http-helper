package name.iaceob.kit.httphelper.kit;

import name.iaceob.kit.httphelper.common.HttpConst;
import name.iaceob.kit.httphelper.config.HttpConfig;
import name.iaceob.kit.httphelper.conn.HttpConnection;
import name.iaceob.kit.httphelper.conn.HttpConnectionBuilder;
import name.iaceob.kit.httphelper.entity.HttpEntity;
import name.iaceob.kit.httphelper.entity.ProxyEntity;
import name.iaceob.kit.httphelper.http.HttpReq;
import name.iaceob.kit.httphelper.restful.HttpMethod;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * Created by cox on 2015/11/27.
 */
public class HttpHelper {

    public static HttpEntity request(HttpMethod method, String url, Map<String, String> paras, String data, Map<String, String> header,
                                     ProxyEntity proxy, Charset charset, Integer connectTimeout, Integer readTimeout,
                                     Boolean autoDetectCharset) {

        HttpConfig config = HttpConfig.me().setConnectTimeout(connectTimeout)
                .setReadTimeout(readTimeout).setCharset(charset).setAutoDetectCharset(autoDetectCharset);
        HttpConnectionBuilder hb = HttpConnectionBuilder.create().setConfig(config);
        if (proxy != null) hb.setProxy(proxy);
        HttpConnection hc = hb.build();
        HttpReq req = new HttpReq(method, url);
        if (paras != null) req.setParas(paras);
        if (header != null) req.setHeader(header);
        if (data != null) req.setData(data);
        try {
            return hc.exec(req);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static HttpEntity request(HttpMethod method, String url, Map<String, String> paras, Map<String, String> header, Charset charset) {
        return request(method, url, paras, null, header, null, charset, HttpConst.DEF_TIMEOUT, HttpConst.DEF_SOTIMEOUT, false);
    }

    public static HttpEntity request(HttpMethod method, String url, Map<String, String> paras, String data, Map<String, String> header, Charset charset) {
        return request(method, url, paras, data, header, null, charset, HttpConst.DEF_TIMEOUT, HttpConst.DEF_SOTIMEOUT, false);
    }

    public static HttpEntity request(HttpMethod method, String url, Map<String, String> paras, String data) {
        return request(method, url, paras, data, null, null, HttpConst.DEF_CHARSET, HttpConst.DEF_TIMEOUT, HttpConst.DEF_SOTIMEOUT, false);
    }

    public static HttpEntity request(HttpMethod method, String url, Map<String, String> paras) {
        return request(method, url, paras, null, null, null, HttpConst.DEF_CHARSET, HttpConst.DEF_TIMEOUT, HttpConst.DEF_SOTIMEOUT, false);
    }

    public static HttpEntity request(HttpMethod method, String url) {
        return request(method, url, null, null, null, null, HttpConst.DEF_CHARSET, HttpConst.DEF_TIMEOUT, HttpConst.DEF_SOTIMEOUT, false);
    }

    public static HttpEntity get(String url, Map<String, String> paras, Map<String, String> header,
                                 ProxyEntity proxy, Charset charset, Integer connectTimeout,
                                 Integer readTimeout, Boolean autoDetectCharset) {
        return request(HttpMethod.GET, url, paras, null, header, proxy, charset, connectTimeout, readTimeout, autoDetectCharset);
    }

    public static HttpEntity post(String url, Map<String, String> paras, String data, Map<String, String> header,
                                  ProxyEntity proxy, Charset charset, Integer connectTimeout, Integer readTimeout,
                                  Boolean autoDetectCharset) {
        return request(HttpMethod.POST, url, paras, data, header, proxy, charset, connectTimeout, readTimeout, autoDetectCharset);
    }

    public static HttpEntity get(String url, Map<String, String> paras, Map<String, String> header,
                                 ProxyEntity proxy, Charset charset, Integer connectTimeout,
                                 Integer readTimeout) {
        return HttpHelper.get(url, paras, header, proxy, charset, connectTimeout, readTimeout, false);
    }

    public static HttpEntity get(String url, Map<String, String> paras, Map<String, String> header,
                                 ProxyEntity proxy, Charset charset) {
        return HttpHelper.get(url, paras, header, proxy, charset, HttpConst.DEF_TIMEOUT, HttpConst.DEF_SOTIMEOUT);
    }

    public static HttpEntity get(String url, Map<String, String> paras, Map<String, String> header, Charset charset) {
        return HttpHelper.get(url, paras, header, null, charset);
    }

    public static HttpEntity get(String url, Map<String, String> paras, Map<String, String> header, Boolean autoDetectCharset) {
        return HttpHelper.get(url, paras, header, null, null, HttpConst.DEF_TIMEOUT, HttpConst.DEF_SOTIMEOUT, autoDetectCharset);
    }

    public static HttpEntity get(String url, Map<String, String> paras, Charset charset) {
        return HttpHelper.get(url, paras, null, charset);
    }

    public static HttpEntity get(String url, Map<String, String> para, Boolean autoDetectCharset) {
        return HttpHelper.get(url, para, null, autoDetectCharset);
    }

    public static HttpEntity get(String url, Charset charset) {
        return HttpHelper.get(url, null, charset);
    }

    public static HttpEntity get(String url, Boolean autoDetectCharset) {
        return HttpHelper.get(url, null, autoDetectCharset);
    }

    public static HttpEntity get(String url) {
        return HttpHelper.get(url, HttpConst.DEF_CONTENT_CHARSET);
    }

    public static HttpEntity post(String url, Map<String, String> paras, String data, Map<String, String> header,
                                  ProxyEntity proxy, Charset charset, Boolean autoComplete) {
        return HttpHelper.post(url, paras, data, header, proxy, charset, HttpConst.DEF_TIMEOUT, HttpConst.DEF_SOTIMEOUT, autoComplete);
    }

    public static HttpEntity post(String url, Map<String, String> paras, String data, Map<String, String> header, Charset charset) {
        return HttpHelper.post(url, paras, data, header, null, charset, false);
    }

    public static HttpEntity post(String url, Map<String, String> paras, String data, Charset charset, Boolean autoDetectCharset) {
        return HttpHelper.post(url, paras, data, null, null, charset, autoDetectCharset);
    }

    public static HttpEntity post(String url, Map<String, String> paras, String data, Charset charset) {
        return HttpHelper.post(url, paras, data, null, charset);
    }

    public static HttpEntity post(String url, String data, ProxyEntity pe, Charset charset) {
        return HttpHelper.post(url, null, data, null, pe, charset, false);
    }


    public static HttpEntity post(String url, String data, Charset charset) {
        return HttpHelper.post(url, data, null, charset);
    }

    public static HttpEntity post(String url, String data) {
        return HttpHelper.post(url, data, null);
    }

}

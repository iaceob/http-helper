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
public class HttpKit {


    public static HttpEntity get(String url, Map<String, String> paras, Map<String, String> header,
                                 ProxyEntity proxy, Charset charset, Integer connectTimeout,
                                 Integer readTimeout) {
        try {
            HttpConfig config = HttpConfig.create.setConnectTimeout(connectTimeout)
                    .setReadTimeout(readTimeout).setCharset(charset);
            HttpConnectionBuilder hb = HttpConnectionBuilder.create().setConfig(config);
            if (proxy != null) hb.setProxy(proxy);
            HttpConnection hc = hb.build();
            HttpReq req = new HttpReq(HttpMethod.GET, url);
            if (paras!=null) req.setParas(paras);
            if (header!=null) req.setHeader(header);
            return hc.exec(req);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static HttpEntity post(String url, Map<String, String> paras, String data, Map<String, String> header,
                                  ProxyEntity proxy, Charset charset, Integer connectTimeout, Integer readTimeout) {
        try {
            HttpConfig config = HttpConfig.create.setConnectTimeout(connectTimeout)
                    .setReadTimeout(readTimeout).setCharset(charset);
            HttpConnectionBuilder hb = HttpConnectionBuilder.create().setConfig(config);
            if (proxy != null) hb.setProxy(proxy);
            HttpConnection hc = hb.build();
            HttpReq req = new HttpReq(HttpMethod.POST, url);
            if (paras!=null) req.setParas(paras);
            if (header!=null) req.setHeader(header);
            if (data!=null) req.setData(data);
            return hc.exec(req);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static HttpEntity get(String url, Map<String, String> paras, Map<String, String> header,
                                 ProxyEntity proxy) {
        return HttpKit.get(url, paras, header, proxy, HttpConst.DEF_CONTENT_CHARSET, HttpConst.DEF_TIMEOUT, HttpConst.DEF_SOTIMEOUT);
    }

    public static HttpEntity get(String url, Map<String, String> paras, Map<String, String> header) {
        return HttpKit.get(url, paras, header, null);
    }

    public static HttpEntity get(String url, Map<String, String> paras) {
        return HttpKit.get(url, paras, null);
    }

    public static HttpEntity get(String url, ProxyEntity pe, Charset charset) {
        return HttpKit.get(url, null, null, pe, charset, HttpConst.DEF_TIMEOUT, HttpConst.DEF_SOTIMEOUT);
    }

    public static HttpEntity get(String url, ProxyEntity pe) {
        return HttpKit.get(url, null, null, pe, null, HttpConst.DEF_TIMEOUT, HttpConst.DEF_SOTIMEOUT);
    }

    public static HttpEntity get(String url, Charset charset) {
        return HttpKit.get(url, null, null, null, charset, HttpConst.DEF_TIMEOUT, HttpConst.DEF_SOTIMEOUT);
    }

    public static HttpEntity get(String url) {
        return HttpKit.get(url, null, null, null, HttpConst.DEF_CONTENT_CHARSET, HttpConst.DEF_TIMEOUT, HttpConst.DEF_SOTIMEOUT);
    }

    public static HttpEntity post(String url, Map<String, String> paras, String data, Map<String, String> header,
                                  ProxyEntity proxy) {
        return HttpKit.post(url, paras, data, header, proxy, HttpConst.DEF_CONTENT_CHARSET, HttpConst.DEF_TIMEOUT, HttpConst.DEF_SOTIMEOUT);
    }

    public static HttpEntity post(String url, Map<String, String> paras, String data, Map<String, String> header) {
        return HttpKit.post(url, paras, data, header, null);
    }

    public static HttpEntity post(String url, Map<String, String> paras, String data) {
        return HttpKit.post(url, paras, data, null);
    }

    public static HttpEntity post(String url, String data, ProxyEntity pe, Charset charset) {
        return HttpKit.post(url, null, data, null, pe, charset, HttpConst.DEF_TIMEOUT, HttpConst.DEF_SOTIMEOUT);
    }

    public static HttpEntity post(String url, String data, ProxyEntity pe) {
        return HttpKit.post(url, null, data, null, pe, null, HttpConst.DEF_TIMEOUT, HttpConst.DEF_SOTIMEOUT);
    }

    public static HttpEntity post(String url, String data) {
        return HttpKit.post(url, null, data);
    }

}

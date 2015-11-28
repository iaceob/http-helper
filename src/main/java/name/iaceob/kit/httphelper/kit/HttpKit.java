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
                                 Integer readTimeout, Boolean autoDetectCharset) {
        try {
            HttpConfig config = HttpConfig.create.setConnectTimeout(connectTimeout)
                    .setReadTimeout(readTimeout).setCharset(charset).setAutoDetectCharset(autoDetectCharset);
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
                                  ProxyEntity proxy, Charset charset, Integer connectTimeout, Integer readTimeout,
                                  Boolean autoDetectCharset) {
        try {
            HttpConfig config = HttpConfig.create.setConnectTimeout(connectTimeout)
                    .setReadTimeout(readTimeout).setCharset(charset).setAutoDetectCharset(autoDetectCharset);
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
        return HttpKit.get(url, paras, header, proxy, HttpConst.DEF_CHARSET, HttpConst.DEF_TIMEOUT, HttpConst.DEF_SOTIMEOUT, false);
    }

    public static HttpEntity get(String url, Map<String, String> paras, Map<String, String> header) {
        return HttpKit.get(url, paras, header, null);
    }

    public static HttpEntity get(String url, Map<String, String> paras) {
        return HttpKit.get(url, paras, null);
    }

    public static HttpEntity get(String url) {
        return HttpKit.get(url, null);
    }

    public static HttpEntity post(String url, Map<String, String> paras, String data, Map<String, String> header,
                                  ProxyEntity proxy) {
        return HttpKit.post(url, paras, data, header, proxy, HttpConst.DEF_CHARSET, HttpConst.DEF_TIMEOUT, HttpConst.DEF_SOTIMEOUT, false);
    }

    public static HttpEntity post(String url, Map<String, String> paras, String data, Map<String, String> header) {
        return HttpKit.post(url, paras, data, header, null);
    }

    public static HttpEntity post(String url, Map<String, String> paras, String data) {
        return HttpKit.post(url, paras, data, null);
    }

    public static HttpEntity post(String url, String data) {
        return HttpKit.post(url, null, data);
    }

}

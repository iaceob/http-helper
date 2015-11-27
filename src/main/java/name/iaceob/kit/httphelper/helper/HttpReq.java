package name.iaceob.kit.httphelper.helper;

import name.iaceob.kit.httphelper.common.HttpConst;
import name.iaceob.kit.httphelper.restful.HttpMethod;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by iaceob on 2015/11/28.
 */
public class HttpReq {

    private HttpMethod method;
    private String url;
    private Map<String, String> headers;
    private Map<String, String> paras;
    private String data;

    public HttpReq(HttpMethod method, String url) {
        this.method = method;
        this.url = url;
        this.headers = new HashMap<String, String>();
        this.setDefaultHeards();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public Map<String, String> getParas() {
        return paras;
    }

    public void setParas(Map<String, String> paras) {
        this.paras = paras;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    // ======
    private void setDefaultHeards() {
        this.setHeader(HttpConst.USER_AGENT, HttpConst.DEF_USER_AGENT)
                .setHeader(HttpConst.ACCEPT_LANGUAGE, HttpConst.DEF_ACCEPT_LANGUAGE)
                .setHeader(HttpConst.ACCEPT_CHARSET, HttpConst.DEF_ACCEPT_CHARSET)
                .setHeader(HttpConst.ACCEPT, HttpConst.DEF_ACCEPT)
                .setHeader(HttpConst.CONNECTION, "keep-alive")
                .setHeader(HttpConst.ACCEPT_ENCODING, "x-gzip, gzip, deflate")
                .setHeader(HttpConst.CONTENT_TYPE, HttpConst.DEF_CONTENT_TYPE);
    }

    public HttpReq setHeader(String key, String value) {
        this.headers.put(key, value);
        return this;
    }

    public HttpReq setHeader(Map<String, String> headers) {
        Set<String> set = headers.keySet();
        for (String key : set)
            this.headers.put(key, headers.get(key));
        return this;
    }


}

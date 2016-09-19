package name.iaceob.kit.httphelper.req;

import name.iaceob.kit.httphelper.common.HttpConst;
import name.iaceob.kit.httphelper.restful.HttpMethod;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by iaceob on 2015/11/28.
 */
@Deprecated
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
        return this.url;
    }

    public HttpReq setUrl(String url) {
        this.url = url;
        return this;
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public HttpReq setHeaders(Map<String, String> headers) {
        if (headers==null) return this;
        for (String key : headers.keySet())
            this.headers.put(key, headers.get(key));
        return this;
    }

    public HttpMethod getMethod() {
        return this.method;
    }

    public HttpReq setMethod(HttpMethod method) {
        this.method = method;
        return this;
    }

    public Map<String, String> getParas() {
        return this.paras;
    }

    public HttpReq setParas(Map<String, String> paras) {
        this.paras = paras;
        return this;
    }

    public String getData() {
        return this.data;
    }

    public HttpReq setData(String data) {
        this.data = data;
        return this;
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


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(" {");
        sb.append("method: ").append(this.getMethod().getName()).append(", ");
        sb.append("url: ").append(this.getUrl()).append(", ");
        if (this.paras!=null) {
            sb.append("paras: {");
            for (String key : this.paras.keySet()) {
                sb.append(key).append(": ").append(this.paras.get(key)).append(", ");
            }
            sb.append("}, ");
        } else {
            sb.append("paras: null, ");
        }
        sb.append("data: ").append(this.getData());
        sb.append("}");
        return sb.toString();
    }

}

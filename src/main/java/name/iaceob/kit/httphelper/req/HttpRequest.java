package name.iaceob.kit.httphelper.req;

import name.iaceob.kit.httphelper.common.HttpConst;
import name.iaceob.kit.httphelper.http.HttpHeader;
import name.iaceob.kit.httphelper.http.HttpPara;
import name.iaceob.kit.httphelper.restful.HttpMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iaceob on 2016/9/19.
 */
public class HttpRequest {


    /**
     * 请求地址
     */
    private String url;
    /**
     * 请求类型
     */
    private HttpMethod method;
    /**
     * 请求头
     */
    private List<HttpHeader> headers;
    /**
     * 请求参数 GET 方式
     */
    private List<HttpPara> paras;
    /**
     * 请求 body 内容
     */
    private String data;

    public HttpRequest(String url, HttpMethod method) {
        this.url = url;
        this.method = method;
        this.headers = new ArrayList<>();
        this.paras = new ArrayList<>();
        this.setDefaultHeaders();
    }

    private void setDefaultHeaders() {
        this.headers.add(new HttpHeader(HttpConst.USER_AGENT, HttpConst.DEF_USER_AGENT));
        this.headers.add(new HttpHeader(HttpConst.ACCEPT_LANGUAGE, HttpConst.DEF_ACCEPT_LANGUAGE));
        this.headers.add(new HttpHeader(HttpConst.ACCEPT_CHARSET, HttpConst.DEF_ACCEPT_CHARSET));
        this.headers.add(new HttpHeader(HttpConst.ACCEPT, HttpConst.DEF_ACCEPT));
        this.headers.add(new HttpHeader(HttpConst.CONNECTION, "keep-alive"));
        this.headers.add(new HttpHeader(HttpConst.ACCEPT_ENCODING, "x-gzip, gzip, deflate"));
        this.headers.add(new HttpHeader(HttpConst.CONTENT_TYPE, HttpConst.DEF_CONTENT_TYPE));
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<HttpHeader> getHeaders() {
        return headers;
    }

    public HttpHeader getHeader(String key) {
        for (HttpHeader header : this.headers)
            if (header.getKey().equals(key))
                return header;
        return null;
    }

    private Boolean exists(String key) {
        return this.getHeader(key) != null;
    }

    private void delHeader(HttpHeader header) {
        for (Integer i = this.headers.size(); i-- > 0; ) {
            if (!this.headers.get(i).getKey().equals(header.getKey())) continue;
            this.headers.remove(i.intValue());
        }
    }

    public void setHeader(HttpHeader header) {
        if (this.exists(header.getKey()))
            this.delHeader(header);
        this.headers.add(header);
    }

    public List<HttpPara> getParas() {
        return paras;
    }

    public HttpPara getPara(String key) {
        for (HttpPara para : this.paras) {
            if (!para.getKey().equals(key)) continue;
            return para;
        }
        return null;
    }

    public void setParas(HttpPara para) {
        this.paras.add(para);
    }


    @Override
    public String toString() {
        return "HttpRequest{" +
                "url='" + url + '\'' +
                ", method=" + method +
                ", headers=" + headers +
                ", paras=" + paras +
                ", data='" + data + '\'' +
                '}';
    }
}

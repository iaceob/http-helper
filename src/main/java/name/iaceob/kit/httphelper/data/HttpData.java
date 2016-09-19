package name.iaceob.kit.httphelper.data;

import name.iaceob.kit.httphelper.http.HttpHeader;

import java.io.Serializable;
import java.net.HttpCookie;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Created by iaceob on 2016/9/19.
 */
public class HttpData implements Serializable {

    private String url;
    private String protocol;
    private String domain;
    private String path;
    private String host;
    private String uri;
    private Integer responseCode;
    private Charset charset;
    private String content;
    private HttpCookie[] cookie;
    private HttpHeader[] headers;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public Charset getCharset() {
        return charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public HttpCookie[] getCookie() {
        return cookie;
    }

    public void setCookie(HttpCookie[] cookie) {
        this.cookie = cookie;
    }

    public HttpHeader[] getHeaders() {
        return headers;
    }

    public void setHeaders(HttpHeader[] headers) {
        this.headers = headers;
    }


    @Override
    public String toString() {
        String content = this.content;
        content = content != null ? content.substring(0, content.length() > 300 ? 300 : content.length()) : "";
        return "HttpData{" +
                "url='" + url + '\'' +
                ", protocol='" + protocol + '\'' +
                ", domain='" + domain + '\'' +
                ", path='" + path + '\'' +
                ", host='" + host + '\'' +
                ", uri='" + uri + '\'' +
                ", responseCode=" + responseCode +
                ", charset=" + charset +
                ", content='" + content + '\'' +
                ", cookie=" + Arrays.toString(cookie) +
                ", headers=" + Arrays.toString(headers) +
                '}';
    }

}

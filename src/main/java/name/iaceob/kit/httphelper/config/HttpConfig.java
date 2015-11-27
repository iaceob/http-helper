package name.iaceob.kit.httphelper.config;

import name.iaceob.kit.httphelper.common.HttpConst;

import java.nio.charset.Charset;

/**
 * Created by cox on 2015/11/27.
 */
public class HttpConfig {

    public static final HttpConfig create = new HttpConfig();

    private Integer connectTimeout;
    private Integer readTimeout;
    private Charset charset;
    private Boolean autoDetectCharset;

    public Boolean getAutoDetectCharset() {
        return this.autoDetectCharset == null ? false : this.autoDetectCharset;
    }

    public HttpConfig setAutoDetectCharset(Boolean autoDetectCharset) {
        this.autoDetectCharset = autoDetectCharset;
        return this;
    }

    public Integer getConnectTimeout() {
        return this.connectTimeout == null ? HttpConst.DEF_TIMEOUT : this.connectTimeout;
    }

    public HttpConfig setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
        return this;
    }

    public Integer getReadTimeout() {
        return this.readTimeout == null ? HttpConst.DEF_SOTIMEOUT : this.readTimeout;
    }

    public HttpConfig setReadTimeout(Integer readTimeout) {
        this.readTimeout = readTimeout;
        return this;
    }

    public Charset getCharset() {
        return charset == null ? HttpConst.DEF_CHARSET : charset;
    }

    public HttpConfig setCharset(Charset charset) {
        this.charset = charset;
        return this;
    }
}

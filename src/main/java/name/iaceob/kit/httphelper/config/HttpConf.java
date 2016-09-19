package name.iaceob.kit.httphelper.config;

import name.iaceob.kit.httphelper.common.HttpConst;
import name.iaceob.kit.httphelper.proxy.HttpProxy;

import java.nio.charset.Charset;

/**
 * Created by iaceob on 2016/9/19.
 */
public class HttpConf {

    /**
     * 代理实现
     */
    private HttpProxy proxy;
    /**
     * 连接超时时间
     */
    private Integer connectTimeout;
    /**
     * 读取资源超时时间
     */
    private Integer readTimeout;
    /**
     * 指定返回内容编码
     */
    private Charset charset;
    /**
     * 自动检测编码
     * TODO 自动检测编码尚未完善, 默认使用 UTF-8 编码, 预设 false
     */
    private Boolean autoDetectCharset;

    public HttpConf() {
        this.autoDetectCharset = false;
        this.charset = HttpConst.DEF_CHARSET;
        this.connectTimeout = HttpConst.DEF_TIMEOUT;
        this.readTimeout = HttpConst.DEF_SOTIMEOUT;
        this.proxy = null;
    }

    public HttpProxy getProxy() {
        return proxy;
    }

    public void setProxy(HttpProxy proxy) {
        this.proxy = proxy;
    }

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Integer getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(Integer readTimeout) {
        this.readTimeout = readTimeout;
    }

    public Charset getCharset() {
        return charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    public Boolean getAutoDetectCharset() {
        return autoDetectCharset;
    }

    public void setAutoDetectCharset(Boolean autoDetectCharset) {
        this.autoDetectCharset = autoDetectCharset;
    }
}

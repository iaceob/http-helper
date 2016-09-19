package name.iaceob.kit.httphelper.kit;

import name.iaceob.kit.httphelper.data.HttpData;
import name.iaceob.kit.httphelper.http.HttpHeader;
import name.iaceob.kit.httphelper.http.HttpPara;
import name.iaceob.kit.httphelper.proxy.HttpProxy;
import name.iaceob.kit.httphelper.restful.HttpMethod;

import java.nio.charset.Charset;
import java.util.List;

/**
 * Http 请求便捷使用方式接口, 具体实现有实现方决定
 * HttpHelper 中自带默认实现方式, 如果遇到特殊情况不建议使用此接口, 而是通过 HttpRequest 去构建请求连接
 */
public interface Http {

    /**
     * Http 请求
     *
     * @param method  方式
     * @param url     链接
     * @param paras   GET 参数
     * @param data    Request body
     * @param headers 头信息
     * @param charset 编码
     * @param proxy   代理
     * @return HttpData
     */
    HttpData connect(HttpMethod method, String url, List<HttpPara> paras, String data, List<HttpHeader> headers, Charset charset, HttpProxy proxy);

    HttpData connect(HttpMethod method, String url, List<HttpPara> paras, List<HttpHeader> headers, Charset charset);

    HttpData connect(HttpMethod method, String url, List<HttpPara> paras, List<HttpHeader> headers);

    HttpData connect(HttpMethod method, String url, List<HttpHeader> headers);

    HttpData connect(HttpMethod method, String url);

    HttpData get(String url, List<HttpPara> paras, List<HttpHeader> headers, Charset charset, HttpProxy proxy);

    HttpData get(String url, List<HttpPara> paras, List<HttpHeader> headers, Charset charset);

    HttpData get(String url, List<HttpPara> paras, List<HttpHeader> headers);

    HttpData get(String url, List<HttpPara> paras);

    HttpData get(String url);

    HttpData post(String url, List<HttpPara> paras, String data, List<HttpHeader> headers, Charset charset, HttpProxy proxy);

    HttpData post(String url, String data, List<HttpHeader> headers, Charset charset);

    HttpData post(String url, String data, List<HttpHeader> headers);

    HttpData post(String url, String data);

    HttpData post(String url);

    HttpData put(String url, List<HttpPara> paras, String data, List<HttpHeader> headers, Charset charset, HttpProxy proxy);

    HttpData put(String url, List<HttpPara> paras, String data, List<HttpHeader> headers, Charset charset);

    HttpData put(String url, List<HttpPara> paras, String data, List<HttpHeader> headers);

    HttpData put(String url, List<HttpPara> paras, String data);

    HttpData put(String url, String data);

    HttpData put(String url);

    HttpData delete(String url, List<HttpPara> paras, String data, List<HttpHeader> headers, Charset charset, HttpProxy proxy);

    HttpData delete(String url, List<HttpPara> paras, String data, List<HttpHeader> headers, Charset charset);

    HttpData delete(String url, List<HttpPara> paras, String data, List<HttpHeader> headers);

    HttpData delete(String url, List<HttpPara> paras, String data);

    HttpData delete(String url, String data);

    HttpData delete(String url);


}

package name.iaceob.httphelper;


import org.apache.http.*;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by iaceob on 2015/10/10.
 */
public class HttpHelper {


    private static final Logger log = LoggerFactory.getLogger(HttpHelper.class);

    private Map<String, String> requestHeaders;
    private Map<String, String> responseHeaders;
    private HttpProxy proxy;
    private Integer stat;
    private String html;
    private String url;
    private Boolean depth = false;
    private Integer depthCount = 3;
    private Integer depthIndex = 0;
    private String host;
    private String basePath;

    public HttpHelper() {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("User-Agent", HttpConst.USER_AGENT);
        headers.put("Accept-Language", HttpConst.ACCEPT_LANGUAGE);
        headers.put("Accept-Charset", HttpConst.ACCEPT_CHARSET);
        headers.put("Accept", HttpConst.ACCEPT);
        headers.put("Connection", "keep-alive");
        headers.put("Accept-Encoding", "x-gzip, gzip, deflate");
        this.requestHeaders = headers;
        this.responseHeaders = new HashMap<String, String>();
    }

    public HttpHelper(Map<String, String> headers) {
        this(headers, null);
    }

    public HttpHelper(Map<String, String> headers, HttpProxy proxy) {
        this.requestHeaders = headers;
        this.responseHeaders = new HashMap<String, String>();
        this.proxy = proxy;
    }

    private String parsetCookie(String cookie) {
        return cookie.split(";")[0];
    }
    private void setResponseHeaders(Map<String, String> responseHeaders) {
        this.responseHeaders = responseHeaders;
    }
    private void setResponseHeaders(Header[] hs) {
        for (Header h : hs) {
            String val = h.getValue();
            String rspck = this.getResponseHeaders()==null ? "" : this.getResponseHeaders().get("Set-Cookie");
            if ("Set-Cookie".equals(h.getName()))
                val = (rspck==null ? "" : (rspck + ";")) + this.parsetCookie(val);
            this.setResponseHeaders(h.getName(), val);
        }
    }
    private void setResponseHeaders(String key, String val) {
        this.responseHeaders.put(key, val);
    }
    private Boolean emptyResponseHeaders() {
        return this.getResponseHeaders()==null||this.getResponseHeaders().isEmpty();
    }
    private void setStat(Integer stat) {
        this.stat = stat;
    }
    private void setHtml(String html) {
        this.html = html;
    }
    private void setUrl(String url) {
        this.url = url;

        String reg = "(?<ssl>[http]+(s|))://(?<host>.*?)(/|\\s+|\\?)";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(url);
        StringBuilder sb = new StringBuilder();
        if (m.find()) {
            sb.append(m.group("ssl")).append("://").append(m.group("host"));
        } else {
            String var1 = url + "/";
            m = p.matcher(var1);
            if (m.find())
                sb.append(m.group("ssl")).append("://").append(m.group("host"));
        }
        this.host = sb.toString();

        Integer ed = 0;
        Integer lio = url.lastIndexOf("/");
        ed = lio==6||lio==7 ? url.length() : lio;
        String var2 = url.split("\\?")[0];
        this.basePath = var2.substring(0, ed);
    }
    public Map<String, String> getResponseHeaders() {
        return responseHeaders;
    }
    public Map<String, String> getRequestHeaders() {
        return requestHeaders;
    }
    public HttpHelper setRequestHeaders(Map<String, String> requestHeaders) {
        this.requestHeaders = requestHeaders;
        return this;
    }
    public HttpHelper setRequestHeaders(String key, String val) {
        this.requestHeaders.put(key, val);
        return this;
    }
    public HttpProxy getProxy() {
        return proxy;
    }
    public HttpHelper setProxy(HttpProxy proxy) {
        this.proxy = proxy;
        return this;
    }
    public void clear() {
        // this.requestHeaders = null;
        this.responseHeaders = new HashMap<String, String>();
        this.proxy = null;
        this.stat = null;
        this.html = null;
        this.url = null;
        this.depth = false;
        this.depthCount = 3;
        this.depthIndex = 0;
    }
    public Integer getStatCode() {
        return this.stat;
    }
    public String getHtml() {
        return this.html;
    }
    public String getUrl() {
        return this.url;
    }
    public Boolean getDepth() {
        return depth;
    }
    public HttpHelper setDepth(Boolean depth) {
        this.depth = depth;
        return this;
    }
    public Integer getDepthCount() {
        return depthCount;
    }
    public HttpHelper setDepthCount(Integer depthCount) {
        this.depthCount = depthCount;
        return this;
    }

    public String getHost() {
        return this.host;
    }
    public String getBasePath() {
        return this.basePath;
    }

    public String getLocation() {
        if (this.stat== HttpStatus.SC_MOVED_PERMANENTLY)
            return this.emptyResponseHeaders() ? null : this.getResponseHeaders().get(HttpConst.LOCATION);
        String refreshReg = "<META\\s+http-equiv=\"refresh\".*?URL=('|)(?<url>[^\"]+)";
        Pattern p = Pattern.compile(refreshReg, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(this.html);
        if (!m.find()) return null;
        String refu = m.group("url").replaceAll("'", "");
        if (refu.charAt(0)=='/') {
            refu = this.getHost() + refu;
            return refu;
        }
        if (!refu.substring(0, 4).equals("http")) {
            refu = this.getBasePath() + refu;
            return refu;
        }
        return refu;
    }
    public String getCookie() {
        return this.emptyResponseHeaders() ? null : this.getResponseHeaders().get(HttpConst.SETCOOKIE);
    }


    public String toString() {
        Field[] fs = this.getClass().getDeclaredFields();
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(" {");
        try {
            for (Integer i=0; i<fs.length; i++) {
                if ("log".equals(fs[i].getName())) continue;
                Object o = fs[i].get(this);
                sb.append(fs[i].getName()).append(":").append(o).append(i+1==fs.length ? "" : ",");
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        sb.append("}");
        return sb.toString();
    }


    private String buildUrlWithQueryString(String url, Map<String, String> paras, String charset) {
        if(paras != null && !paras.isEmpty()) {
            StringBuilder sb = new StringBuilder(url);
            boolean isFirst;
            if(url.indexOf("?") == -1) {
                isFirst = true;
                sb.append("?");
            } else {
                isFirst = false;
            }

            String key;
            String value;
            for(Iterator i$ = paras.entrySet().iterator(); i$.hasNext(); sb.append(key).append("=").append(value)) {
                Map.Entry entry = (Map.Entry)i$.next();
                if(isFirst) {
                    isFirst = false;
                } else {
                    sb.append("&");
                }

                key = (String)entry.getKey();
                value = (String)entry.getValue();
                if(value!=null&&!"".equals(value)) {
                    try {
                        value = URLEncoder.encode(value, charset);
                    } catch (UnsupportedEncodingException var9) {
                        throw new RuntimeException(var9);
                    }
                }
            }

            return sb.toString();
        } else {
            return url;
        }
    }


    /**
     * 将传入的键/值对参数转换为NameValuePair参数集
     *
     * @param paramsMap
     *            参数集, 键/值对
     * @return NameValuePair参数集
     */
    private List<NameValuePair> getParamsList(Map<String, String> paramsMap) {
        if (paramsMap == null || paramsMap.size() == 0) {
            return new  ArrayList<NameValuePair>();
        }
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> map : paramsMap.entrySet()) {
            params.add(new BasicNameValuePair(map.getKey(), map.getValue()));
        }

        if(params==null){
            return new  ArrayList<NameValuePair>();
        }
        return params;
    }


    private Charset getBomCharset(String html) {
        if (html==null||"".equals(html)) return null;
        byte[] bytes = html.getBytes();
        String code;
        if(bytes==null || bytes.length<2) return null;
        int p = ((int)bytes[0]&0x00ff) <<8|((int)bytes[1]&0x00ff);
        switch (p) {
            case 0xefbb:
                code = "UTF-8";
                break;
            case 0xfffe:
                code = "Unicode";
                break;
            case 0xfeff:
                code = "UTF-16BE";
                break;
            default:
                code = "GBK";
        }
        return Charset.forName(code);
    }

    /**
     * 从 html meta 标签中获取页面编码
     * @param html
     * @return
     */
    private Charset getHtmlCharset(String html) {
        String ct=null;
        Pattern pattern = Pattern.compile("<meta\\s+.*?charset=(\"|'|)(?<charset>.*?)(\"|'|>)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(html);
        ct = matcher.find() ? matcher.group("charset").toUpperCase() : null;
        if (ct==null) return this.getBomCharset(html);
        ct = "GB-2312".equals(ct) ? "GBK" : ct;
        return Charset.forName(ct);
    }

//    private void resetDepth() {
//        this.setDepth(false);
//        this.depthIndex = 0;
//    }

    private void doDepth(Map<String, String> paras, String charset) {
        String location = this.getLocation();
        if (!this.depth||location==null) return;
        if (location.equals(url)) {
            this.clear();
            throw new RuntimeException("错误的重定向");
        }
        if (this.depthIndex.equals(this.depthCount)) {
            this.clear();
            throw new RuntimeException("过多的重定向");
        }
        log.debug("将重定向至: {}", location);
        this.depthIndex+=1;
        this.get(location, paras, charset);
        return;
    }

    private void doDepth(Map<String, String> paras, Object data, String charset) {
        if (!this.depth||this.getLocation()==null) return;
        if (this.getLocation().equals(url)) {
            this.clear();
            throw new RuntimeException("错误的重定向");
        }
        if (this.depthIndex.equals(this.depthCount)) {
            this.clear();
            throw new RuntimeException("过多的重定向");
        }
        log.debug("将重定向至: {}", this.getLocation());
        this.depthIndex+=1;
        this.post(this.getLocation(), paras, data, charset);
    }






    public void get(String url, Map<String, String> paras, String charset) {
        if (url == null || url.isEmpty()) return;
        charset = (charset == null ? HttpConst.DEFAULT_CHARSET : charset);
        CloseableHttpClient hc = null;
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(HttpConst.TIMEOUT)
                .setConnectionRequestTimeout(HttpConst.TIMEOUT)
                .setSocketTimeout(HttpConst.SOTIMEOUT).build();
        if (this.proxy==null) {
            hc = HttpClients.custom().setDefaultRequestConfig(config).build();
        } else {
            HttpHost py = new HttpHost(this.proxy.getHost(), this.proxy.getPort());
            HttpClientBuilder hcb = HttpClients.custom().setProxy(py).setDefaultRequestConfig(config);
            if (this.proxy.getAccount()!=null&&!this.proxy.getAccount().isEmpty()) {
                Credentials credentials = new UsernamePasswordCredentials(this.proxy.getAccount(), this.proxy.getPasswd());
                AuthScope authScope = new AuthScope(this.proxy.getHost(), this.proxy.getPort());
                CredentialsProvider credsProvider = new BasicCredentialsProvider();
                credsProvider.setCredentials(authScope, credentials);
                hcb.setDefaultCredentialsProvider(credsProvider);
            }
            hc = hcb.build();
            log.info("Proxy Server, {}:{}", this.proxy.getHost(), this.proxy.getPort());
        }
        url = this.buildUrlWithQueryString(url, paras, charset);

        HttpGet get = new HttpGet(url);

        if (this.requestHeaders!=null&&!this.requestHeaders.isEmpty()) {
            Set<String> keys = this.requestHeaders.keySet();
            for(String key : keys)
                get.addHeader(key, this.requestHeaders.get(key));
        }
        CloseableHttpResponse response = null;
        String html = null;
        try {
            log.debug("Fetch URL: {}", url);
            response = hc.execute(get);
            this.setStat(response.getStatusLine().getStatusCode());
            // 将 reponse header 写入到 reponse header 变量中
            this.setResponseHeaders(response.getAllHeaders());
            this.setUrl(url);
            HttpEntity he = response.getEntity();
            html = EntityUtils.toString(he);

            // Charset cht = null;
            ContentType reader = ContentType.get(he);
            if (reader!=null&&reader.getCharset()!=null) {
                this.setHtml(html);
                this.doDepth(paras, charset);
                return;
            }
            Charset htmcht = this.getHtmlCharset(html);
            if (htmcht!=null) {
                this.setHtml(new String(html.getBytes(HTTP.DEF_CONTENT_CHARSET), htmcht));
                this.doDepth(paras, charset);
                return;
            }
            this.setHtml(new String(html.getBytes(HTTP.DEF_CONTENT_CHARSET), Charset.forName(charset)));
            this.doDepth(paras, charset);
            this.clear();
        } catch (ClientProtocolException e) {
            log.error(e.getMessage(), e);
        } catch (UnsupportedCharsetException e) {
            log.error(e.getCharsetName(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } catch (RuntimeException e) {
            log.debug(e.getMessage(), e);
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }

        }
    }


    public void post(String url, Map<String, String> paras, Object data, String charset) {
        if (url == null || url.isEmpty()) return;
        charset = (charset == null ? HttpConst.DEFAULT_CHARSET : charset);
        CloseableHttpClient hc = null;
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(HttpConst.TIMEOUT)
                .setConnectionRequestTimeout(HttpConst.TIMEOUT)
                .setSocketTimeout(HttpConst.SOTIMEOUT).build();
        if (this.proxy==null) {
            hc = HttpClients.custom().setDefaultRequestConfig(config).build();
        } else {
            HttpHost py = new HttpHost(this.proxy.getHost(), this.proxy.getPort());
            HttpClientBuilder hcb = HttpClients.custom().setProxy(py).setDefaultRequestConfig(config);
            if (this.proxy.getAccount()!=null&&!this.proxy.getAccount().isEmpty()) {
                Credentials credentials = new UsernamePasswordCredentials(this.proxy.getAccount(), this.proxy.getPasswd());
                AuthScope authScope = new AuthScope(this.proxy.getHost(), this.proxy.getPort());
                CredentialsProvider credsProvider = new BasicCredentialsProvider();
                credsProvider.setCredentials(authScope, credentials);
                hcb.setDefaultCredentialsProvider(credsProvider);
            }
            hc = hcb.build();
            log.info("Proxy Server, {}:{}", this.proxy.getHost(), this.proxy.getPort());
        }


        HttpPost post = null;
        String html = null;
        CloseableHttpResponse response = null;
        try {

            post = new HttpPost(this.buildUrlWithQueryString(url, paras, charset));

            if (data instanceof Map) {
                Map<String, String> var1 = (Map<String, String>) data;
                List<NameValuePair> params = getParamsList(var1);
                UrlEncodedFormEntity formEntity = null;
                formEntity = new UrlEncodedFormEntity(params, charset);
                post.setEntity(formEntity);
            }
            if (data instanceof String) {
                StringEntity entity = new StringEntity(data.toString(), charset);
                post.setEntity(entity);
            }

            if (this.requestHeaders!=null&&!this.requestHeaders.isEmpty()) {
                Set<String> keys = this.requestHeaders.keySet();
                for(String key : keys)
                    post.addHeader(key, this.requestHeaders.get(key));
            }
            log.debug("Fetch URL: {}", url);
            response = hc.execute(post);
            this.setStat(response.getStatusLine().getStatusCode());
            this.setResponseHeaders(response.getAllHeaders());
            this.setUrl(url);
            HttpEntity he = response.getEntity();
            html = EntityUtils.toString(he);

            // Charset cht = null;
            ContentType reader = ContentType.get(he);
            if (reader!=null&&reader.getCharset()!=null) {
                this.setHtml(html);
                this.doDepth(paras, data, charset);
                return;
            }
            Charset htmcht = this.getHtmlCharset(html);
            if (htmcht!=null) {
                this.setHtml(new String(html.getBytes(HTTP.DEF_CONTENT_CHARSET), htmcht));
                this.doDepth(paras, data, charset);
                return;
            }
            this.setHtml(new String(html.getBytes(HTTP.DEF_CONTENT_CHARSET), Charset.forName(charset)));
            this.doDepth(paras, data, charset);
            this.clear();
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        } catch (ClientProtocolException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } catch (RuntimeException e) {
            log.debug(e.getMessage(), e);
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }


    public void get(String url, String charset) {
        this.get(url, null, charset);
    }
    public void get(String url) {
        this.get(url, HttpConst.DEFAULT_CHARSET);
    }
    public void post(String url, Map<String, String> data, String charset) {
        this.post(url, null, data, charset);
    }
    public void post(String url, Map<String, String> data) {
        this.post(url, data, HttpConst.DEFAULT_CHARSET);
    }
    public void post(String url, String data, String charset) {
        this.post(url, null, data, charset);
    }
    public void post(String url, String data) {
        this.post(url, data, HttpConst.DEFAULT_CHARSET);
    }


}

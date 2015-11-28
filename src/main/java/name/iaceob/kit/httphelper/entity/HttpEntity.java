package name.iaceob.kit.httphelper.entity;

import name.iaceob.kit.httphelper.common.HttpConst;
import name.iaceob.kit.httphelper.http.HttpStatus;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cox on 2015/11/27.
 */
public class HttpEntity {
    private String url;
    private String host;
    private String uri;
    private String html;
    private Integer responseCode;
    private Map<String, List<String>> headers;

    public Integer getResponseCode() {
        return this.responseCode;
    }

    public HttpEntity setResponseCode(Integer stateCode) {
        this.responseCode = stateCode;
        return this;
    }

    public String getHost() {
        return this.host;
    }

    public HttpEntity setHost(String host) {
        this.host = host;
        return this;
    }

    public String getUri() {
        return this.uri;
    }

    public HttpEntity setUri(String uri) {
        this.uri = uri;
        return this;
    }

    public String getUrl() {
        return this.url;
    }

    public HttpEntity setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getHtml() {
        return this.html;
    }

    public HttpEntity setHtml(String html) {
        this.html = html;
        return this;
    }

    public Map<String, List<String>> getHeaders() {
        return this.headers;
    }

    public HttpEntity setHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
        return this;
    }

    public List<String> getHeader(String key) {
        return this.getHeaders().get(key);
    }

    public String getHeaderToStr(String key) {
        return this.getHeader(key).get(0);
    }

    /**
     * 获取 URL 转发的链接
     * @return
     */
    public String getLocation() {
        if (this.getResponseCode() == HttpStatus.SC_MOVED_TEMPORARILY) {
            List<String> vals = this.getHeader(HttpConst.LOCATION);
            if (vals!=null && !vals.isEmpty()) return vals.get(0);
        }
        String refreshReg = "<META\\s+http-equiv=\"refresh\".*?URL=('|)(?<url>[^\"]+)";
        Pattern p = Pattern.compile(refreshReg, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(this.getHtml());
        if (!m.find()) return null;
        String refu = m.group("url").replaceAll("'", "");
        if (refu.charAt(0)=='/') {
            refu = this.getHost() + refu;
            return refu;
        }
        if (!refu.substring(0, 4).equals("http")) {
            refu = this.getUri() + refu;
            return refu;
        }
        return refu;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(" {");

        sb.append("url: ").append(this.getUrl()).append(", ");
        sb.append("uri: ").append(this.getUri()).append(", ");
        sb.append("host: ").append(this.getHost()).append(", ");

        Map<String, List<String>> headerFields = this.getHeaders();

        Set<String> headerFieldsSet = headerFields.keySet();
        Iterator<String> hearerFieldsItera = headerFieldsSet.iterator();

        while (hearerFieldsItera.hasNext()) {

            String headerFieldKey = hearerFieldsItera.next();
            List<String> headerFieldValue = headerFields.get(headerFieldKey);

            StringBuilder sb2 = new StringBuilder();
            for (Integer i=0; i<headerFieldValue.size(); i++) {
                sb2.append(headerFieldValue.get(i)).append(i+1==headerFieldValue.size() ? " " : ", ");
            }

            sb.append(headerFieldKey).append(":").append(sb2.toString()).append(", ");
        }

        sb.append("html").append(":").append(this.getHtml()==null ? null : this.getHtml().substring(0, this.getHtml().length()>500 ? 500 : this.getHtml().length()))
                .append("...").append(" }");
        return sb.toString();
    }
}

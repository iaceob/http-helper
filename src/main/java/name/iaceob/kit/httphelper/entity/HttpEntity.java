package name.iaceob.kit.httphelper.entity;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by cox on 2015/11/27.
 */
public class HttpEntity {
    private String url;
    private String html;
    private Map<String, List<String>> headers;

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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(" {");

        sb.append("url: ").append(this.getUrl()).append(", ");

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

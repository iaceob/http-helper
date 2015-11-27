package name.iaceob.kit.httphelper.entity;

/**
 * Created by cox on 2015/11/27.
 */
public class HttpEntity {
    private String html;

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(" {")
                .append("html").append(this.getHtml()==null ? null : this.getHtml().substring(0, this.getHtml().length()>500 ? 500 : this.getHtml().length()))
                .append(" }");
        return sb.toString();
    }
}

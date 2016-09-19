package name.iaceob.kit.httphelper.http;

/**
 * Created by iaceob on 2016/9/19.
 */
public class HttpPara extends HttpMap {


    private String key;
    private Object value;

    public HttpPara(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "HttpPara{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}

package name.iaceob.httphelper;

/**
 * Created by iaceob on 2015/10/10.
 */
public class HttpProxy {
    private String host;
    private Integer port;
    private String account;
    private String passwd;

    public HttpProxy(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public HttpProxy(String host, Integer port, String account, String passwd) {
        this.host = host;
        this.port = port;
        this.account = account;
        this.passwd = passwd;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

//    public String toString() {
//        return JsonKit.toJson(this);
//    }
}

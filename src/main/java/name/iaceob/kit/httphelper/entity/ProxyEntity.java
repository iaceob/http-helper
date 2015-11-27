package name.iaceob.kit.httphelper.entity;

/**
 * Created by iaceob on 2015/10/10.
 */
public class ProxyEntity {
    private String host;
    private Integer port;
    private String account;
    private String password;

    public ProxyEntity(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public ProxyEntity(String host, Integer port, String account, String password) {
        this.host = host;
        this.port = port;
        this.account = account;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public String toString() {
//        return JsonKit.toJson(this);
//    }
}

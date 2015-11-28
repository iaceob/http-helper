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
        return this.host;
    }

    public ProxyEntity setHost(String host) {
        this.host = host;
        return this;
    }

    public Integer getPort() {
        return this.port;
    }

    public ProxyEntity setPort(Integer port) {
        this.port = port;
        return this;
    }

    public String getAccount() {
        return this.account;
    }

    public ProxyEntity setAccount(String account) {
        this.account = account;
        return this;
    }

    public String getPassword() {
        return this.password;
    }

    public ProxyEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(" {");
        sb.append("host").append(":").append(this.getHost()).append(", ")
                .append("port").append(":").append(this.getPassword()).append("}");
        return sb.toString();
    }
}

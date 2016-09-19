package name.iaceob.kit.httphelper.proxy;

import name.iaceob.kit.httphelper.auth.ProxyAuthenticator;

import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * Created by iaceob on 2016/9/19.
 */
public class TcpProxy implements HttpProxy {

    private String host, auth, passwd;
    private Integer port;

    public TcpProxy(String host, Integer port, String auth, String passwd) {
        if (host == null || "".equals(host))
            throw new IllegalArgumentException("Host can not be null");
        if (port == null)
            throw new IllegalArgumentException("Port can not be null");
        this.host = host;
        this.port = port;
        this.auth = auth;
        this.passwd = passwd == null ? "" : passwd;
    }

    public TcpProxy(String host, Integer port) {
        this(host, port, null, null);
    }


    @Override
    public Proxy gen() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(this.host, this.port));
        if (this.auth == null)
            return proxy;
        Authenticator.setDefault(new ProxyAuthenticator(this.auth, this.passwd));
        return proxy;
    }

}

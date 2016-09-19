package name.iaceob.kit.httphelper.proxy;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;

/**
 * Created by iaceob on 2016/9/19.
 */
public class SocketProxy implements HttpProxy {

    private String host;
    private Integer port;

    public SocketProxy(String host, Integer port) {
        if (host == null || "".equals(host))
            throw new IllegalArgumentException("Host can not be null");
        if (port == null)
            throw new IllegalArgumentException("Port can not be null");
        this.host = host;
        this.port = port;
    }

    @Override
    public Proxy gen() {
        SocketAddress addr = new InetSocketAddress(this.host, this.port);
        Proxy proxy = new Proxy(Proxy.Type.SOCKS, addr);
        return proxy;
    }

}

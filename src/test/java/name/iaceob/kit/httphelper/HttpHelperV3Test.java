package name.iaceob.kit.httphelper;

import name.iaceob.kit.httphelper.common.HttpConst;
import name.iaceob.kit.httphelper.config.HttpConf;
import name.iaceob.kit.httphelper.conn.HttpConn;
import name.iaceob.kit.httphelper.data.HttpData;
import name.iaceob.kit.httphelper.http.HttpHelper;
import name.iaceob.kit.httphelper.kit.Http;
import name.iaceob.kit.httphelper.proxy.SocketProxy;
import name.iaceob.kit.httphelper.http.HttpHeader;
import name.iaceob.kit.httphelper.http.HttpPara;
import name.iaceob.kit.httphelper.req.HttpRequest;
import name.iaceob.kit.httphelper.restful.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.nio.charset.Charset;

/**
 * Created by iaceob on 2016/9/19.
 */
public class HttpHelperV3Test {

    private static final Logger log = LoggerFactory.getLogger(HttpHelperV3Test.class);


    @Test
    public void testHttpbin() {
        String url = "http://httpbin.org/post?type=1";
        HttpRequest req = new HttpRequest(url, HttpMethod.POST);
        req.setHeader(new HttpHeader(HttpConst.COOKIE, "SESS=a654851b54ad544854"));
        req.setHeader(new HttpHeader(HttpConst.CONTENT_TYPE, "application/json"));
        req.setParas(new HttpPara("id", 1));
        req.setParas(new HttpPara("id", 2));
        req.setParas(new HttpPara("id", 3));
        req.setParas(new HttpPara("id", 4));
        req.setParas(new HttpPara("id", 5));
        req.setData("{\"id\": [1, 2, 3, 4, 5], \"type\": 1}");

        HttpConf cfg = new HttpConf();
        cfg.setCharset(Charset.forName("UTF-8"));
        cfg.setConnectTimeout(HttpConst.DEF_TIMEOUT);
        cfg.setReadTimeout(HttpConst.DEF_SOTIMEOUT);
        cfg.setAutoDetectCharset(false);
        // TCP 代理
        // cfg.setProxy(new TcpProxy("123.138.73.122", 9797));
        // SOCKET 代理
        // cfg.setProxy(new SocketProxy("127.0.0.1", 1080));

        HttpConn conn = new HttpConn(cfg);

        HttpData data = conn.exec(req);
        log.debug("data: {}", data);
    }

    @Test
    public void testSf() {
        String url = "https://segmentfault.com/";
        HttpRequest req = new HttpRequest(url, HttpMethod.GET);
        req.setHeader(new HttpHeader(HttpConst.COOKIE, "SESS=a654851b54ad544854"));
        HttpConf cfg = new HttpConf();
        cfg.setCharset(Charset.forName("UTF-8"));
        HttpConn conn = new HttpConn(cfg);
        HttpData data = conn.exec(req);
        log.debug("data: {}", data);
    }

    // TODO Google 测试未通过, 错误发生在 SSL 连接上, 其他站, 例如上方的 segmentfault 测试可用.
    // 可能在部分站点提交时无法访问, 该问题尚需要完善
    @Test
    public void testGoogle() {
        String url = "https://google.com";
        HttpRequest req = new HttpRequest(url, HttpMethod.GET);
        req.setHeader(new HttpHeader(HttpConst.COOKIE, "SESS=a654851b54ad544854"));
        HttpConf cfg = new HttpConf();
        cfg.setCharset(Charset.forName("UTF-8"));
        cfg.setProxy(new SocketProxy("127.0.0.1", 1080));
        HttpConn conn = new HttpConn(cfg);
        HttpData data = conn.exec(req);
        log.debug("data: {}", data);
    }

    @Test
    public void testStackoverflow() {
        String url = "http://stackoverflow.com";
        HttpRequest req = new HttpRequest(url, HttpMethod.GET);
        HttpConf cfg = new HttpConf();
        cfg.setCharset(Charset.forName("UTF-8"));
        cfg.setProxy(new SocketProxy("127.0.0.1", 1080));
        HttpConn conn = new HttpConn(cfg);
        HttpData data = conn.exec(req);
        log.debug("data: {}", data);
    }

    @Test
    public void testEasemob() {
        String url = "http://docs.easemob.com";
        HttpRequest req = new HttpRequest(url, HttpMethod.GET);
        HttpConf cfg = new HttpConf();
        cfg.setCharset(Charset.forName("UTF-8"));
        HttpConn conn = new HttpConn(cfg);
        HttpData data = conn.exec(req);
        log.debug("data: {}", data);
    }

    @Test
    public void testBaidu() {
        String url = "https://www.baidu.com/";
        HttpRequest req = new HttpRequest(url, HttpMethod.GET);
        HttpConf cfg = new HttpConf();
        cfg.setCharset(Charset.forName("UTF-8"));
        HttpConn conn = new HttpConn(cfg);
        HttpData data = conn.exec(req);
        log.debug("data: {}", data);
    }

    @Test
    public void testHttp() {
        String url = "http://bing.com";
        Http http = new HttpHelper();
        HttpData data = http.connect(HttpMethod.GET, url);
        log.debug("data: {}", data);
    }
}

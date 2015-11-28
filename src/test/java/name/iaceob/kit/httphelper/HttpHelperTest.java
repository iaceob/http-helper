package name.iaceob.kit.httphelper;


import name.iaceob.kit.httphelper.common.HttpConst;
import name.iaceob.kit.httphelper.config.HttpConfig;
import name.iaceob.kit.httphelper.conn.HttpConnection;
import name.iaceob.kit.httphelper.conn.HttpConnectionBuilder;
import name.iaceob.kit.httphelper.entity.HttpEntity;
import name.iaceob.kit.httphelper.entity.ProxyEntity;
import name.iaceob.kit.httphelper.http.HttpReq;
import name.iaceob.kit.httphelper.restful.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class HttpHelperTest {

    private static final Logger log = LoggerFactory.getLogger(HttpHelperTest.class);

    @Test
    public void testUrlConnection() throws IOException {
        String url = "http://segmentfault.com/";
        url = "http://www.baidu.com/s?wd=kh&rsv_spt=1&rsv_iqid=0xad16aa310001a4b4&issp=1&f=8&rsv_bp=0&rsv_idx=2&ie=utf-8&tn=baiduhome_pg&rsv_enter=1&rsv_sug3=3&rsv_sug1=1&rsv_sug2=0&inputT=554&rsv_sug4=1352";
        url = "http://www.baidu.com/link?url=PZKL1hz8xkRV4wV0u7UrTlBEK66CcAx4Z1pjj1407_qLmUAIrYovH40droQbmaMxSUBeutNTycu1Ar2J_IWHx_&wd=&eqid=d29e45c10001b22f000000035658a9a2";
        url = "http://politics.people.com.cn/BIG5/n/2014/0226/c70731-24469659.html";
        url = "http://baidu.com/";
        HttpConfig config = HttpConfig.create.setConnectTimeout(HttpConst.DEF_TIMEOUT)
                .setCharset(Charset.forName("utf-8"));
        ProxyEntity pe = new ProxyEntity("192.168.25.254", 28129);
        pe.setAccount("yproxyq").setPassword("zproxyx0#");
        HttpConnection hc = HttpConnectionBuilder.create().setConfig(config)
                .setProxy(pe)
                .build();
        HttpReq req = new HttpReq(HttpMethod.GET, url);
        // req.setHeader(HttpConst.COOKIE, "sf_remember=5ea78002e05b9699e46727a95f37a30c;PHPSESSID=web2~3fg8r6t705k7me8qunvkqc0p41");
        HttpEntity he = hc.exec(req);
        log.debug(he.getHeaderToStr("Location"));
        log.debug("contains 我的主: {}", he.getHtml().contains("我的主"));
    }

    @Test
    public void ttd2() {
        String url = "http://segmentfault.com/";
        url = "http://politics.people.com.cn/BIG5/n/2014/0226/c70731-24469659.html";
        String c = HttpKit.get(url);
        log.debug(c);

    }


    @Test
    public void ttd3() throws MalformedURLException {
        Map<String, String> m = new HashMap<String, String>();
        String t = m.get(22);
        System.out.println(t);
        URL u = new URL("http://baidu.com/s");
        System.out.println(u.toString());
    }

}
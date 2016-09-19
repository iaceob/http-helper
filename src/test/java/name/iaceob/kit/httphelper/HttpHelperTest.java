package name.iaceob.kit.httphelper;


import name.iaceob.kit.httphelper.common.HttpConst;
import name.iaceob.kit.httphelper.config.HttpConfig;
import name.iaceob.kit.httphelper.conn.HttpConnection;
import name.iaceob.kit.httphelper.conn.HttpConnectionBuilder;
import name.iaceob.kit.httphelper.entity.HttpEntity;
import name.iaceob.kit.httphelper.req.HttpReq;
import name.iaceob.kit.httphelper.kit.HttpKit;
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
        url = "http://sd.china.com.cn/a/2015/fzzx_0516/230615.html";
        url = "http://www.chinaso.com/search/link?url=d12ln5Mp58P%2FwdMVX43HkzYR6tqhI%2Bb5%2Bh2qzlCSj0AGgjKhAYovQg1svQL4K1%2B1&pos=0&wd=%E9%B8%A0%E6%B1%9F%E5%8C%BA+%E5%A4%B1%E8%B8%AA";
        // url = "http://baidu.com/s?wd=java";
        url = "http://www.ahcaijing.com/news/2014/0317/378136.shtml";
        url = "http://finance.ifeng.com/news/region/20120718/6775697.shtml";
        url = "http://china.findlaw.cn/ask/question_31067572.html";
        url = "http://m.tianya.cn/sch/sch.jsp?vu=28456309174&o=1&k=sag&t=2";
        url = "http://liuyan.people.com.cn/thread.php?tid=3100415&display=1&page=13";
        url = "http://tgrw.jjq.gov.cn/News_list.asp?id=113";
        url = "http://edu.sina.com.cn/kaoyan/2015-11-20/doc-ifxkwuwy7011845.shtml";
        url = "http://gkcx.eol.cn/schoolhtm/schoolTemple/school1813.htm";
        HttpConfig config = HttpConfig.me().setConnectTimeout(HttpConst.DEF_TIMEOUT)
                .setCharset(Charset.forName("utf-8"))
                // .setAutoDetectCharset(true)
                ;
//        ProxyEntity pe = new ProxyEntity("192.168.25.254", 28129);
//        pe.setAccount("yproxyq").setPassword("zproxyx0#");
        HttpConnection hc = HttpConnectionBuilder.create().setConfig(config)
                // .setProxy(pe)
                .build();
        HttpReq req = new HttpReq(HttpMethod.GET, url);
        // req.setHeader(HttpConst.COOKIE, "sf_remember=5ea78002e05b9699e46727a95f37a30c;PHPSESSID=web2~3fg8r6t705k7me8qunvkqc0p41");
        HttpEntity he = hc.exec(req);
        log.debug("", he.getResponseCode());
        log.debug(he.getBasePath());
        log.debug(he.getHtml());

    }

    @Test
    public void ttd2() {
        String url = "http://segmentfault.com/";
        url = "http://politics.people.com.cn/BIG5/n/2014/0226/c70731-24469659.html";
        url = "http://sd.china.com.cn/a/2015/fzzx_0516/230615.html";
        url = "http://edu.sina.com.cn/kaoyan/2015-11-20/doc-ifxkwuwy7011845.shtml";
        url = "http://roll.sohu.com/20151116/n426598703.shtml";
        url = "http://www.xilu.com/special_beijingwumai.html";
        url = "http://edu.sina.com.cn/kaoyan/2015-11-20/doc-ifxkwuwy7011845.shtml";
        url = "http://www.2888392.cn/news/2015/11/30/30323.html";
        url = "http://ah.offcn.com/html/2015/11/74797.html?pc_hash=lL1mcE";
        url = "http://m.tianya.cn/bbs/art.jsp?item=news&id=285919";
        url = "http://henan.sina.com.cn/news/s/2013-08-22/1120-88553.html";
        url = "http://edu.anhuinews.com/system/2015/11/03/007068966.shtml";
        url = "http://news.sina.com.cn/c/2015-11-25/doc-ifxkwuxx1833706.shtml";
        HttpEntity he = HttpKit.get(url, true);
        String s = he.getHtml();
        log.debug(s);

    }

    @Test
    public void ttd4() {
        String url = "http://my.oschina.net/tinyframework/blog/288661";
        HttpEntity he = HttpKit.request(HttpMethod.GET, url);
        log.debug("headers: {}", he.getHeaders());
        log.debug("html: {}", he.getHtml());
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
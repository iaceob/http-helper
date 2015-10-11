package name.iaceob.httphelper.test;

import name.iaceob.httphelper.HttpHelper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by iaceob on 2015/10/10.
 */
public class testHH {


    private static final Logger log = LoggerFactory.getLogger(testHH.class);

    @Test
    public void ttd1() {

        String url = null;
        url = "http://www.baidu.com/";
        url = "http://www.baidu.com/link?url=P25mGmRqnhQb-jyXtI9RPN5pv3L7LfIXDMnTvI8oKmS&wd=&eqid=f4c635450024b60c000000045618bd67";

        HttpHelper hh = new HttpHelper();
        hh.setDepth(true).get(url);
        Map<String, String> reqh = hh.getRequestHeaders();
        Map<String, String> reph = hh.getResponseHeaders();

        // log.debug(html);
        log.debug("Request Header: {}", reqh);
        log.debug("Response Header: {}", reph);
        log.debug("Location: {}", hh.getLocation());
        log.debug("Cookie: {}", hh.getCookie());
        log.debug("Status Code: {}", hh.getStatCode());
        log.debug("URL: {}", hh.getUrl());
        log.debug(hh.getHtml());

        /*
        if (StrKit.isBlank(hh.getLocation())) return;
        if (hh.getLocation().equals(url)) return;
        hh.get(hh.getLocation());

        Map<String, String> reqh2 = hh.getRequestHeaders();
        Map<String, String> reph2 = hh.getResponseHeaders();

        // log.debug(html);
        log.debug("Request Header: {}", JsonKit.toJson(reqh2));
        log.debug("Response Header: {}", JsonKit.toJson(reph2));
        log.debug("Location: {}", hh.getLocation());
        log.debug("Cookie: {}", hh.getCookie());
        log.debug("Status Code: {}", hh.getStatCode());
        log.debug("URL: {}", hh.getUrl());
        log.debug(hh.getHtml());
        */
    }


    @Test
    public void ttd2() {
        String url = "http://www.baidu.com/link?url=P25mGmRqnhQb-jyXtI9RPN5pv3L7LfIXDMnTvI8oKmS&wd=&eqid=f4c635450024b60c000000045618bd67";
        Map<String,String> h = new HashMap<String, String>();
        h.put("x", "b");
        log.debug(buildUrlWithQueryString(url, h, "UTF-8"));
    }


    @Test
    public void ttd3() {
        String metav;
        metav = "<noscript><META http-equiv=\"refresh\" content=\"0;URL='http://ek.39.net/'\"></noscript>";
        // metav = "<META HTTP-EQUIV=refresh CONTENT=\"1; url=http://roundtopstatebank.com\">";
        String reg = "<META\\s+[^>]*http-equiv=[\\'\"]?refresh(?=[^>]*URL=['\"]([^\\'\" >]+)['\"])";
        Pattern p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(metav);
        log.debug(m.groupCount()+"");
        if (m.find()) {
            log.debug(m.group(1));
        }
    }


    private String buildUrlWithQueryString(String url, Map<String, String> paras, String charset) {
        if(paras != null && !paras.isEmpty()) {
            StringBuilder sb = new StringBuilder(url);
            boolean isFirst;
            if(url.indexOf("?") == -1) {
                isFirst = true;
                sb.append("?");
            } else {
                isFirst = false;
            }

            String key;
            String value;
            for(Iterator i$ = paras.entrySet().iterator(); i$.hasNext(); sb.append(key).append("=").append(value)) {
                Map.Entry entry = (Map.Entry)i$.next();
                if(isFirst) {
                    isFirst = false;
                } else {
                    sb.append("&");
                }

                key = (String)entry.getKey();
                value = (String)entry.getValue();
                if(value!=null) {
                    try {
                        value = URLEncoder.encode(value, charset);
                    } catch (UnsupportedEncodingException var9) {
                        throw new RuntimeException(var9);
                    }
                }
            }

            return sb.toString();
        } else {
            return url;
        }
    }

}

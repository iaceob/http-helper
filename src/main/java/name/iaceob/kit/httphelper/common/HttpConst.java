package name.iaceob.kit.httphelper.common;

import java.nio.charset.Charset;

/**
 * Created by cox on 2015/11/27.
 */
public class HttpConst {

    private static final String _HTTPHELPER_VERSION = "3.2-beta";

    public static final String LOCATION = "Location";
    public static final String USER_AGENT = "User-Agent";
    public static final String ACCEPT_LANGUAGE = "Accept-Language";
    public static final String ACCEPT_CHARSET = "Accept-Charset";
    public static final String ACCEPT = "Accept";
    public static final String CONNECTION = "Connection";
    public static final String ACCEPT_ENCODING = "Accept-Encoding";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String COOKIE = "Cookie";
    public static final String SET_COOKIE = "Set-Cookie";

    public static final String DEF_USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64; rv:43.0) Gecko/20100101 Firefox/50.0 HttpKit/" + _HTTPHELPER_VERSION;
    public static final String DEF_ACCEPT_LANGUAGE = "en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4,zh-TW;q=0.2";
    public static final String DEF_ACCEPT_CHARSET = "utf-8,ISO-8859-1;q=0.7,*;q=0.7";
    public static final String DEF_ACCEPT = "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8";
    public static final String DEF_CONTENT_TYPE = "application/x-www-form-urlencoded";
    public static final Charset DEF_CHARSET = Charset.forName("UTF-8");
    public static final Integer DEF_TIMEOUT = 20000;
    public static final Integer DEF_SOTIMEOUT = 30000;

    public static final Charset DEF_CONTENT_CHARSET = Charset.forName("ISO-8859-1");


}

package name.iaceob.kit.httphelper.common;

import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by iaceob on 2015/11/28.
 */
public class IdentifyCharset {


    public static Charset identify(String content) {
        String ct = null;
        Pattern pattern = Pattern.compile("<meta\\s+.*?charset=(\"|'|)(?<charset>.*?)(\"|'|>)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(content);
        ct = matcher.find() ? matcher.group("charset").toUpperCase() : null;
        if (ct == null) return IdentifyCharset.getBomCharset(content);
        ct = "GB-2312".equals(ct) ? "GBK" : ct;
        return Charset.forName(ct);
    }

    private static Charset getBomCharset(String html) {
        if (html == null || "".equals(html)) return null;
        byte[] bytes = html.getBytes();
        String code;
        if (bytes == null || bytes.length < 2) return null;
        int p = ((int) bytes[0] & 0x00ff) << 8 | ((int) bytes[1] & 0x00ff);
        switch (p) {
            case 0xefbb:
                code = "UTF-8";
                break;
            case 0xfffe:
                code = "Unicode";
                break;
            case 0xfeff:
                code = "UTF-16BE";
                break;
            default:
                code = "GBK";
        }
        return Charset.forName(code);
    }

}

package name.iaceob.kit.httphelper;

import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by iaceob on 2015/11/28.
 */
public class IdentifyCharset {


    public static Charset identify(String content, String contentType) {
        Charset ct1 = IdentifyCharset.getCharsetFromContentType(contentType);
        if (ct1 != null) return ct1;
        Charset ct2 = IdentifyCharset.getCharsetFromHtml(content);
        if (ct2 != null) return ct2;
        return Charset.forName("UTF-8");
    }

    private static Charset getCharsetFromContentType(String contentType) {
        if (contentType == null || "".equals(contentType)) return null;
        String[] cts = contentType.split(";");
        if (cts.length == 1) return null;
        String[] cht = cts[1].split("=");
        if (cht.length == 1) return null;
        return Charset.forName(cht[1]);
    }

    private static Charset getCharsetFromHtml(String html) {
        String ct = null;
        Pattern pattern = Pattern.compile("<meta\\s+.*?charset=(\"|'|)(?<charset>.*?)(\"|'|>)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(html);
        ct = matcher.find() ? matcher.group("charset").toUpperCase() : null;
        ct = "GB-2312".equals(ct) ? "GBK" : ct;
        return ct == null || "".equals(ct) ? null : Charset.forName(ct);
    }

    /**
     * 測試發現這幾乎是一種不靠譜的選擇方案
     *
     * @param html html 內容
     * @return Charset
     */
    @Deprecated
    private static Charset getCharsetFromBom(String html) {
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

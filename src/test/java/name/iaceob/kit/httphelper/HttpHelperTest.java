package name.iaceob.kit.httphelper;


import name.iaceob.httphelper.test.URLConnectionUtil;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class HttpHelperTest {


    @Test
    public void testUrlConnection() {
        String url = "http://baidu.com/s";
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("wd", "java");
        try {
            URLConnectionUtil.doGet(url, m);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
package name.iaceob.kit.httphelper.http;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by iaceob on 2016/9/19.
 */
public abstract class HttpMap implements Serializable {

    /**
     * Http 请求中 map 型数据 key
     *
     * @return String
     */
    public abstract String getKey();

    /**
     * Http 请求中 map 型数据 value
     *
     * @return String
     */
    public abstract Object getValue();


    /**
     * 将数组类型 HttpMap 类转换为 Map 对象
     *
     * @param maps 数组
     * @return Map
     */
    public static Map<String, String> toMap(List<? extends HttpMap> maps) {
        if (maps == null)
            return null;
        Map<String, String> zes = new HashMap<>();
        for (HttpMap map : maps)
            zes.put(map.getKey(), map.getValue().toString());
        return zes;
    }

}

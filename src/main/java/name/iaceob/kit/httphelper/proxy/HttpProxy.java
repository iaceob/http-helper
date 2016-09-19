package name.iaceob.kit.httphelper.proxy;

import java.net.Proxy;

/**
 * Created by iaceob on 2016/9/19.
 */
public interface HttpProxy {


    /**
     * 生成代理类
     *
     * @return Proxy
     */
    Proxy gen();


}

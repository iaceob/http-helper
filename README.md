
# http-helper

## 版本

3.0-beta

## 版本升级

3.x 系列版本从 3.0 开始仍然兼容 2.x 系列版本, 之后的版本将不在兼容.
3.x 系列内部实现很多于 2.x 实现不相同, 具体可通过 tag 中代码查阅.

[2.x 文档](./README-2.x.md)

## 使用方式

仍然采用两种方式实现

### 构建对象方式:

```java

HttpRequest request = new HttpRequest("http://httpbin.org/get", HttpMethod.GET);
HttpConf conf = new HttpConf();

request.setHeader(new HttpHeader("key", "val"));
request.setParas(new HttpPara("key", "val"));
request.setData("DATA");

conf.setCharset(Charset.forName("utf-8"));
conf.setProxy(new TcpProxy("admin", "password"));
conf.setConnectTimeout(HttpConst.DEF_TIMEOUT);
conf.setReadTimeout(HttpConst.DEF_SOTIMEOUT);
conf.setAutoDetectCharset(false);

HttpConn conn = new HttpConn(conf);
HttpData response = conn.exec(request);

```

### 使用预定义的工具类

```java

Http http = new HttpHelper();
http.connect(HttpMethod.GET, "http://httpbin.org/get");
http.get("http://httpbin.org/get");
http.post("http://httpbin.org/post");
http.put("http://httpbin.org/put");
http.delete("http://httpbin.org/delete");

```

具体请参阅 Http 中所定义的接口

## 升级日志

### v3.1-beta

- 移除 v2.x 代码


### v3.0-beta

- 修改整体请求实现方式, 代码逻辑更加严谨, 包结构更加合理
- 增加 HttpHeader, HttpPara 类, 做强类型数据, 抛弃 Map 实现.
- 请求结束返回 Cookie 单独处理, 课在 HttpData 中直接获取, Cookie 对象使用 `java.net.HttpCookie` 类
- 代理实现放置于 HttpConf 中, 而不是直接使用实体传送, 支持 HTTP/TCP SOCKET 代理, 使用者可自定义其他代理, 时间 HttpProxy 接口即可
- Http 工具类定义为接口类型, 而不是直接实现, 便于使用其他库扩展
- Deprecated 2.x 实现类

进测试发现, 在请求 Google 网站时, 会报出 SSL 相关错误问题, 暂时尚未解决, 测试过其他 SSL 站点均可访问(此处有坑).

3.0 版本暂时支持 2.x 系列代码, 应及时替换, 后续版本将不在维护 2.x 系列代码.
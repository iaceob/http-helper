#http-helper

## 使用方式
```
String url = "http://example.com";
// 设置链接配置信息
HttpConfig config = HttpConfig.create.setConnectTimeout(HttpConst.DEF_TIMEOUT)
        .setCharset(Charset.forName("utf-8")).setXxx;
// 配置代理信息
ProxyEntity pe = new ProxyEntity("192.168.25.254", 28129);
pe.setAccount("yproxyq").setPassword("zproxyx0#"); // 设置代理验证, 或者直接在 new ProxyEntity 中设置代理验证信息
// 创建 HttpConnection 将 配置信息以及代理等信息放入到 HttpConnection 中
HttpConnection hc = HttpConnectionBuilder.create().setConfig(config)
        .setProxy(pe)
        .build();
// 创建链接方式 HttpMethod{ GET, POST, PUT, DELETE }
HttpReq req = new HttpReq(HttpMethod.GET, url);
// 设置 RequestHeader
req.setHeader(HttpConst.COOKIE, "key=val");
// 执行
HttpEntity he = hc.exec(req);
// 获取 Response Header, 另有 getHeader 获取 List<String> 所有 Header 和 getHeaders 获取完整 Response Header
he.getHeaderToStr("Location");
// 获取返回 HTML
he.getHtml();
```

或者更简单的方式使用 HttpKit
```
HttpEntity he = HttpKit.get(url);
HttpEntity he = HttpKit.post(url, data);
```

## v2.2.3-beta
HttpEntity 增加 ResponseCode

## v2.2.2-beta
HttpEntity 增加 URI 以及 HOST

## v2.2.1-beta
HttpEntity 中增加请求 URL

## v2.2-beta
修复 HttpConnection post 未传递参数错误, 另增加 HttpKit 快捷调用

## v2.1-beta
完成 response header 提取
代理功能测试成功

## v2.0-beta
拋棄 HttpClient 實現, 改用 HttpURLConnection 方式實現,
基本頁面訪問以實現, 待完善
HttpEntity 目前僅有返回的 html 內容
刪除原有 HttpClient 實現方式代碼
代理功能暫未測試
配置項中增加自動檢測編碼功能, 默認爲 false 不自動檢測, 另自動檢測需添加從 Header 中獲取
編碼信息, 瀏覽器通常的做法是首先從 Header 取編碼, 當不存在才會在 html 中查找編碼
v 2.x 不兼容 v 1.x 使用方式也做了變動
v 2.x 不依賴任何 jar 包

## v1.0-beta
HttpHelper hh = new HttpHelper();
// hh.setXxx 设置访问链接的 header proxy 等信息
hh.get("http://example.com")
// hh.getXxx 获取抓取后的信息
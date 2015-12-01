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

## v2.3.6-beta
剔除编码自动检测, 在多线程测试中发现会造成乱码, 若不制定编码, 默认使用 ISO-8859-1

## v2.3.5-beta
HttpEntity 增加返回 Charset

## v2.3.4-beta
HttpKit 增加自动检测编码快捷方式

## v2.3.3-beta
添加訪問鏈接 HttpStatusCode 爲 400(錯誤訪問) 以上狀態內容獲取

## v2.3.2-beta
修復自動編碼檢測失敗 bug

## v2.3.1-beta
修復獲取 basePath 錯誤問題

## v2.2.9-beta
HttpEntity 添加 protocol domain host basePath

## v2.2.8-beta
修复 get 方式访问, 405 状态错误

## v2.2.7-beta
修复 HttpKit.post 参数未写入, HttpEntity 中的 uri 以及 host 未写入

## v2.2.6-beta
HttpEntity 中增加获取转发链接

## v2.2.5-beta
HttpKit 增加 AutoDetectCharset 选项

## v2.2.4-beta
copy HttpClient@HttpStatus.java

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
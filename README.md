#http-helper

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
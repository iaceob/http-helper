
# http-helper

## REMOVE

http-helper project merge to [enoa](https://github.com/ein-windmill/enoa).

http-helper is enoa-http default provider.

- [enoa-http](https://github.com/ein-windmill/enoa/tree/master/enoa-http)

## EXAMPLE

### GET

```java
Http.request("https://httpbin.org//get")
  .method(HttpMethod.GET)
  .para("arg0", "v0")
  .para("arg1", "v1")
  .header("user-agent", "Mozilla/5.0 firefox 54")
  .header("origin", "http://localhost:100")
  .emit();
```

### POST

```java
Http.request(EoUrl.with("https://httpbin.org").subpath("post").para("arg0", 1))
  .method(HttpMethod.POST)
  .para("arg0", "2")
  .para("arg1", 3)
  .cookie("key0", "value0")
  .emit();
```

### MORE

[https://github.com/ein-windmill/enoa/tree/master/enoa-example/example-http](https://github.com/ein-windmill/enoa/tree/master/enoa-example/example-http)


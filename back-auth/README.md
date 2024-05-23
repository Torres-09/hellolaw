### MVC 기반의 로직 작성
```java
private String getAccessToken(HttpServletRequest req) {
    Cookie[] list = req.getCookies();
    if (list != null) {
        for (Cookie cookie : list) {
            log.info(cookie.toString());
            log.info(cookie.getName());
            log.info(cookie.getValue());
            if (cookie.getName().equals("access-token")) {
                return cookie.getValue();
            }
        }
    }
    return null;
}
```
MVC 를 기반으로 작동하기 때문에 Servlet 기술을 사용하여 로직을 작성했습니다.  
HttpServletRequest, HttpServletResponse와 같은 인터페이스를 사용합니다.
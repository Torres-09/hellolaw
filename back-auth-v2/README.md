### WebFlux 기반의 로직 작성
```java
    private Mono<String> getAccessToken(MultiValueMap<String, HttpCookie> cookies) {
        log.info("쿠키에서 액세스 토큰 추출 시도");
        HttpCookie accessTokenCookie = cookies.getFirst("access-token");
        if (accessTokenCookie == null) {
            log.warn("액세스 토큰 쿠키가 존재하지 않습니다.");
            return Mono.empty();
        }
        String accessToken = accessTokenCookie.getValue();
        log.info("액세스 토큰 추출 성공: {}", accessToken);
        return Mono.just(accessToken);
    }
```

기존 MVC에서 사용하던 HttpServletRequest를 전부 ServerWebExchange, ServerHttpRequest로 전환하고, 반응형 프로그래밍을 도입했습니다.
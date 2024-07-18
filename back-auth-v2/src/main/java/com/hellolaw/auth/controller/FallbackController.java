package com.hellolaw.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/test")
    public ResponseEntity<String> testFallback() {
        return ResponseEntity.ok("테스트 서비스를 현재 사용할 수 없습니다. 나중에 다시 시도하십시오.");
    }

    @GetMapping("/answer")
    public ResponseEntity<String> answerFallback() {
        return ResponseEntity.ok("답변 서비스를 현재 사용할 수 없습니다. 나중에 다시 시도하십시오.");
    }

    @GetMapping("/healthcheck")
    public ResponseEntity<String> healthcheckFallback() {
        return ResponseEntity.ok("헬스 체크 서비스를 현재 사용할 수 없습니다. 나중에 다시 시도하십시오.");
    }

    @GetMapping("/law")
    public ResponseEntity<String> lawFallback() {
        return ResponseEntity.ok("법안 서비스를 현재 사용할 수 없습니다. 나중에 다시 시도하십시오.");
    }

    @GetMapping("/precedent")
    public ResponseEntity<String> precedentFallback() {
        return ResponseEntity.ok("판례 서비스를 현재 사용할 수 없습니다. 나중에 다시 시도하십시오.");
    }

    @GetMapping("/question")
    public ResponseEntity<String> questionFallback() {
        return ResponseEntity.ok("법률 조언 서비스를 현재 사용할 수 없습니다. 나중에 다시 시도하십시오.");
    }
}

package com.vnn.devops;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class HelloController {

    // API kiểm tra trang chủ
    @GetMapping("/")
    public Map<String, Object> hello() {
        return Map.of(
            "message", "Hello DevOps! Ứng dụng Spring Boot đang chạy thành công.",
            "status", "UP"
        );
    }

    // API trả về thông tin môi trường
    @GetMapping("/api/v1/info")
    public Map<String, Object> info() {
        return Map.of(
            "appName", "sample-to-devops",
            "javaVersion", System.getProperty("java.version"),
            "os", System.getProperty("os.name")
        );
    }
}
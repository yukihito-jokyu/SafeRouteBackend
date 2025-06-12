package com.osc.saferoute;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // ① このクラスがWebリクエストを処理することを示す
@CrossOrigin(origins = "http://localhost:5173")
public class HelloController {

    @GetMapping("/") // ② ルートパス ("/") へのGETリクエストをこのメソッドに割り当てる
    public String hello() {
        return "Hello, Spring Boot!"; // ③ 返した文字列がそのままブラウザに表示される
    }
}
package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // HTTP GET 요청을 처리하는 핸들러 메소드
    @GetMapping("/")
    public String home(){
        // "home"을 반환하여 뷰 리졸버(ViewResolver)에 의해 실제 View로 매핑됨
        return "home";
    }
}

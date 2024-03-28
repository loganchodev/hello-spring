package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // "/hello" 경로에 GET 요청을 처리하는 핸들러 메소드
    @GetMapping("hello")
    public String hello(Model model){
        // 모델에 데이터 추가
        model.addAttribute("data", "");
        // "hello" 템플릿을 렌더링
        return "hello";
    }

    // "/hello-mvc" 경로에 GET 요청을 처리하는 핸들러 메소드
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        // 요청 파라미터로 전달된 이름을 모델에 추가
        model.addAttribute("name", name);
        // "hello-template.html" 템플릿을 렌더링
        return "hello-template.html";
    }

    // "/hello-string" 경로에 GET 요청을 처리하는 핸들러 메소드
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        // 문자열을 HTTP 응답 본문으로 반환
        return "hello " + name;
    }

    // "/hello-api" 경로에 GET 요청을 처리하는 핸들러 메소드
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        // Hello 객체를 생성하여 이름을 설정한 후 반환
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    // Hello 클래스: JSON 응답으로 반환될 객체
    static class Hello{
        private String name;

        // Getter 및 Setter
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}

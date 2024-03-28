package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    // MemberService를 주입받는 생성자
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    // 회원 등록 폼을 보여주는 핸들러 메소드
    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    // 회원 등록을 처리하는 핸들러 메소드
    @PostMapping("/members/new")
    public String create (Member member){
        // 회원 등록 서비스 호출
        memberService.join(member);
        // 홈 페이지로 리다이렉트
        return "redirect:/";
    }

    // 전체 회원 목록을 보여주는 핸들러 메소드
    @GetMapping("/members")
    public String list(Model model){
        // 전체 회원 조회
        List<Member> members = memberService.findMembers();
        // 모델에 회원 목록 추가
        model.addAttribute("members", members);
        // 회원 목록 페이지로 이동
        return "members/memberList";
    }
}

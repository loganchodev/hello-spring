package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
//@Transactional
class MemberServiceIntergrationTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    // 회원가입 테스트
    @Test
    @Commit
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName()); // 저장된 회원과 조회한 회원의 이름이 같은지 검증
    }

    // 중복 회원 예외 테스트
    @Test
    void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        try {
            memberService.join(member2);
            fail();

        } catch (IllegalStateException e) {
            //then
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); // 예외가 발생하는지, 발생한 예외 메시지가 올바른지 검증
        }
    }
}

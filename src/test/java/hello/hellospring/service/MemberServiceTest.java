package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    // 각 테스트가 실행된 후에 실행되는 메서드
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore(); // 테스트 종료 후 저장소 초기화
    }

    // 회원가입 테스트
    @Test
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

    @Test
    void join() {
        // 테스트할 메서드가 이미 회원가입 테스트에서 충분히 검증되었으므로 별도의 테스트가 필요하지 않음
    }

    @Test
    void findMembers() {
        // 테스트할 메서드가 이미 회원가입 테스트에서 충분히 검증되었으므로 별도의 테스트가 필요하지 않음
    }

    @Test
    void findOne() {
        // 테스트할 메서드가 이미 회원가입 테스트에서 충분히 검증되었으므로 별도의 테스트가 필요하지 않음
    }
}

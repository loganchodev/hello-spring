package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 각 테스트 메서드 실행 후 호출되는 메서드로, 저장소를 초기화하여 테스트 간의 영향을 방지합니다.
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    // save() 메서드 테스트
    @Test
    public void save() {
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        repository.save(member);

        // then
        Member result = repository.findById(member.getId()).get();
        assertThat(result).isEqualTo(member); // 저장된 회원과 조회한 회원이 같은지 검증합니다.
    }

    // findByName() 메서드 테스트
    @Test
    public void findByName() {
        // given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // when
        Member result = repository.findByName("spring1").get();

        // then
        assertThat(result).isEqualTo(member1); // 이름으로 조회한 회원과 기대한 회원이 같은지 검증합니다.
    }

    // findAll() 메서드 테스트
    @Test
    public void findAll() {
        // given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // when
        List<Member> result = repository.findAll();

        // then
        assertThat(result.size()).isEqualTo(2); // 저장된 모든 회원을 조회한 결과의 크기가 2인지 검증합니다.
    }
}

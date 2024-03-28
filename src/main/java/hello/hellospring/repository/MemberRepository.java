package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

// 회원 데이터에 접근하기 위한 Repository 인터페이스
public interface MemberRepository {

    // 회원 저장
    Member save(Member member);

    // ID로 회원 조회
    Optional<Member> findById(Long id);

    // 이름으로 회원 조회
    Optional<Member> findByName(String name);

    // 모든 회원 조회
    List<Member> findAll();
}

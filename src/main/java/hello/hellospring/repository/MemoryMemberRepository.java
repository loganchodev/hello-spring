package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

// 메모리를 사용한 회원 저장소 구현 클래스
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); // 회원 정보를 저장할 Map
    private static long sequence = 0L; // 회원 ID를 생성하기 위한 변수

    // 회원 저장
    @Override
    public Member save(Member member) {
        member.setId(++sequence); // 회원 ID를 증가시키고
        store.put(member.getId(), member); // Map에 회원 정보를 저장
        return member; // 저장된 회원 객체 반환
    }

    // ID로 회원 조회
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // 주어진 ID에 해당하는 회원을 Optional로 반환
    }

    // 이름으로 회원 조회
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() // 저장된 회원 목록에서
                .filter(member -> member.getName().equals(name)) // 주어진 이름과 같은 회원을 필터링하여
                .findAny(); // 임의의 회원을 Optional로 반환
    }

    // 모든 회원 조회
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // 저장된 모든 회원을 리스트로 반환
    }

    // 저장소 초기화 메서드
    public void clearStore() {
        store.clear(); // 저장소를 비움
    }
}

package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;

    // EntityManager를 주입받는 생성자
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    // 회원 정보 저장
    @Override
    public Member save(Member member) {
        em.persist(member); // JPA를 사용하여 회원 정보를 영속화 (DB에 저장)
        return member;
    }

    // ID로 회원 조회
    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); // JPA를 사용하여 ID로 회원을 조회
        return Optional.ofNullable(member); // 조회된 회원을 Optional로 반환
    }

    // 이름으로 회원 조회
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList(); // JPQL을 사용하여 이름으로 회원을 조회

        return result.stream().findAny(); // 조회된 회원 중 임의의 한 명을 Optional로 반환
    }

    // 모든 회원 조회
    @Override
    public List<Member> findAll() {
        List<Member> result = em.createQuery("select m from Member m", Member.class).getResultList(); // JPQL을 사용하여 모든 회원을 조회
        return result; // 조회된 모든 회원을 반환
    }

}

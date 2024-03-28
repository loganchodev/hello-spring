package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    // 생성자를 통한 의존성 주입
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 가입 메서드
    public Long join(Member member) {
        long start = System.currentTimeMillis(); // 가입 처리 시작 시간 기록
        try {
            validateDuplicateMember(member); // 중복 회원 검증
            memberRepository.save(member); // 회원 저장
            return member.getId(); // 저장된 회원의 ID 반환
        } finally {
            long finish = System.currentTimeMillis(); // 가입 처리 종료 시간 기록
            long timeMs = finish - start; // 가입 처리 소요 시간 계산
            System.out.println("join = " + timeMs + "ms"); // 가입 처리 소요 시간 출력
        }
    }

    // 중복 회원 검증 메서드
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다."); // 중복 회원이 존재하면 예외 발생
                });
    }

    // 전체 회원 조회 메서드
    public List<Member> findMembers() {
        long start = System.currentTimeMillis(); // 조회 처리 시작 시간 기록
        try {
            return memberRepository.findAll(); // 모든 회원 조회
        } finally {
            long finish = System.currentTimeMillis(); // 조회 처리 종료 시간 기록
            long timeMs = finish - start; // 조회 처리 소요 시간 계산
            System.out.println("findMembers = " + timeMs + "ms"); // 조회 처리 소요 시간 출력
        }
    }

    // 특정 회원 조회 메서드
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId); // 주어진 ID에 해당하는 회원 조회
    }
}

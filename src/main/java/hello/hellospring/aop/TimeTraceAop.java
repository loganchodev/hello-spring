package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// 스프링 빈으로 등록되는 컴포넌트임을 나타내는 어노테이션
@Component
// Aspect 역할을 하는 클래스임을 나타내는 어노테이션
@Aspect
public class TimeTraceAop {

    // Around 어드바이스: 메소드 실행 전후에 처리를 추가하는 어드바이스
    // hello.hellospring 패키지 및 하위 패키지에 있는 모든 메소드를 대상으로 설정
    @Around("execution(* hello.hellospring..*(..))")
    // 프록시 객체인 ProceedingJoinPoint를 통해 타겟 메소드의 호출을 직접 제어할 수 있음
    public Object execut(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        // 메소드 호출 전 시간 기록
        System.out.println("START: " + joinPoint.toString());
        try {
            // 타겟 메소드 호출
            Object result = joinPoint.proceed();
            // 타겟 메소드 호출 결과 반환
            return result;
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            // 메소드 호출 후 시간 기록
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}

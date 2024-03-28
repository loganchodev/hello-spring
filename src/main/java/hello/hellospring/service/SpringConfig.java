package hello.hellospring.service;

import hello.hellospring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    // MemberService 빈 등록
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository()); // MemberRepository를 주입하여 MemberService 빈 생성
    }

    // MemberRepository 빈 등록
    @Bean
    public MemberRepository memberRepository() {
        // SpringConfig를 통해 주입받은 EntityManager를 사용하여 JpaMemberRepository 빈 생성
        return new JpaMemberRepository(em);
    }
}

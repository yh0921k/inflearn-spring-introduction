package study.spring_introduction;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.spring_introduction.repository.MemberRepository;
import study.spring_introduction.repository.MemoryMemberRepository;
import study.spring_introduction.service.MemberService;

@Configuration
public class SpringConfig {

  @Bean
  public MemberService memberService() {
    return new MemberService(memberRepository());
  }

  @Bean
  public MemberRepository memberRepository() {
    return new MemoryMemberRepository();
  }
}

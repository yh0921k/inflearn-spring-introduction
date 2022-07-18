package study.spring_introduction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.spring_introduction.repository.MemberRepository;
import study.spring_introduction.service.MemberService;

@Configuration
public class SpringConfig {

  private final MemberRepository memberRepository;

  @Autowired // 생성자가 하나인 경우 생략 가능
  public SpringConfig(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  @Bean
  public MemberService memberService() {
    return new MemberService(memberRepository);
  }

//  @Bean
//  public MemberRepository memberRepository() {
//    // return new MemoryMemberRepository();
//    // return new JdbcMemberRepository(dataSource);
//    // return new JdbcTemplateMemberRepository(dataSource);
//    // return new JpaMemberRepository(entityManager);
//  }
}

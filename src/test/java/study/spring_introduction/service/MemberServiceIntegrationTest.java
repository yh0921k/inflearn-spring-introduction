package study.spring_introduction.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.spring_introduction.domain.Member;
import study.spring_introduction.repository.MemberRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

  @Autowired MemberService memberService;
  @Autowired MemberRepository memberRepository;

  @Test
  void 회원가입() {
    // given
    Member member = new Member();
    member.setName("Spring");

    // when
    Long savedId = memberService.join(member);

    // then
    Member foundMember = memberService.findOne(savedId).get();
    assertThat(member.getName()).isEqualTo(foundMember.getName());
  }

  @Test
  public void 중복_회원_예외() {
    // given
    Member member1 = new Member();
    member1.setName("Spring");

    Member member2 = new Member();
    member2.setName("Spring");

    // when
    memberService.join(member1);

    // then
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> memberService.join(member2));
    assertThat(exception.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
  }
}

package study.spring_introduction.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import study.spring_introduction.domain.Member;
import study.spring_introduction.repository.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

  MemberService memberService;
  MemoryMemberRepository memberRepository;

  @BeforeEach
  public void beforeEach() {
    memberRepository = new MemoryMemberRepository();
    memberService = new MemberService(memberRepository);
  }

  @AfterEach
  public void afterEach() {
    memberRepository.clearStore();
  }

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

    //    try {
    //      memberService.join(member2);
    //      fail("사용자 중복 예외가 발생해야 합니다.");
    //    } catch (IllegalArgumentException exception) {
    //      Assertions.assertThat(exception.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    //    }

    // then
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> memberService.join(member2));
    assertThat(exception.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
  }

  @Test
  void 전체_사용자_조회() {}

  @Test
  void 단일_사용자_조회() {}
}

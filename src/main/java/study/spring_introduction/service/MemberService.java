package study.spring_introduction.service;

import org.springframework.transaction.annotation.Transactional;
import study.spring_introduction.domain.Member;
import study.spring_introduction.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

  private final MemberRepository memberRepository;

  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  /** 회원 가입 */
  public Long join(Member member) {

    long start = System.currentTimeMillis();
    try {
      validateDuplicatedMember(member); // 중복 회원 검증
      this.memberRepository.save(member);
      return member.getId();
    } finally {
      long finish = System.currentTimeMillis();
      long timeMs = finish - start;
      System.out.println("회원가입() : " + timeMs + "ms");
    }
  }

  private void validateDuplicatedMember(Member member) {
    memberRepository
        .findByName(member.getName())
        .ifPresent(
            m -> {
              throw new IllegalArgumentException("이미 존재하는 회원입니다.");
            });
  }

  /** 전체 회원 조회 */
  public List<Member> findMembers() {
    long start = System.currentTimeMillis();
    try {
      return memberRepository.findAll();
    } finally {
      long finish = System.currentTimeMillis();
      long timeMs = finish - start;
      System.out.println("전체회원조회() : " + timeMs + "ms");
    }
  }

  /** 단일 사용자 조회 */
  public Optional<Member> findOne(Long memberId) {
    return memberRepository.findById(memberId);
  }
}

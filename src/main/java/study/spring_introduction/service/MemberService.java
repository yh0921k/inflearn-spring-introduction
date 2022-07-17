package study.spring_introduction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.spring_introduction.domain.Member;
import study.spring_introduction.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

  private final MemberRepository memberRepository;

  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  /** 회원 가입 */
  public Long join(Member member) {
    validateDuplicatedMember(member); // 중복 회원 검증

    this.memberRepository.save(member);
    return member.getId();
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
    return memberRepository.findAll();
  }

  /** 단일 사용자 조회 */
  public Optional<Member> findOne(Long memberId) {
    return memberRepository.findById(memberId);
  }
}

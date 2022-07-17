package study.spring_introduction.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import study.spring_introduction.domain.Member;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {
  MemoryMemberRepository repository = new MemoryMemberRepository();

  @AfterEach
  public void afterEach() {
    // 각 테스트 메서드가 끝날 때마다 동작하는 콜백 메서드
    repository.clearStore();
  }

  @Test
  @DisplayName("사용자 생성 및 저장")
  public void save() {
    Member member = new Member();
    member.setName("Spring");

    repository.save(member);

    Member result = repository.findById(member.getId()).get();

    // org.junit.jupiter.api.Assertions
    // Assertions.assertEquals(member, result);

    // org.assertj.core.api.Assertions;
    // Assertions.assertThat(member).isEqualTo(result);

    // Static Import
    assertThat(member).isEqualTo(result);
  }

  @Test
  @DisplayName("이름으로 사용자 조회")
  public void findByName() {
    Member member1 = new Member();
    member1.setName("Spring 1");
    repository.save(member1);

    Member member2 = new Member();
    member2.setName("Spring 2");
    repository.save(member2);

    Member result = repository.findByName("Spring 1").get();
    assertThat(result).isEqualTo(member1);
    // assertThat(result).isEqualTo(member2);
  }

  @Test
  @DisplayName("전체 사용자 조회")
  public void findAll() {
    Member member1 = new Member();
    member1.setName("Spring 1");
    repository.save(member1);

    Member member2 = new Member();
    member2.setName("Spring 2");
    repository.save(member2);

    List<Member> result = repository.findAll();
    assertThat(result.size()).isEqualTo(2);
  }
}
package study.spring_introduction.repository;

import study.spring_introduction.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

  private final EntityManager entityManager;

  public JpaMemberRepository(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public Member save(Member member) {
    // 자동 생성되는 PK도 해당 객체에 초기화해줌
    entityManager.persist(member);
    return member;
  }

  @Override
  public Optional<Member> findById(Long id) {
    Member member = entityManager.find(Member.class, id);
    return Optional.ofNullable(member);
  }

  @Override
  public Optional<Member> findByName(String name) {
    // PK 기반이 아닌 데이터 연산에 대해 JPQL을 작성해야함
    List<Member> result = entityManager
        .createQuery("select m from Member m where m.name = :name", Member.class)
        .setParameter("name", name)
        .getResultList();

    return result.stream().findAny();
  }

  @Override
  public List<Member> findAll() {
    // JPQL 사용
    return entityManager.createQuery("select m from Member m", Member.class).getResultList();
  }
}

package study.spring_introduction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.spring_introduction.domain.Member;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

  @Override
  Optional<Member> findByName(String name);
}

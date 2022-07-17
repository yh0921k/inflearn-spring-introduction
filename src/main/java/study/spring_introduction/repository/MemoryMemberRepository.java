package study.spring_introduction.repository;

import org.springframework.stereotype.Repository;
import study.spring_introduction.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

  // 실무에서는 공유되는 변수라면 동시성 문제가 있을 수 있어 ConcurrentHashMap 등을 사용해야함
  private static Map<Long, Member> store = new HashMap<>();

  // 실무에서는 공유되는 변수라면 동시성 문제가 있을 수 있어 AtomicLong 등을 사용해야함
  private static long sequence = 0L;

  @Override
  public Member save(Member member) {
    member.setId(++sequence);
    store.put(member.getId(), member);
    return member;
  }

  @Override
  public Optional<Member> findById(Long id) {
    return Optional.ofNullable(store.get(id));
  }

  @Override
  public Optional<Member> findByName(String name) {
    return store.values().stream().filter(member -> member.getName().equals(name)).findAny();
  }

  @Override
  public List<Member> findAll() {
    return new ArrayList<>(store.values());
  }

  public void clearStore() {
    store.clear();
  }
}

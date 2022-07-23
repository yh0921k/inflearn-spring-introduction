package study.spring_introduction.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {

  // 패키지명, 클래스명, 파라미터 타입 등의 조합으로 적용 클래스를 설정할 수 있음
  // 현재 모든 클래스에 적용
  @Around("execution(* study.spring_introduction..*(..))")
  public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
    long start = System.currentTimeMillis();
    System.out.println("Start : " + joinPoint.toString());
    try {
      return joinPoint.proceed();
    } finally {
      long finish = System.currentTimeMillis();
      long timeMs = finish - start;
      System.out.println("End : " + joinPoint.toString() + " " + timeMs + "ms");
    }
  }
}

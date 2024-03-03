## Chapter 10

### Helper for Integration Test

Helper 객체 또한 역할 분리를 잘 해주어야하나 고민했다. \
예를 들어 '상황'을 설정해주는 Helper 와 '실행'을 검증하기 위한 조회 쿼리 Helper 등으로 나누어 사용하는 것과 같다.

### Testable LocalDateTime.now()

LocalDateTime.now() 를 사용하면 테스트 하기 어렵기에 TDD 하는 과정에서 당연히 기능 구현 시 단점이 부각될 것이다. \
한편 TDD 로 개발하지 않는다면 LocalDateTime.now() 를 사용하는 것이 더 편리하거나 당연할지도 모르겠다.

Testable 하게 LocalDateTime.now() 를 사용하는 방법으로 책에서는

1. 매개 변수로 LocalDateTime 객체 주입 하기 \
   isExpired() 메서드를 호출하는데 매개변수가 들어가는게 testable 하다는 것 이외의 어떤 장점이 있을까? \
2. 테스트 하는 입장에서 now() 호출을 통제할 수 있는 새로운 클래스 사용하기 \
   교재에서는 BizClock 이라는 객체를 소개하고 있다. 해당 클래스를 상속 받은 자식 클래스가 부모 클래스의 인스턴스를 원하는 방식대로 동작하도록 수정하는 게 핵심이다. \
   이 방법은 부모 클래스의 생명 주기와 초기화를 컨트롤해야한다는 점에서 불편할 것이다.
   또한 개인적으로는 자식이 부모 내부를 갈아끼는게 어색하다. 특히 Bean 으로 객체 컴포넌트를 관리하는 Spring 특성상 '이게 맞나?' 싶다.
   마지막으로 공식적인 클래스가 아닌 커스텀 클래스가 감히 데이터 모델링 영역에 들어온다는 게 어색하다. ㅋㅋㅋ

LocalDateTime.now() 메서드가 Clock 이라는 객체를 매개변수르 받는다는 점을 이용해, Clock 을 모킹하는 방법도 존재한다.
Clock 을 Test 를 고려한 설계라는 점과 Oracle 또한 Test 를 위해 Clock 객체를 사용하라고 명시한 점이 안심되는 부분이다.
하지만 이 또한, 코드레벨에서 LocalDateTime 하나 사용할 걸 Clock 과 새로운 객체 두 개를 사용하게 된다는 점이 불편했다.

차라리 테스트코드가 복잡하면 됐지 싶다고 생각해서 LocalDateTime을 mocking 하는 경우를 찾아보았다.
Mockito, PowerMockito 의 mockStatic 기능이 있다.
https://stackoverflow.com/questions/10583202/powermockito-mock-single-static-method-and-return-object

그럼에도 static method 를 mocking 하는 것이 좋지 않다는 의견이 많다.
무엇이 좋은 설계인지가 참 어렵다. 무엇을 공부해야할까?

여러모로 살펴봤을 때 now() 역할만 수행하는 static class 를 만들고, clock 를 주입받을 수 있도록 설계하고자 한다.

### Test with Clock

1. MockBean Clock
2. SpyBean Clock: Mockito-inline library 를 사용해야해서 조심스러움
3. TestConfiguration 으로 Bean
   Override https://stackoverflow.com/questions/62817126/mock-localdate-nowclock-using-clock-fixed

일련의 과정으로 Clock 을 모킹하는 방법을 시도해보았을 때,
Bean 객체를 mocking 하는 SpringBootTest 가 고작 Unit Test 에서 쓰일만큼 가볍지 않다는 것을 들었다.
결국 책에서 제시한 두 번째 방법이 최선이라고 결론 내렸다.

## Maintainable Test Code

1. 기댓값을 변수나 필드로 표현하지 않기
2. 두 개 이상을 검증하지 않기 (테스트는 항상 독립적으로)
3. 임의의 값에 일치하는 모의 객체
4. **과도하게 구현 검증하지 않기** // 아직 어렵다.
5. 서로 다른 테스트에 중복된 상황을 설정하지 않기 (테스트는 항상 독립적으로)
6. 실행환경이 다르다고 실패하지 않기 (경로, OS)
7. 실행 시점이 다르다고 실패하지 않기 (now(), 랜덤 값)
8. 검증에 필요하지 않은 값 설정하지 않기 => 빌더 패턴의 객체 생성 보조 클래스 사용하기.
9. 조건부 검증 피하기. 조건 또한 단언으로 검증하기 (assertTrue(string.contains('a')))
10. 통합 테스트 시 필요하지 않은 범위 연동하지 않기 @JdbcTest
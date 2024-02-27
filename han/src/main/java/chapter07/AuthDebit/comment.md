## Chapter 7

### Mocking

TDD 대상을 어디까지 생각해야할지 궁금해졌다. \
chapter 7의 예제가 제시된 순서는 Mocking 을 빠르게 소개하기 위함이겠지만, 자동이체 정보 등록 기능을 수행하는 객체를 먼저 제시했다.

데이터 모델링과 Entity 작성 정도는 어플리케이션과 독립적으로 수행하는게 맞을 것 같다. \
하지만 Repository 또는 Active Record 같은 데이터베이스 접근 객체는 TDD 를 통해 개발하는게 맞을까? \
Service Layer 부터는 TDD 를 수월하게 할 수 있을 것 같다. 하지만 그 전의 단게에서도 TDD 를 수행하는 것이 좋을까? 
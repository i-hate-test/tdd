## Mockito

### When & Why to use?

1. Fake 용도의 Class 를 만드는게 번거로운 경우
    1. TDD 를 수행하는 과정에서 Fake Class 를 만들면, 실제 제 역할을 하는 Class 는 우선 Interface 형태로 만들게 된다.
    2. Mockito 를 사용하면 Fake Class, 불필요한 Interface 를 만들 필요가 없어진다.
2. 호출 여부, 인자 매칭을 빠르게 구현할 수 있다.
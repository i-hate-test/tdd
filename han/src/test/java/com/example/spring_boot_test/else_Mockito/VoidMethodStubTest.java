package com.example.spring_boot_test.else_Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.mock;

import java.util.List;
import org.junit.jupiter.api.Test;

public class VoidMethodStubTest {
  @Test
  void voidMethodWillThrowTest() {
    List mockList = mock(List.class);
    willThrow(UnsupportedOperationException.class).given(mockList).clear();

    assertThrows(UnsupportedOperationException.class, mockList::clear);
  }
}

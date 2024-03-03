package chap02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordStrengthMeterTest {
    private PasswordStrengthMeter meter = new PasswordStrengthMeter();

    private void assertStrength(String password, PasswordStrength expStr){
        PasswordStrength result = meter.meter(password);
        assertEquals(expStr, result);
    }
    //모든 조건 충족
    @Test
    void meetsAllCriteria_Then_Strong(){
        assertStrength("ab12!@AB", PasswordStrength.STRONG);
    }

    //길이만 8글자 미만, 모든 조건 충족
    @Test
    void meetsOtherCriteria_except_for_Length_Then_Normal() {
        assertStrength("ab12!@A", PasswordStrength.NORMAL);
        assertStrength("Ab12!c", PasswordStrength.NORMAL);
    }

    //숫자 미포함, 모든 조건 충족
    @Test
    void meetsOtherCriteria_except_for_number_Then_Normaal(){
        assertStrength("ab!@ABqwer", PasswordStrength.NORMAL);
    }

    //값이 없는 경우
    @Test
    void nullInput_Then_Invalid(){
        assertStrength(null,PasswordStrength.INVALID);
    }

    //대문자를 포함하지 않고 나머지 조건을 충족하는 경우
    @Test
    void meetsOtherCriteria_except_for_Uppercase_Then_Normal(){
        assertStrength("ab12!@df",PasswordStrength.NORMAL);
    }

    //길이가 8글자 이상인 조건만 충족하는 경우
    @Test
    void meetsOnlyLengthCriteria_Then_Weak(){
        assertStrength("abdefghi",PasswordStrength.WEAK);
    }

    //숫자포함 조건만 충족하는 경우
    @Test
    void meetsOnlyNumCriteria_Then_Weak(){
        assertStrength("12345", PasswordStrength.WEAK);
    }

    //대문자포함 조건만 충족하는 경우
    @Test
    void meetsOnlyUpperCriteria_Then_Weak(){
        assertStrength("ABZEF", PasswordStrength.WEAK);
    }

    //아무조건도 충족하지 않은 경우
    @Test
    void meetsNoCriteria_Then_Weak(){
        assertStrength("abc", PasswordStrength.WEAK);
    }
}

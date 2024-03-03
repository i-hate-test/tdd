package chap08.테스트하고_싶은_코드를_분리하기;

import chap08.Grade;
import chap08.Product;
import chap08.Subscription;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static chap08.Grade.GOLD;
import static org.junit.jupiter.api.Assertions.assertEquals;

//테스트 하고 싶은 코드는 해당 코드를 별도 클래스로 분리하여 테스트 가능하게 바꿈
public class PointRule {
    public int calculate(Subscription s, Product p, LocalDate now){
        int point = 0;
        if(s.isFinished(now)){
            point += p.getDefaultPoint();
        }else {
            point += p.getDefaultPoint() +10;
        }
        if (s.getGrade()==GOLD){
            point +=100;
        }
        return point;
    }

    //아래와 같이 포인트 계산 기능만 테스트 가능
    @Test
    void 만료전_GOLD등급은_130포인트(){
        PointRule rule = new PointRule();
        Subscription s = new Subscription(
                LocalDate.of(2019,5,5),
                Grade.GOLD);
        Product p = new Product();
        p.setDefaultPoint(20);

        int point = rule.calculate(s, p, LocalDate.of(2019,5,1));

        assertEquals(130, point);
    }

    //포인트 계산 기능 자체를 대역으로 변경하고 싶다면 세터를 이용해서 의존 대상을 주입할 수 있게 하면 됨
    //ex)별도론 분리한 계산기능을 주입할 수 있는 세터 추가
        //테스트 코드에서 대역으로 계산기능을 대체할 수 있게 함
    private PointRule pointRule = new PointRule(); //기본 구현을 사용
    public void setPointRule(PointRule pointRule){
        this.pointRule = pointRule;
    }
}

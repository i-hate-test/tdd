package chap08.하드코딩된_상수를_생성자나_메서드_파라미터로받기_and_의존대상을_주입받기;

import chap08.PayInfoDao;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaySync {
    private PayInfoDao payInfoDao;

    //하드 코딩된 경로는 테스트 하기가 어려움
    private String filePath = "D:\\data\\pay\\cp0001.csv";
    //생성자나 세터를 이용해서 경로를 전달받는 식으로 테스트 하기 쉽게 만듦
    public void setFilePath(String filePath){
        this.filePath = filePath;
    }
    //의존 객체를 직접 생성하면 테스트 하기 어려움 (해당 의존객체(PayInfoDao)가 올바르게 동작하는데 필요한 모든 환경 구성해야 되므로)
    //따라서 의존 대상은 주입받을 수 있는 수단을 제공해서 교체 가능하게 바꿈
    //생성자를 통해 의존대상을 주입하게 수정해서 테스트 가능하게 함
/*    public PaySync(PayInfoDao payInfoDao) {
        this.payInfoDao = payInfoDao;
    }*/

    //만약 많은 레거시 코드에서 생성자 없는 버전 사용 시,
    //기존 코드는 그대로 유지하고 세터를 이용해서 의존대상을 교체할 수 있도록 수정
    public void setPayInfoDao(PayInfoDao payInfoDao){
        this.payInfoDao = payInfoDao;
    }

    public void sync() throws IOException {
        Path path = Paths.get(filePath);
    }

    //또는 하드코딩된 경로를 파라미터로 전달받아 테스트 가능하게 변경
    public void sync1(String filePath) throws IOException {
        Path path = Paths.get(filePath);
    }

    //세터 메서드로 테스트 코드
    @Test
    void someTest() throws IOException {
        PaySync paySync = new PaySync();
        paySync.setFilePath("src/test/resources/c0111.csv");

        paySync.sync();

        //이하 결과검증코드
    }

    //메서드 실행 시, 경로를 인자로 전달 받기
    @Test
    void someTest1() throws IOException {
        PaySync paySync = new PaySync();

        paySync.sync1("src/test/resources/c0111.csv");
        //이하 결과검증코드
    }

    //의존 대상 교체 할 수 있도록 코드 수정 후, 대역 사용하여 테스트 진행
    //대역 생성
    private MemoryPayInfoDao memoryPayInfoDao = new MemoryPayInfoDao();
    @Test
    void allDataSaved() throws IOException {
        PaySync paySync = new PaySync();
        paySync.setPayInfoDao(memoryPayInfoDao);//대역으로 교체
        paySync.setFilePath("src/test/resources/c0111.csv");
        paySync.sync();

        //대역을 이용한 검증
        List<PayInfo> savedInfos = memoryPayInfoDao.getAll();
        assertEquals(2, savedInfos.size());
    }
}

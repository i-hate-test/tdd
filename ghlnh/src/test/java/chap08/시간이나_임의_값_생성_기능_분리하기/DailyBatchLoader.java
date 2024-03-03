package chap08.시간이나_임의_값_생성_기능_분리하기;

import chap08.Times;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

//테스트 대상이 시간이나 임의 값을 사용하면 테스트 시점에 따라 테스트 결과가 달라짐
//->테스트 대상이 사용하는 시간이나 임의 값을 제공하는 기능을 별도로 분리해서 테스트 가능성을 높임
public class DailyBatchLoader {
    private String basePath = ".";
    //현재 일자를 구하는 Times클래스를 따로 생성
    private Times times = new Times();

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public void setTimes(Times times) {
        this.times = times;
    }

    public int load() {
        LocalDate date = times.today();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        Path batchPath = Paths.get(basePath, date.format(formatter), "batch.txt");

        //...batchPath에서 데이터를 읽어와 저장하는 코드
        try {
            return (int) Files.lines(batchPath).count();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //테스트에 사용할 파일을 src/test/resources/2019/01/01 폴더에 저장했다면
    //Times의 대역을 이용해 DailyBatchLoader가 사용할 일자를 지정 가능
    public class DailyBatchLoaderTest {
        private Times mockTimes = Mockito.mock(Times.class);
        private final DailyBatchLoader loader = new DailyBatchLoader();

        @BeforeEach
        void setUp() {
            loader.setBasePath("src/test/resources");
            loader.setTimes(mockTimes);
        }

        @Test
        void loadCount() {
            given(mockTimes.today()).willReturn(LocalDate.of(2019, 1, 1));

            int ret = loader.load();

            assertEquals(3, ret);
        }
    }
}



package chap09;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.jupiter.api.Assertions.assertEquals;


//WireMock을 이용한 REST 클라이언트 테스트
/*
WireMockServer의 사용법
* 테스트 실행 전에 WireMockServer를 시작한다. 실제 Http서버가 뜬다.
* 테스트에서 WireMockServer의 동작을 기술한다.
* Http연동을 수행하는 테스트를 실행한다.
* 테스트 실행 후에 WireMockServer를 중지한다.
*/

public class CardNumberValidatorTest {
    private WireMockServer wireMockServer;

    @BeforeEach
    void setUp(){
        wireMockServer = new WireMockServer(options().port(8089));
        wireMockServer.start();
    }

    @AfterEach
    void tearDown(){
        wireMockServer.stop();
    }

    @Test
    void valid() {
        //요청이 다음과 같다면
        //URL = "/card",  POST요청, 요청 몸체가 "1234567890"
        wireMockServer.stubFor(post(urlEqualTo("/card"))
                .withRequestBody(equalTo(("1234567890")))
                //응답은 Content-Type : text/plain 이고, 응답 몸체가 "ok"
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/plain")
                        .withBody("ok")));
        CardNumberValidator validator =
                new CardNumberValidator("http://localhost:8089");
        CardValidity validity = validator.validate("1234567890");
        assertEquals(CardValidity.VALID, validity);
    }

    //WireMockServer는 응답 시간 지연 가능 (아래는 5초 뒤에 응답 하도록 테스트 설정)
    @Test
    void timeout() {
        wireMockServer.stubFor(post(urlEqualTo("/card"))
                .willReturn(aResponse()
                        .withFixedDelay(50000)));

        CardNumberValidator validator =
                new CardNumberValidator("http://localhost:8089");
        CardValidity validity = validator.validate("1234567890");
        assertEquals(CardValidity.TIMEOUT,validity);
    }
}

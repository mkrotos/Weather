package com.krotos.weather;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.apache.http.protocol.HTTP;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Optional;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MeteoTrafficDataTestIT {
    private static final String TEST_URL = "/";
    private static final String MOCK_URL = "http://localhost:8089/";
    private static final String WRONG_URL = "wrong/url";

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    private MeteoTrafficData meteoTrafficData;

    @Before
    public void createMeteoDataBeforeEach(){
        meteoTrafficData = new MeteoTrafficData(MOCK_URL);
    }

    private void mockApiWithBody(String httpBody) {
        stubFor(get(urlEqualTo(TEST_URL))
                .willReturn(aResponse()
                        .withHeader(HTTP.CONTENT_TYPE, "text/plain")
                        .withBody(httpBody)));
    }

    @Test
    public void getBody() {
        //given
        String httpBody = "Temp";
        mockApiWithBody(httpBody);
        Optional<String> expectedBody = Optional.of("Temp");
        //when
        Optional<String> receivedData = meteoTrafficData.getData();

        //then
        assertEquals(expectedBody, receivedData);
    }

    @Test
    public void getWrongBody() {
        //given
        String httpBody = "Wrong body";
        mockApiWithBody(httpBody);
        Optional<String> notExpectedBody = Optional.of("Temp");
        //when
        Optional<String> receivedData = meteoTrafficData.getData();
        //then
        assertNotEquals(notExpectedBody, receivedData);
    }

    @Test
    public void returnEmptyOptionalWhenUrlIsWrong(){
        meteoTrafficData=new MeteoTrafficData(WRONG_URL);

        assertEquals(Optional.empty(),meteoTrafficData.getData());
    }



}

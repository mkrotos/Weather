package com.krotos.weather;

import com.google.common.io.Resources;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;


import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MeteoTrafficDataServiceTest {

    private static final String RAW_DATA_EXAMPLE_PATH = "RawDataExample";
    private static final String PROBLEM_WITH_EXAMPLE_FILE_MESSAGE = "Problem with example file";
    private MeteoTrafficDataService meteoTrafficDataService;
    private final double DELTA = 0.0001;

    private IMeteoTrafficData createMeteoDataMock() {
        IMeteoTrafficData dataMock = mock(IMeteoTrafficData.class);

        String rawData = readRawDataFile();

        when(dataMock.getData()).thenReturn(rawData);
        return dataMock;
    }

    private String readRawDataFile() {
        URL url = Resources.getResource(RAW_DATA_EXAMPLE_PATH);
        String rawData = null;
        try {
            rawData = Files.lines(Paths.get(url.toURI())).collect(Collectors.joining("\n"));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            System.out.println(PROBLEM_WITH_EXAMPLE_FILE_MESSAGE);
        }
        return rawData;
    }

    @Before
    public void beforeEach() {
        meteoTrafficDataService = MeteoTrafficDataService.createWith(createMeteoDataMock());
    }

    @Test
    public void returnsProperAirTemperature() {
        //given
        double expectedTemp = 6.9;
        //when
        double receivedTemp = meteoTrafficDataService.getTemp();
        //then
        assertEquals(expectedTemp, receivedTemp, DELTA);
    }

    @Test
    public void returnsProperHumidity() {
        //given
        double expectedHumidity = 69;
        //when
        double receivedHumidity = meteoTrafficDataService.getHumidity();
        //then
        assertEquals(expectedHumidity, receivedHumidity, DELTA);
    }

    @Test
    public void returnsProperRain() {
        //given
        String expectedRain = "brak opad&oacute;w";
        //when
        String receivedRain = meteoTrafficDataService.getRain();
        //then
        assertEquals(expectedRain, receivedRain);
    }

    @Test
    public void returnsProperWindVelocity() {
        //given
        double expectedWindVelocity = 0.6;
        //when
        double receivedWindVelocity = meteoTrafficDataService.getWindVelocity();
        //then
        assertEquals(expectedWindVelocity, receivedWindVelocity, DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionWhenNoDataIsProvided() {
        //throw when:
        meteoTrafficDataService = MeteoTrafficDataService.createWith(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwWhenDataIsWrongOnGetTemp(){
        //given
        createMeteoServiceWithWrongData();

        //throw when
        meteoTrafficDataService.getTemp();
    }
    @Test(expected = IllegalArgumentException.class)
    public void throwWhenDataIsWrongOnGetHumidity(){
        //given
        createMeteoServiceWithWrongData();

        //throw when
        meteoTrafficDataService.getHumidity();
    }
    @Test(expected = IllegalArgumentException.class)
    public void throwWhenDataIsWrongOnGetRain(){
        //given
        createMeteoServiceWithWrongData();

        //throw when
        meteoTrafficDataService.getRain();
    }
    @Test(expected = IllegalArgumentException.class)
    public void throwWhenDataIsWrongOnGetWindVelocity(){
        //given
        createMeteoServiceWithWrongData();

        //throw when
        meteoTrafficDataService.getWindVelocity();
    }

    private void createMeteoServiceWithWrongData() {
        meteoTrafficDataService=MeteoTrafficDataService.createWith(new IMeteoTrafficData() {
            @Override
            public String getData() {
                return "Some wrong data";
            }
        });
    }

}
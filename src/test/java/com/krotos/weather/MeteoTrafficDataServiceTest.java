package com.krotos.weather;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MeteoTrafficDataServiceTest {


     private static MeteoTrafficDataService meteoTrafficDataService;
     private final double DELTA = 0.0001;

     private static IMeteoTrafficData createMeteoDataMock() {
        IMeteoTrafficData dataMock = mock(IMeteoTrafficData.class);

        String rawData = readRawDataFileAdapter();

        when(dataMock.getData()).thenReturn(rawData);
        return dataMock;
    }

    private static String readRawDataFileAdapter() {
        String rawData = null;
        try {
            rawData = readRawDataFile();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Problem with RawDataExample file");
        }
        return rawData;
    }

    private static String readRawDataFile() throws IOException {
        String rawDataFilePath = "D:\\kody\\Java\\Projekty\\Weather\\src\\test\\resources\\RawDataExample";
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(rawDataFilePath));

        String lines;
        String rawData;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");

        try {
            while ((lines = reader.readLine()) != null) {
                stringBuilder.append(lines);
                stringBuilder.append(ls);
            }
            rawData= stringBuilder.toString();
        } finally {
            reader.close();
        }
        return rawData;
    }

    @BeforeClass
    public static void beforeAll() {
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
    public void returnsProperHumidity(){
        //given
        double expectedHumidity=69;
        //when
        double  receivedHumidity=meteoTrafficDataService.getHumidity();
        //then
        assertEquals(expectedHumidity,receivedHumidity,DELTA);
    }
    @Test
    public void returnsProperRain(){
        //given
        String expectedRain="brak opad&oacute;w";
        //when
        String  receivedRain=meteoTrafficDataService.getRain();
        //then
        assertEquals(expectedRain,receivedRain);
    }
    @Test
    public void returnsProperFixedRain(){
        //given
        String expectedFixedRain="brak opadów";
        //when
        String receivedFixedRain=meteoTrafficDataService.getFixedRain();
        //then
        assertEquals(expectedFixedRain,receivedFixedRain);
    }

    @Test
    public void returnsProperWindVelocity(){
        //given
        double expectedWindVelocity=0.6;
        //when
        double receivedWindVelocity=meteoTrafficDataService.getWindVelocity();
        //then
        assertEquals(expectedWindVelocity,receivedWindVelocity,DELTA);
    }

}
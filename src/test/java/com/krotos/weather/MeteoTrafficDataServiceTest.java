package com.krotos.weather;

import org.junit.BeforeClass;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MeteoTrafficDataServiceTest {

    //do osobnego pliku
    public static final String METEO_DATA_EXAMPLE = "\n" +
            "                    <div class=\"map-dialog-box\" state=\"\" zoom_min=\"15\">\n" +
            "                        <div class=\"txt\">\n" +
            "                            Temperatura powietrza: <b>13.8 &deg;C</b><br>\n" +
            "                            <!--Temperatura przy jezdni: <b>24.7 &deg;C</b><br>\n" +
            "                            Punkt rosy: <b>0.1 &deg;C</b><br>\n" +
            "                            Temperatura zamarzania: <b>-0.0 &deg;C</b><br>-->\n" +
            "                            Wilgotność: <b>39.0 %</b><br>\n" +
            "                            Temperatura pasa: <b>30.2 &deg;C</b><br>\n" +
            "                            Wilgotność pasa: <b>0.0 %</b><br>\n" +
            "                            Widoczność: <b>2000.0 m</b><br>\n" +
            "                            Rodzaj opadu: <b>brak opad&oacute;w</b><br>\n" +
            "                            <!--Wartość opadu: <b>0.0 mm</b><br>\n" +
            "                            Sól: <b>0.0 g/l</b><br>-->\n" +
            "                            Stan pasa: <b>sucho</b><br>\n" +
            "                            Kierunek wiatru: <b>58.0 &deg; NE</b><br>\n" +
            "                            Prędkość wiatru: <b>2.0 m/s</b><br>\n" +
            "    ";


    IMeteoDataService meteoTrafficDataService;

    public IMeteoTrafficData createMeteoDataMock(){
        IMeteoTrafficData dataMock=mock(IMeteoTrafficData.class);

        when(dataMock.getData()).thenReturn(METEO_DATA_EXAMPLE);
        return dataMock;
    }

    @BeforeClass
    public void beforeAll(){
        meteoTrafficDataService=MeteoTrafficDataService.createWith(createMeteoDataMock());
    }

}
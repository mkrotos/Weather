package com.krotos.weather;

import org.apache.commons.lang3.StringUtils;

public class MeteoTrafficDataService implements IMeteoDataService {

    private static final String TEMP_OPEN = "Temperatura powietrza: <b>";
    private static final String TEMP_CLOSE = " &deg;C</b>";
    private static final String HUMIDITY_OPEN = "Wilgotność: <b>";
    private static final String HUMIDITY_CLOSE = " %</b><br>";
    private static final String RAIN_OPEN = "Rodzaj opadu: <b>";
    private static final String RAIN_CLOSE = "</b><br>";
    private static final String WIND_VELOCITY_OPEN = "Prędkość wiatru: <b>";
    private static final String WIND_VELOCITY_CLOSE = " m/s</b><br>";

    private IMeteoTrafficData meteoData;
    private String response;

    private MeteoTrafficDataService(IMeteoTrafficData meteoData) {
        this.meteoData = meteoData;
        response=this.meteoData.getData();
    }

    public static MeteoTrafficDataService createWith(IMeteoTrafficData meteoData) {
        return new MeteoTrafficDataService(meteoData);
    }


    private String getStringFromResponseBetween(String open, String close) {
        return StringUtils.substringBetween(response, open, close);
    }

    public Double getTemp() {
        return Double.parseDouble(getStringFromResponseBetween(TEMP_OPEN, TEMP_CLOSE));
    }

    public Double getHumidity() {
        return Double.parseDouble(getStringFromResponseBetween(HUMIDITY_OPEN, HUMIDITY_CLOSE));
    }

    public String getRain() {
        return getStringFromResponseBetween(RAIN_OPEN, RAIN_CLOSE);
    }


    public Double getWindVelocity() {
        return Double.parseDouble(getStringFromResponseBetween(WIND_VELOCITY_OPEN, WIND_VELOCITY_CLOSE));
    }
}

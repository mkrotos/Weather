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
    private static final String WRONG_RAW_DATA_MESSAGE = "Wrong raw data";
    private static final String PARSE_DOUBLE_ERROR_MESSAGE = "Parse double error";
    private static final String NO_SUCH_DATA_MESSAGE = "No such data";

    private final String RESPONSE;

    private MeteoTrafficDataService(IMeteoTrafficData meteoData) {
        checkMeteoData(meteoData);
        RESPONSE = meteoData.getData().get();
    }

    private void checkMeteoData(IMeteoTrafficData meteoData) {
        if (meteoData == null || !meteoData.getData().isPresent()) {
            throw new IllegalArgumentException(WRONG_RAW_DATA_MESSAGE);
        }
    }

    public static MeteoTrafficDataService createWith(IMeteoTrafficData meteoData) {
        return new MeteoTrafficDataService(meteoData);
    }

    private String getStringFromResponseBetween(String open, String close) {
        return StringUtils.substringBetween(RESPONSE, open, close);
    }

    public Double getTemp() {
        String tempData = getStringFromResponseBetween(TEMP_OPEN, TEMP_CLOSE);
        checkBlankAndDoubleParsing(tempData);
        return Double.parseDouble(tempData);
    }

    public Double getHumidity() {
        String humidityData = getStringFromResponseBetween(HUMIDITY_OPEN, HUMIDITY_CLOSE);
        checkBlankAndDoubleParsing(humidityData);
        return Double.parseDouble(humidityData);
    }

    public String getRain() {
        String rainData = getStringFromResponseBetween(RAIN_OPEN, RAIN_CLOSE);
        checkBlank(rainData);
        return rainData;
    }


    public Double getWindVelocity() {
        String windVelocityData = getStringFromResponseBetween(WIND_VELOCITY_OPEN, WIND_VELOCITY_CLOSE);
        checkBlankAndDoubleParsing(windVelocityData);
        return Double.parseDouble(windVelocityData);
    }

    private void checkBlankAndDoubleParsing(String tempData) {
        checkBlank(tempData);
        try {
            Double.parseDouble(tempData);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(PARSE_DOUBLE_ERROR_MESSAGE);
        }
    }

    private void checkBlank(String tempData) {
        if (StringUtils.isBlank(tempData)) {
            throw new IllegalArgumentException(NO_SUCH_DATA_MESSAGE);
        }
    }
}

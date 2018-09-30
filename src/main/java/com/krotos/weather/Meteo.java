package com.krotos.weather;

public class Meteo {

    public static final String ALL_POINTS_BASE_URL = "http://traffic.erzeszow.pl/device/meteo/data/1";
    private static final String FIRST_POINT_URL = ALL_POINTS_BASE_URL + "/1";

    public static void main(String[] args) {
        meteo();
    }

    private static void meteo(){
        IMeteoDataService meteoDataService =MeteoTrafficDataService
                .createWith(new MeteoTrafficData(FIRST_POINT_URL));
        System.out.println(meteoDataService.getTemp());
        System.out.println(meteoDataService.getRain());
        System.out.println(meteoDataService.getHumidity());
        System.out.println(meteoDataService.getWindVelocity());

        AverageMeteoService averageMeteoService=new  AverageMeteoService(ALL_POINTS_BASE_URL,9);
        System.out.println(averageMeteoService.getTemp());
        System.out.println(averageMeteoService.getHumidity());
        System.out.println(averageMeteoService.getRain());
        System.out.println(averageMeteoService.getWindVelocity());

    }


}

package com.krotos.weather;

public class Meteo {

    public static void main(String[] args) {
        meteo();
    }

    private static void meteo(){
        MeteoTrafficDataService meteoDataService =MeteoTrafficDataService.createWith(new MeteoTrafficData());
        System.out.println(meteoDataService.getTemp());
        System.out.println(meteoDataService.getRain());
        System.out.println(meteoDataService.getHumidity());
        System.out.println(meteoDataService.getWindVelocity());


    }


}

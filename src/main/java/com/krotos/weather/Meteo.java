package com.krotos.weather;

public class Meteo {

    public static void main(String[] args) {
        meteo();
    }

    private static void meteo(){
        IMeteoDataService meteoDataService =MeteoTrafficDataService
                .createWith(new MeteoTrafficData("http://traffic.erzeszow.pl/device/meteo/data/1/1"));
        System.out.println(meteoDataService.getTemp());
        System.out.println(meteoDataService.getRain());
        System.out.println(meteoDataService.getHumidity());
        System.out.println(meteoDataService.getWindVelocity());

    }


}

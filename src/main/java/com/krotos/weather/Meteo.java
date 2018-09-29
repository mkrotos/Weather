package com.krotos.weather;

public class Meteo {

    public static void main(String[] args) {
        meteo();
    }

    private static void meteo(){
        IMeteoDataService IMeteoDataService =MeteoTrafficDataService.createWith(new MeteoTrafficTrafficData());
        System.out.println(IMeteoDataService.getTemp());
        System.out.println(IMeteoDataService.getRain());
        System.out.println(IMeteoDataService.getHumidity());
        System.out.println(IMeteoDataService.getWindVelocity());


    }


}

package com.krotos.weather;

public class Meteo {

    public static void main(String[] args) {
        meteo();
    }

    private static void meteo(){
        MeteoDataRefactor meteoDataRefactor=new MeteoDataRefactor();
        System.out.println(meteoDataRefactor.getTemp());
        System.out.println(meteoDataRefactor.getOpady());
        System.out.println(meteoDataRefactor.getWilgotnosc());
        System.out.println(meteoDataRefactor.getPredWiatru());


    }


}

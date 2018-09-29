package com.krotos.weather;

import org.apache.commons.lang3.StringUtils;

public class MeteoDataRefactor {

    private MeteoData meteoData=new MeteoData();
    private String response=meteoData.getData();

    private String getStringFromResponseBetween(String open, String close){
        return StringUtils.substringBetween(response,open,close);
    }

    public String getTemp(){
      return getStringFromResponseBetween("Temperatura powietrza: <b>"," &deg;C</b>");
    }
    public String getWilgotnosc(){
        return getStringFromResponseBetween("Wilgotność: <b>"," %</b><br>");
    }
    public String getOpady(){
        return getStringFromResponseBetween("Rodzaj opadu: <b>","</b><br>");
    }
    public String getPredWiatru(){
        return getStringFromResponseBetween("Prędkość wiatru: <b>"," m/s</b><br>");
    }
}

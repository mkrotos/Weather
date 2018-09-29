package com.krotos.weather;

public interface IMeteoDataService {
    Double getTemp();

    Double getHumidity();

    String getRain();

    Double getWindVelocity();
}

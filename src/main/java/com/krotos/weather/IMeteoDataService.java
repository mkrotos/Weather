package com.krotos.weather;

interface IMeteoDataService {
    Double getTemp();

    Double getHumidity();

    String getRain();

    Double getWindVelocity();
}

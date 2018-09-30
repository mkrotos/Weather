package com.krotos.weather;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

public class MeteoTrafficData implements IMeteoTrafficData {

    private final String METEO_RZESZOW_API_URL;
    private final String UTF_8 = "UTF-8";

    public MeteoTrafficData(String METEO_RZESZOW_API_URL) {
        this.METEO_RZESZOW_API_URL = METEO_RZESZOW_API_URL;
    }

    public Optional<String> getData() {
        URI uri;
        try {
            uri = new URI(METEO_RZESZOW_API_URL);
            return Optional.ofNullable(IOUtils.toString(uri, UTF_8));
        } catch (URISyntaxException | IOException | IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}

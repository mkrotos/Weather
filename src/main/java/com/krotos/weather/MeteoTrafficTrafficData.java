package com.krotos.weather;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MeteoTrafficTrafficData implements IMeteoTrafficData {

    private static final String METEO_RZESZOW_API_URL = "http://traffic.erzeszow.pl/device/meteo/data/1/1";
    private static final String UTF_8 = "UTF-8";

    public  String getData() {
        URI uri;
        try {
            uri = new URI(METEO_RZESZOW_API_URL);
            return IOUtils.toString(uri, UTF_8);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

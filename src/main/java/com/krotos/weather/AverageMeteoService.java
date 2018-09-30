package com.krotos.weather;

import java.util.*;
import java.util.stream.Collectors;

public class AverageMeteoService implements IMeteoDataService {

    private String meteoDataUrlBase;
    private int numberOfMeteoPoints;
    private MeteoTrafficDataService meteoTrafficDataService;

    public AverageMeteoService(String meteoDataUrlBase, int numberOfMeteoPoints) {
        this.meteoDataUrlBase = meteoDataUrlBase;
        this.numberOfMeteoPoints = numberOfMeteoPoints;
    }

    @Override
    public Double getTemp() {
        List<Double> tempList = new ArrayList<>();
        for (int i = 1; i <= numberOfMeteoPoints; i++) {
            createDataServiceForPoint(i);
            tempList.add(meteoTrafficDataService.getTemp());
        }
        return tempList.stream().mapToDouble(l -> l).average().getAsDouble();
    }

    private void createDataServiceForPoint(int i) {
        IMeteoTrafficData meteoTrafficData = new MeteoTrafficData(meteoDataUrlBase + "/" + i);
        meteoTrafficDataService = MeteoTrafficDataService.createWith(meteoTrafficData);
    }

    @Override
    public Double getHumidity() {
        List<Double> humidityList = new ArrayList<>();
        for (int i = 1; i <= numberOfMeteoPoints; i++) {
            createDataServiceForPoint(i);
            humidityList.add(meteoTrafficDataService.getHumidity());
        }
        return humidityList.stream().mapToDouble(l -> l).average().getAsDouble();
    }

    @Override
    public String getRain() {
        Map<String, Integer> rainMap = new HashMap<>();

        for (int i = 1; i <= numberOfMeteoPoints; i++) {
            createDataServiceForPoint(i);
            String actualRain = meteoTrafficDataService.getRain();
            if (rainMap.containsKey(actualRain)) {
                Integer actualValue = rainMap.get(actualRain) + 1;
                rainMap.put(actualRain, actualValue);
            } else {
                rainMap.put(actualRain, 0);
            }
        }

        Set<String> mostFrequentlyRainSet = findKeysWithBiggestValues(rainMap);

        return refactorSetToString(mostFrequentlyRainSet);
    }

    private String refactorSetToString(Set<String> mostFrequentlyRainSet) {
        String mostFrequentlyRain="";
        if (mostFrequentlyRainSet.size() == 1) {
             mostFrequentlyRain = (String) mostFrequentlyRainSet.toArray()[0];

        } else {
            for (String rain : mostFrequentlyRainSet) {
                mostFrequentlyRain+="/"+rain;
            }
        }
        return mostFrequentlyRain;
    }

    private Set<String> findKeysWithBiggestValues(Map<String, Integer> rainMap) {
        Integer maxValue = rainMap.values().stream()
                .max(Integer::compareTo).get();
        return rainMap.entrySet().stream()
                .filter(entry -> entry.getValue().equals(maxValue))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    @Override
    public Double getWindVelocity() {
        List<Double> windVeloList = new ArrayList<>();
        for (int i = 1; i <= numberOfMeteoPoints; i++) {
            createDataServiceForPoint(i);
            windVeloList.add(meteoTrafficDataService.getWindVelocity());
        }

        return windVeloList.stream().mapToDouble(l -> l).average().getAsDouble();
    }
}

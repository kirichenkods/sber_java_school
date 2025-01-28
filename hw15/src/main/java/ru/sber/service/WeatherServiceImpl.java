package ru.sber.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.sber.weatherdata.Weather;

import java.io.IOException;

@Component
public class WeatherServiceImpl implements WeatherService {
    @Value("${weather.api.key}")
    private String weatherApiKey;
    @Override
    public Weather getWeatherInCity(String city) {
        String url = "https://api.weatherapi.com/v1/current.json?key=" +
                weatherApiKey +
                "&q=" + city + "&aqi=no";

        String responseJSON = getResponse(url);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return objectMapper.readValue(responseJSON, Weather.class);
        } catch (JsonProcessingException e) {
            System.err.println("Не удалось обработать JSON ответа " + e.getMessage());
        }
        return new Weather();
    }

    private static String getResponse(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.body() != null) {
                return response.body().string();
            }
        } catch (IOException e) {
            System.err.println("Ошибка в обработке ответа " + e.getMessage());
        }

        return "";
    }
}

package ru.sber;

import ru.sber.service.WeatherService;
import ru.sber.service.WeatherServiceImpl;
import ru.sber.ui.UserInterface;
import ru.sber.ui.UserInterfaceConsole;
import ru.sber.weatherdata.Weather;

public class App {
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterfaceConsole();
        String city = userInterface.getCity();
        WeatherService weatherService = new WeatherServiceImpl();
        Weather currentWeather = weatherService.getWeatherInCity(city);
        userInterface.showCurrentWeather(currentWeather);
    }
}

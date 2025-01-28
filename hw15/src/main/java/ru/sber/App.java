package ru.sber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.sber.service.WeatherService;
import ru.sber.service.WeatherServiceImpl;
import ru.sber.ui.UserInterface;
import ru.sber.ui.UserInterfaceConsole;
import ru.sber.weatherdata.Weather;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        ConfigurableApplicationContext factory = SpringApplication.run(App.class);

        WeatherService weatherService = factory.getBean(WeatherServiceImpl.class);
        UserInterface userInterface = factory.getBean(UserInterfaceConsole.class);

        String city = userInterface.getCity();
        Weather currentWeather = weatherService.getWeatherInCity(city);
        userInterface.showCurrentWeather(currentWeather);
    }
}

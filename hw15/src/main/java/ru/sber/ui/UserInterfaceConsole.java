package ru.sber.ui;

import org.springframework.stereotype.Component;
import ru.sber.weatherdata.Weather;

import java.util.Scanner;

@Component
public class UserInterfaceConsole implements UserInterface {
    @Override
    public String getCity() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите город:");
        return scanner.nextLine();
    }

    @Override
    public void showCurrentWeather(Weather weather) {
        if (weather.getCurrent() != null && weather.getLocation() != null) {
            System.out.println("Температура в городе " + weather.getLocation().getName() + " " + weather.getCurrent().getTemp_c());
            System.out.println("Облачность в процентах " + weather.getCurrent().getCloud());
        } else {
            System.out.println("Не удалось получить данные о погоде в указанном городе");
        }
    }
}

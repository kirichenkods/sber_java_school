package ru.sber.ui;

import ru.sber.weatherdata.Weather;

/**
 * Интерфейс для взаимодействия с пользователем.
 */
public interface UserInterface {
    /**
     * Запрашивает у пользователя название города.
     * @return Название города, введенное пользователем.
     */
    String getCity();

    /**
     * Отображает текущую погоду в заданном городе.
     * @param weather Объект {@link Weather}, содержащий информацию о погоде.
     */
    void showCurrentWeather(Weather weather);
}

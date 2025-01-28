package ru.sber.service;

import ru.sber.weatherdata.Weather;

/**
 * Интерфейс для получения информации о погоде.
 */
public interface WeatherService {
    /**
     * Получение текущих погодных условий в указанном городе.
     * @param city Название города, для которого нужно получить информацию о погоде.
     * @return Объект {@link Weather}, содержащий текущие погодные условия в указанном городе.
     */
    Weather getWeatherInCity(String city);
}

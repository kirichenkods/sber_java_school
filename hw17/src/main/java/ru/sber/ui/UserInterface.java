package ru.sber.ui;

public interface UserInterface {
    /**
     * Выводит главное меню
     */
    void showMenu();

    /**
     * Показывает сообщение пользователю
     * @param message сообщение, которое будет выведено
     */
    void showMessage(String message);

    /**
     * Получить пользовательский ввод
     */
    String getUserInput();
}

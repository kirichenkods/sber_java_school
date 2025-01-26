package task1.Interfaces;

public interface UserInterface {
    /**
     * Получает данные от пользователя
     * @return String
     */
    String readUserData();

    /**
     * Отправляет сообщение пользователю
     * @param message String
     */
    void showMessage(String message);

    /**
     * Получает от пользователя число
     * нужен для получения суммы для снятия/пополнения
     * @return double
     */
    double getAmount();
}

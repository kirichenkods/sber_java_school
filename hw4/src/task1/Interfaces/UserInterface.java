package task1.Interfaces;

public interface UserInterface {
    /**
     * получает данные от пользователя
     * @return String
     */
    String readUserData();

    /**
     * отпарвляет сообщение пользователю
     * @param message String
     */
    void showMessage(String message);

    /**
     * получает от пользователя число
     * нужен для получения суммы для снятия/пополнения
     * @return double
     */
    double getAmount();
}

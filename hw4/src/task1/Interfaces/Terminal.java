package task1.Interfaces;

public interface Terminal {
    /**
     * Проверка баланса
     */
    void checkAccount();

    /**
     * Снять наличные
     * @param money
     */
    void withdrawMoney(double money);

    /**
     *
     * Пополнить счет
     * @param money
     */
    void putMoney(double money);

    /**
     * Метод запускающий работу терминала
     */
    void doWork();
}

package task1.Interfaces;

public interface Terminal {
    /**
     * проверка баланса
     */
    void checkAccount();

    /**
     * снять наличные
     * @param money
     */
    void withdrawMoney(double money);

    /**
     * пополнить счет
     * @param money
     */
    void putMoney(double money);

    /**
     * метод запускающий работу терминала
     */
    void doWork();
}

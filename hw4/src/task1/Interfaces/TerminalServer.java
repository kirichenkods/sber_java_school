package task1.Interfaces;

import task1.Exceptions.IncorrectMoneyInputException;
import task1.Exceptions.NotEnoughMoneyException;

public interface TerminalServer {
    /**
     * Снимает переданную сумму со счета
     * @param money
     * @throws NotEnoughMoneyException
     * @throws IncorrectMoneyInputException
     */
    void withdrawMoney(double money) throws NotEnoughMoneyException, IncorrectMoneyInputException;

    /**
     * Метод пополняет счет на переданную сумму
     * @param money
     * @throws IncorrectMoneyInputException
     */
    void putMoney(double money) throws IncorrectMoneyInputException;

    /**
     * Проверка счета
     * @return остаток на счете
     */
    double checkAccount();
}

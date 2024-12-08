package task1.Impl;

import task1.Exceptions.IncorrectMoneyInputException;
import task1.Exceptions.NotEnoughMoneyException;
import task1.Interfaces.TerminalServer;

public class TerminalServerImpl implements TerminalServer {
    private double account;

    public TerminalServerImpl() {
        this.account = 0.0d;
    }

    public double checkAccount() {
        return account;
    }

    public void putMoney(double money) throws IncorrectMoneyInputException {
        if ((money % 100.0) != 0) {
            throw new IncorrectMoneyInputException("Сумма должна быть кратна 100!");
        }
        this.account = account + money;
    }

    public void withdrawMoney(double money) throws NotEnoughMoneyException, IncorrectMoneyInputException {
        if ((money % 100.0) != 0) {
            throw new IncorrectMoneyInputException("Сумма должна быть кратна 100!");
        }
        if (money > account) {
            throw new NotEnoughMoneyException("Недостачно средств!");
        }
        this.account = account - money;
    }
}

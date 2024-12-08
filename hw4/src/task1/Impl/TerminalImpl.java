package task1.Impl;

import task1.Enums.Commands;
import task1.Exceptions.IncorrectInputException;
import task1.Exceptions.IncorrectMoneyInputException;
import task1.Exceptions.NotEnoughMoneyException;
import task1.Interfaces.PinValidator;
import task1.Interfaces.Terminal;
import task1.Interfaces.TerminalServer;
import task1.Interfaces.UserInterface;

import java.util.Arrays;
import java.util.List;

public class TerminalImpl implements Terminal {
    private final PinValidator pinValidator;
    private final TerminalServer server;
    private final UserInterface userInterface;

    public TerminalImpl(UserInterface userInterface, String pin) {
        this.userInterface = userInterface;
        this.server = new TerminalServerImpl();
        this.pinValidator = new PinValidatorImpl(userInterface, pin);
    }

    @Override
    public void checkAccount() {
        userInterface.showMessage("баланс равен " + server.checkAccount() + "\n");
    }

    @Override
    public void withdrawMoney(double money) {
        try {
            server.withdrawMoney(money);
        } catch (NotEnoughMoneyException | IncorrectMoneyInputException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void putMoney(double money) {
        try {
            server.putMoney(money);
        } catch (IncorrectMoneyInputException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * главный метод, в котором меняется состояние работы терминала
     */
    @Override
    public void doWork() {
        Commands cmd = Commands.MAIN_MENU;
        //терминал работает, пока не получит от пользователя команду на выход
        //каждый метод в case возвращает команду-состояние
        while (!cmd.equals(Commands.EXIT)) {
            switch (cmd) {
                case GET_PIN -> cmd = getResultCheckPin();
                case ACCESS -> cmd = getResultMenuAccess();
                case BLOCK -> cmd = getResultCheckBlock();
                case CHECK_ACCOUNT -> cmd = getResultBeforeCheckAccount();
                case WITHDRAW_MONEY -> cmd = getResultBeforeWithdrawMoney();
                case PUT_MONEY -> cmd = getResultBeforePutMoney();
                case MAIN_MENU -> cmd = getResultFromMainMenu();
            }
        }
    }

    /**
     * пополнение счета
     * возвращает в меню доступа к счету
     */
    private Commands getResultBeforePutMoney() {
        String message = "введите сумму для пополнения счета\n" +
                "Сумма должна быть кратна 100";
        userInterface.showMessage(message);
        double amount = userInterface.getAmount();
        if (amount > 0) {
            putMoney(amount);
        }
        return Commands.ACCESS;
    }

    /**
     * снятие наличных
     * возвращает в меню доступа к счету
     */
    private Commands getResultBeforeWithdrawMoney() {
        String message = "введите сумму для снятия\n" +
                "Сумма должна быть кратна 100";
        userInterface.showMessage(message);
        double amount = userInterface.getAmount();
        if (amount > 0) {
            withdrawMoney(amount);
        }
        return Commands.ACCESS;
    }

    /**
     * выводит меню доступа к счету
     *
     * @return EXIT - выход
     * CHECK_ACCOUNT - проверка счета
     * WITHDRAW_MONEY - снять деньги
     * PUT_MONEY - пополнить счет
     * MAIN_MENU - если пользователь ввел неверный код
     */
    private Commands getResultMenuAccess() {
        String message =
                "Введите " + Commands.CHECK_ACCOUNT.getCmd() + " для проверки баланса\n" +
                        "Введите " + Commands.WITHDRAW_MONEY.getCmd() + " для снятия наличных\n" +
                        "Введите " + Commands.PUT_MONEY.getCmd() + " для пополнения счета\n" +
                        "Введите " + Commands.EXIT.getCmd() + " для выхода";
        userInterface.showMessage(message);
        String cmd = userInterface.readUserData();
        try {
            checkInputCommand(cmd, Arrays.asList(Commands.CHECK_ACCOUNT,
                    Commands.WITHDRAW_MONEY, Commands.PUT_MONEY, Commands.EXIT));
            return Commands.getCommandByValue(cmd);
        } catch (IncorrectInputException e) {
            System.err.println(e.getMessage());
        }

        return Commands.ACCESS;
    }

    /**
     * сообщяет пользователю состояние счета
     * возвращает состояние меню доступа к счету
     *
     * @return Commands.ACCESS
     */
    private Commands getResultBeforeCheckAccount() {
        checkAccount();

        return Commands.ACCESS;
    }

    /**
     * главное меню
     * возвращает
     *
     * @return EXIT - выход
     * GET_PIN - получение пин-кода
     * MAIN_MENU если пользователь вводит неправильный код
     */
    private Commands getResultFromMainMenu() {
        String welcomeMessage =
                "Для ввода пин-кода введите " + Commands.GET_PIN.getCmd() + "\n" +
                        "Для выхода введите " + Commands.EXIT.getCmd();
        userInterface.showMessage(welcomeMessage);

        //получение команды от пользователя
        String cmd = userInterface.readUserData();
        try {
            checkInputCommand(cmd, Arrays.asList(Commands.GET_PIN, Commands.EXIT));
            return Commands.getCommandByValue(cmd);
        } catch (IncorrectInputException e) {
            System.err.println(e.getMessage());
        }
        return Commands.MAIN_MENU;
    }

    /**
     * проверяет блокировку терминала
     * возвращает состояние после проверки блокировки
     *
     * @return BLOCK - терминал заблокирован, MAIN_MENU - возврат в главное меню
     */
    private Commands getResultCheckBlock() {
        if (pinValidator.isBlock()) {
            return Commands.BLOCK;
        }
        return Commands.MAIN_MENU;
    }

    /**
     * проверяет пин-код
     * возвращает состояние после проверки пин-код
     *
     * @return GET_PIN - если пин введен неверно, но количество попыток не закончилось
     * ACCESS - если пин введен верно
     * BLOCK - терминал заблокирован на определенное время
     */
    private Commands getResultCheckPin() {
        if (pinValidator.isPinCorrect()) {
            return Commands.ACCESS;
        }
        if (pinValidator.isBlock()) {
            return Commands.BLOCK;
        }
        return Commands.GET_PIN;
    }

    /**
     * проверка введенной команды
     * команда должна соответствовать команде из списка, иначе выбросит исключение
     *
     * @param cmd      введенная команда
     * @param commands список команд
     */
    private void checkInputCommand(String cmd, List<Commands> commands) throws IncorrectInputException {
        if (!commands.contains(Commands.getCommandByValue(cmd))) {
            throw new IncorrectInputException("Вводите только предложенные команды");
        }
    }
}

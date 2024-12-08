package task1.Enums;

/**
 * список команд для терминала
 */
public enum Commands {
    EXIT("0"), //выход
    GET_PIN("1"), //получение пин-кода
    CHECK_ACCOUNT("2"), //проверка счета
    WITHDRAW_MONEY("3"), //снять деньги
    PUT_MONEY("4"),  //пополнить счет
    ACCESS("5"), //доступ к операциям
    BLOCK("6"), //доступ заблокирован
    MAIN_MENU("7"); //главное меню

    private final String cmd;

    Commands(String cmd) {
        this.cmd = cmd;
    }

    public String getCmd() {
        return cmd;
    }

    /**
     * по переданному значению возвращает соответствующую команду
     *
     * @param command
     * @return
     */
    public static Commands getCommandByValue(String command) {
        Commands[] commands = Commands.values();
        for (Commands cmd : commands) {
            if (cmd.getCmd().equals(command)) {
                return cmd;
            }
        }
        return null;
    }
}

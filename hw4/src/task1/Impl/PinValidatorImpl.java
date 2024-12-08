package task1.Impl;

import task1.Exceptions.AccountIsLockedException;
import task1.Interfaces.PinValidator;
import task1.Interfaces.UserInterface;

public class PinValidatorImpl implements PinValidator {
    private final UserInterface userInterface;
    private final String pin;
    private int countAttempts;
    private boolean isBlock;
    private long lockedStart;
    private static final int ATTEMPTS_LIMIT = 3;
    private static final int BLOCK_TIME = 10;

    public PinValidatorImpl(UserInterface userInterface, String pin) {
        this.userInterface = userInterface;
        this.pin = pin;
    }

    @Override
    public boolean isPinCorrect() {
        String pinCode = getPinCode();
        //если пин введен неверно, то увеличить счетчик попыток ввода
        if (!pin.equals(pinCode)) {
            userInterface.showMessage("неверно введен пин-код!");
            countAttempts++;
            return false;
        }
        return true;
    }

    /**
     * получает пин-код от пользователя
     * @return String pin-code
     */
    private String getPinCode() {
        userInterface.showMessage("введите пин-код");
        StringBuilder input = new StringBuilder();
        while (input.length() < pin.length()) {
            Integer val;
            try {
                val = Integer.parseInt(userInterface.readUserData());
                input.append(val);
            } catch (NumberFormatException e) {
                System.err.println("Введите число");
            }
        }
        return input.toString();
    }

    /**
     * проверка на блокировку терминала
     */
    @Override
    public boolean isBlock() {
        // сначала проверить состояние блокировки
        try {
            doCheckBlock();
        } catch (AccountIsLockedException e) {
            System.err.println(e.getMessage());;
        }
        return this.isBlock;
    }

    /**
     * проверка блокировки
     * блокирует терминал, если количество попыток исчерпано
     * разблокирует терминал, если время блокировки вышло
     */
    private void doCheckBlock() throws AccountIsLockedException {
        if (isBlock) {
            try {
                userInterface.readUserData();
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }

            long differenceTime = (long) ((System.currentTimeMillis() - lockedStart) / 1000.0);
            // если время вышло, то снять блокировку, иначе сообщить время до окончания блокировки
            if (differenceTime < BLOCK_TIME) {
                throw new AccountIsLockedException("До окончания блокировки осталось " +
                        (BLOCK_TIME - differenceTime) + " секунд");
            } else {
                this.isBlock = false;
                countAttempts = 0;
            }
        }
        if (countAttempts >= ATTEMPTS_LIMIT) {
            isBlock = true;
            lockedStart = System.currentTimeMillis();
            throw new AccountIsLockedException("Ваш аккаунт заблокирован на " + BLOCK_TIME + " секунд");
        }
    }
}
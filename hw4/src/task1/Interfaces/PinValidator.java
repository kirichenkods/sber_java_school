package task1.Interfaces;

public interface PinValidator {
    /**
     * проверка введенного пароля
     * @return
     */
    boolean isPinCorrect();

    /**
     * проверяет заблокирован ли терминал
     * @return
     */
    boolean isBlock();
}

package task1.Interfaces;

public interface PinValidator {
    /**
     * Проверка введенного пароля
     * @return
     */
    boolean isPinCorrect();

    /**
     * Проверяет заблокирован ли терминал
     * @return
     */
    boolean isBlock();
}

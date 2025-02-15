package ru.sber.ui;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class UserInterfaceConsole implements UserInterface {
    @Override
    public void showMenu() {
        System.out.println(
                """
                        Введите 0 для выхода
                        Введите 1 для поиска рецепта
                        Введите 2 для удаления рецепта
                        Введите 3 для добавления или редактирования рецепта
                        """
        );
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}

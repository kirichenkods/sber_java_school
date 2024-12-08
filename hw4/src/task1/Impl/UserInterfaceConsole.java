package task1.Impl;

import task1.Interfaces.UserInterface;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class UserInterfaceConsole implements UserInterface {
    @Override
    public String readUserData() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (!input.isEmpty()) {
            return input.substring(0, 1);
        }
        return "";
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public double getAmount() {
        Scanner scanner = new Scanner(System.in);
        double amount = 0.0;
        try {
            amount = scanner.nextDouble();
        } catch (NoSuchElementException e) {
            System.err.println("Неверный ввод");
        }
        return amount;
    }
}

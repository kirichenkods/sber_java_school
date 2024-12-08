package task1;

import task1.Impl.TerminalImpl;
import task1.Impl.UserInterfaceConsole;
import task1.Interfaces.Terminal;
import task1.Interfaces.UserInterface;

public class TermApp {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        UserInterface userInterface = new UserInterfaceConsole();
        String pin = "1234";
        Terminal terminal = new TerminalImpl(userInterface, pin);
        terminal.doWork();
    }
}

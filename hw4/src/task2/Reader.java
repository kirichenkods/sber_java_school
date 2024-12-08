package task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.net.URL;
import java.util.Scanner;

public class Reader {
    public static void main(String[] args) {
//        String url = "https://developer.mozilla.org/ru/";
//        String url = "https://developer.mozillaorg/ru/";

        System.out.println("введите адрес url:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        boolean isError = false;

        while (true) {
            try {
                System.out.println(readContent(input));
            } catch (UnknownHostException | MalformedURLException e) {
                System.err.println("ошибка в адресе " + e);
                isError = true;
            } catch (IOException e) {
                System.err.println("Ошибка ввода " + e);
                isError = true;
            }
            finally {
                if (isError) {
                    System.out.println("Повторите ввод, или введите 0 для выхода: ");
                    input = scanner.nextLine();
                    if (input.equals("0")) {
                        break;
                    } else {
                        isError = false;
                    }
                } else {
                    break;
                }
            }
        }
    }

    public static String readContent(String url) throws IOException {
        StringBuilder result = new StringBuilder();
        URL uniformResourceLocator = new URL(url);
        BufferedReader reader = new BufferedReader(new InputStreamReader(uniformResourceLocator.openStream()));
        String input = "";
        while ((input = reader.readLine()) != null) {
            result.append(input);
        }
        reader.close();
        return result.toString();
    }
}

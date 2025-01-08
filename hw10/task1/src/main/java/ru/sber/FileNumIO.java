package ru.sber;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для чтения и записи числовых данных в файл.
 */
public class FileNumIO {
    private final String fileName;

    public FileNumIO(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Записывает список целых чисел в указанный файл.
     * @param list Список целых чисел для записи в файл.
     */
    public void writeNumbersToFile(List<Integer> list) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (Integer integer : list) {
                writer.write(integer + "\n");
            }
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    /**
     * Считывает целые числа из указанного файла и возвращает их в виде списка.
     * @return Список целых чисел, считанных из файла.
     */
    public List<Integer> readNumbersFromFile() {
        List<Integer> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении из файла: " + e.getMessage());
        }
        return list;
    }
}

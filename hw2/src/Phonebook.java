import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * телефонный справочник
 */
public class Phonebook {
    private final Map<String, HashSet<String>> container = new HashMap<>();

    /**
     * добавляет запись в справочник
     * если такая фамилия уже есть, то добавить номер к уже существующим
     */
    public void add(String surname, String number) {
        HashSet<String> numbers = new HashSet<>();

        if (!container.containsKey(surname)) {
            numbers.add(number);
        } else {
            numbers = container.get(surname);
            numbers.add(number);
        }

        container.put(surname, numbers);
    }

    /**
     * возвращает номера из справочника по фамилии,
     * если записей нет, то вернется пустая коллекция
     */
    public Set<String> get(String surname) {
        return container.containsKey(surname) ? container.get(surname) : new HashSet<>();
    }
}

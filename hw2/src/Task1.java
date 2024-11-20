import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Objects;

public class Task1 {
    public static void main(String[] args) {
        String[] currencies = new String[]{"Lira", "Euro", "Dollar", "Dollar",
                "Ruble", "Dollar", "Peso", "Won", "Ruble", "Lira", "Euro",
                "Real", "Peso", "Ruble", "Riyal", "Won", "Franc"};

        System.out.println("уникальные значения:");
        Set<String> uniqueWords = new HashSet<>(List.of(currencies));
        System.out.println(uniqueWords);

        System.out.println("количество вхождений каждого слова:");
        Map<String, Integer> counterWords = getCountWords(currencies);
        System.out.println(counterWords);
    }

    /**
     * метод принимает массив строк и возвращает Map, содеражащую уникальные слова
     * из этого массива (в ключах) и количество их вхождений
     */
    public static Map<String, Integer> getCountWords(String[] words) {
        // если массив пуст, то сразу выходим
        if (words.length == 0) {
            return new HashMap<>();
        }
        Map<String, Integer> result = new HashMap<>();
        for (String word : words) {
            //если слово ещё не встречалось, то вернет 0,
            // иначе количество раз, которое это слово уже встречалось
            int count = Objects.isNull(result.get(word)) ? 0 : result.get(word);
            //увеличить счетчик повторов перед записью
            result.put(word, ++count);
        }

        return result;
    }
}

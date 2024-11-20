public class Task2 {
    public static void main(String[] args) {
        Phonebook phonebook = new Phonebook();

        phonebook.add("Ivanov", "+79001234567");
        phonebook.add("Petrov", "+79111114567");
        phonebook.add("Petrov", "+79111114567");
        phonebook.add("Ivanov", "+79001112233");
        phonebook.add("Semenov", "+79012224588");

        System.out.println("Petrov " + phonebook.get("Petrov"));
        System.out.println("Ivanov " + phonebook.get("Ivanov"));
        System.out.println("Semenov " + phonebook.get("Semenov"));
        System.out.println("Stepanov " + phonebook.get("Stepanov"));
    }
}

package task2;

public class Employee extends Person {
    public Employee(String name, int age) {
        super(name, age);
    }

    public Employee(String name, int age, boolean male) {
        super(name, age, male);
    }

    public String print() {
        return getName() + " " + getAge();
    }
}


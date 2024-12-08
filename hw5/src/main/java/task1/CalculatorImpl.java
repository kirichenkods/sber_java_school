package task1;

public class CalculatorImpl implements Calculator {
    @Override
    public int calc(int number) {
//        System.out.println("считаем факториал " + number);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }

        if (number < 1) {
            throw new IllegalArgumentException("отрицательный аргумент!");
        }
        return (number == 1) ? 1 : number * calc(number - 1);
    }
}

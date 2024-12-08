package task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import task2.Employee;
import task2.Person;
import task2.ReflectionHelper;
import task4.Week;
import task5.CachedInvocationHandler;
import task6.PerformanceProxy;
import task7.BeanUtils;

import java.lang.reflect.Proxy;
import java.time.LocalDateTime;

import static java.lang.reflect.Proxy.newProxyInstance;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HWTest {

    @Test
    void task1Test() {
        Calculator calculator = new CalculatorImpl();
        assertEquals(40320, calculator.calc(8));
        assertEquals(1, calculator.calc(1));
        assertEquals(120, calculator.calc(5));
    }

    @Test
    void task2Test() {
        Class<Employee> employeeClass = Employee.class;
        ReflectionHelper.printDeclaredMethods(employeeClass);
    }

    @Test
    void task3Test() {
        Class<Person> personClass = Person.class;
        ReflectionHelper.printGetters(personClass);
    }

    @Test
    void task4Test() {
        Class<Week> weekClass = Week.class;

        try {
            System.out.println(ReflectionHelper.isConstNameEqualsValue(weekClass));
        } catch (IllegalAccessException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    void task5Test() {
        Calculator calculator = new CalculatorImpl();
        Calculator proxyCalculator = (Calculator) newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                calculator.getClass().getInterfaces(),
                new CachedInvocationHandler(calculator));

        System.out.println("START: " + LocalDateTime.now());
        System.out.println("факториал 1 = " + proxyCalculator.calc(1));
        System.out.println("факториал 5 = " + proxyCalculator.calc(5));
        System.out.println("факториал 6 = " + proxyCalculator.calc(6));
        System.out.println("факториал 1 = " + proxyCalculator.calc(1));
        System.out.println("факториал 1 = " + proxyCalculator.calc(1));
        System.out.println("факториал 5 = " + proxyCalculator.calc(5));
        System.out.println("факториал 1 = " + proxyCalculator.calc(1));
        System.out.println("END: " + LocalDateTime.now());
    }

    @Test
    void task6Test() {
        Calculator calculator1 = (Calculator) Proxy.newProxyInstance(
                CalculatorImpl.class.getClassLoader(),
                new Class[]{Calculator.class},
                new PerformanceProxy(new CalculatorImpl()));
        System.out.println(calculator1.calc(3));

        Calculator calculator2 = PerformanceProxy.create(new CalculatorImpl(), Calculator.class);
        System.out.println(calculator2.calc(3));

    }

    @Test
    void task7Test() {
        Person person1 = new Employee("Tom", 20, true);
        Person person2 = new Employee("Ann", 40, false);
        Assertions.assertNotEquals(person1, person2);
        BeanUtils.assign(person1, person2);
        Assertions.assertEquals(person1, person2);
    }
}
package task2;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public final class ReflectionHelper {
    private ReflectionHelper() {
    }

    /**
     * Выводит на консоль все методы класса, включая все родительские методы
     * (включая приватные)
     * @param clazz
     */
    public static void printDeclaredMethods(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods){
            System.out.println(method.getName());
        }
        if (clazz.getSuperclass() != null) {
            printDeclaredMethods(clazz.getSuperclass());
        }
    }

    /**
     * Выводит на консоль все геттеры класса
     * @param clazz
     */
    public static void printGetters(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().startsWith("get") || method.getName().startsWith("is")) {
                System.out.println(method.getName());
            }
        }
    }

    /**
     * Проверяет что все String константы имеют значение = их имени
     * @param clazz
     * @throws IllegalAccessException
     */
    public static boolean isConstNameEqualsValue(Class<?> clazz) throws IllegalAccessException {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if ( !(field.getType().equals(String.class) &&
                    Modifier.isFinal(field.getModifiers()) &&
                    field.getName() == field.get(null)) ) {
                return false;
            }
        }
        return true;
    }
}

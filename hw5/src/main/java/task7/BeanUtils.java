package task7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class BeanUtils {
    /**
     * Scans object "from" for all getters. If object "to"
     * contains correspondent setter, it will invoke it
     * to set property value for "to" which equals to the property
     * of "from".
     * <p/>
     * The type in setter should be compatible to the value returned
     * by getter (if not, no invocation performed).
     * Compatible means that parameter type in setter should
     * be the same or be superclass of the return type of the getter.
     * <p/>
     * The method takes care only about public methods.
     *
     * @param to   Object which properties will be set.
     * @param from Object which properties will be used to get values.
     */
    public static void assign(Object to, Object from) {
        List<Method> methods = getGetters(from);
        for (Method method : methods) {
            Method methodTo = getEqualsSetterByGetter(to, method);
            if (methodTo != null) {
                try {
                    methodTo.invoke(to, method.invoke(from));
                } catch (IllegalAccessException | InvocationTargetException e) {
                    System.err.println(e.getMessage());;
                }
            }
        }
    }

    /**
     * ищет соответсвующий сеттер у объекта to, по названию переданного геттера getter
     * @param to
     * @param getter
     * @return
     */
    private static Method getEqualsSetterByGetter(Object to, Method getter) {
        Method[] methods = to.getClass().getMethods();
        String methodName = "";
        if (getter.getName().startsWith("get")) {
            methodName = "set" + getter.getName().replace("get", "");
        } else if (getter.getName().startsWith("is")) {
            methodName = "set" + getter.getName().replace("is", "");
        }

        for (Method meth : methods) {
            if (meth.getName().equals(methodName) &&
                    meth.getParameterTypes()[0].equals(getter.getReturnType()) &&
                    meth.getParameterCount() == 1) {
                return meth;
            }
        }
        return null;
    }

    /**
     * возвращает геттеры переданного объекта
     * @param from
     * @return
     */
    private static List<Method> getGetters(Object from) {
        Method[] methods = from.getClass().getMethods();
        return Arrays.stream(methods)
                .filter(method -> method.getName().startsWith("get") ||
                        method.getName().startsWith("is"))
                .toList();
    }
}
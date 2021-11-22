package com.ifmo.lesson19;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/*
Могут пригодиться следующие параметры JVM:
--add-opens=java.base/java.util=ALL-UNNAMED
 */
public class ReflectionUtils {
    /**
     * Используя reflection рекурсивно найдите все поля объекта и приведите их к строке.
     * Например, для класса {@link ToStringObject} со следующими значениями:
     * <pre>
     *         ToStringObject obj1 = new ToStringObject("a", 1, List.of("b", "c"), null);
     *         ToStringObject obj2 = new ToStringObject("d", 2, List.of("e", "f"), obj1);
     *
     *         System.out.println(ReflectionUtils.toString(obj1));
     *         System.out.println(ReflectionUtils.toString(obj2));
     * </pre>
     *
     * вывод будет следующим:
     * <pre>
     *     ToStringObject={val1=a,val2=1,val3=List12={e0=b,e1=c},another=null}
     *     ToStringObject={val1=d,val2=2,val3=List12={e0=e,e1=f},another=ToStringObject={val1=a,val2=1,val3=List12={e0=b,e1=c},another=null}}
     * </pre>
     *
     * @param obj Объект, из которого будет построена строка.
     * @return Строка.
     */
    public static String toString(Object obj) {
        var aClass = obj.getClass();
        Field[] fields = aClass.getDeclaredFields();
        var vs = Arrays.stream(fields).map(field -> {
            String str = null;
            field.setAccessible(true);
            try {
                Object value = field.get(obj);
                if (value == null)
                {
                    str = field.getName() + "=null";
                } if (value instanceof ToStringObject stringObject){
                    str = field.getName() + "=" + "ToStringObject={"+ toString(stringObject) +"}";
                }
                else {
                    str = field.getName() + "=" + value;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return str;
        }).toArray(String[]::new);
        String str1 = String.join(",", vs);

        return str1;
    }

    /**
     * Метод создаёт полную копию объекта, включая все его зависимости.
     *
     * @param src Объект, с которого будет создаваться копия.
     * @param <T>
     * @return Полная копия объекта.
     */
    public static <T> T deepCopy(T src) throws InstantiationException, IllegalAccessException {
        var aClass = src.getClass();
        Field[] fields = aClass.getDeclaredFields();
        T t = (T) aClass.newInstance();
        for(Field field: fields){
            field.setAccessible(true);
            Object value = field.get(src);
            field.set(t, value);
        }

        return t;
    }
}

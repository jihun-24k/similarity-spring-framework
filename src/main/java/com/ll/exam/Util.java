package com.ll.exam;

import javax.management.Descriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Util {
    public static class reflection{
        public static <T> T getFieldValue(Object o, String fieldName, T defaultValue) {
            Field field = null;

            try {
                field = o.getClass().getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                return defaultValue;
            }

            field.setAccessible(true);

            try {
                return (T)field.get(o);
            } catch (IllegalAccessException e) {
                return defaultValue;
            }
        }
    }

    public static class cls{
        public static <T> T newObj(Class cls, Object defaultValue){
            try {
                return (T) cls.getDeclaredConstructor().newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    };

    public static class str{
        //앞단의 문자를 소문자로 바꿔주는 메서드
        public static String decapitalize(String str) {
            if (str == null || str.length() == 0) {
                return str;
            }

            char c[] = str.toCharArray();
            c[0] = Character.toLowerCase(c[0]);

            return new String(c);
        }
    }
}

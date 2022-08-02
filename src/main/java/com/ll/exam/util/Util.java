package com.ll.exam.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.management.Descriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

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
        // uriStr 처리
        public static String beforeFrom(String str, String fromStr, int matchCount) {
            StringBuilder sb = new StringBuilder();

            String[] bits = str.split(fromStr);

            for (int i = 0; i < matchCount; i++) {
                sb.append(bits[i]);
                if ( i + 1 < matchCount ) {
                    sb.append(fromStr);
                }
            }

            return sb.toString();
        }
    }
    public static Map<String, Object> mapOf(Object... args) {
        Map<String, Object> map = new LinkedHashMap<>();

        for (int i = 0; i<args.length; i+=2){
            String key = (String)args[i];
            Object value = args[i+1];

            map.put(key,value);
        }
        return map;
    }

    public static class json {
        private static final ObjectMapper om;

        static {
            om = new ObjectMapper();
        }

        public static String toStr(Object obj, String defaultValue) {
            try {
                return om.writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                return defaultValue;
            }
        }

        public static <T> T toObj(String jsonStr, Class<T> cls, T defaultValue) {
            try {
                return (T) om.readValue(jsonStr, cls);
            } catch (JsonProcessingException e) {
                return defaultValue;
            }
        }

        public static <T> T toObj(String jsonStr, TypeReference<T> typeReference, T defaultValue) {
            try {
                return om.readValue(jsonStr, typeReference);
            } catch (JsonProcessingException e) {
                return defaultValue;
            }
        }

    }
}

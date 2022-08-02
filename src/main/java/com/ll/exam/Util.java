package com.ll.exam;

public class Util {
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

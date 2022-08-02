package com.ll.exam.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) //이 어노테이션의 타입은 메서드 (메서드에 붙는 어노테이션이다)
@Retention(RetentionPolicy.RUNTIME) // 자세히 모름
public @interface GetMapping {
    String value(); //어노테이션을 달, value를 입력가능하게 함, 지금은 String
}

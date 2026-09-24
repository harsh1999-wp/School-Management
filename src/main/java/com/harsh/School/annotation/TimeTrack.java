package com.harsh.School.annotation;


import java.lang.annotation.*;

@Target(ElementType.METHOD) // only applied for methods if not it will applied for any place
@Retention(RetentionPolicy.RUNTIME)// will stay for that preiod of time
@Documented
public @interface TimeTrack  {

    long warnAfter() default 1000;

    String operation() default "";

}


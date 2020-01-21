package com.github.microwww.bitcoin.annotation;

import java.lang.annotation.*;

@Documented
@Target(value = {ElementType.METHOD, ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Version {
    String since() default "0.0.1";

    String warn();

    String end();
}

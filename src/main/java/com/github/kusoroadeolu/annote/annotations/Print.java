package com.github.kusoroadeolu.annote.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(Prints.class)
public @interface Print {
    String value();
    String type() default "string";
    int order();
}

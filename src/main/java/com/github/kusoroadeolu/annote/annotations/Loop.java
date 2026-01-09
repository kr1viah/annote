package com.github.kusoroadeolu.annote.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(Loops.class)
public @interface Loop {
    String value() default "true";
    int order();
}

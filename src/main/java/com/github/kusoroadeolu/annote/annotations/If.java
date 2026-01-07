package com.github.kusoroadeolu.annote.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(Ifs.class)
public @interface If {
    String value();
}

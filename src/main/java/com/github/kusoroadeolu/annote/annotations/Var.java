package com.github.kusoroadeolu.annote.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(Vars.class)  // Points to the container
public @interface Var {
    String name(); //Name of the variable
    String value();
    String type();
}

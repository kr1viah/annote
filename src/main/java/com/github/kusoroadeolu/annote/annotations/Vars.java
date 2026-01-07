package com.github.kusoroadeolu.annote.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Vars {
    Var[] value();  // This is what holds multiple @Var annotations
}
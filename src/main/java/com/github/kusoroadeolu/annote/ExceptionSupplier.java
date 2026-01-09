package com.github.kusoroadeolu.annote;

import com.github.kusoroadeolu.annote.exception.AnnoteException;

public interface ExceptionSupplier<E> {
    E supply() throws Exception;

    static <E> E run(ExceptionSupplier<E> supplier){
        try {
            return supplier.supply();
        }catch (Exception e){
            throw new AnnoteException(e);
        }
    }
}

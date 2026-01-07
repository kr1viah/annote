package com.github.kusoroadeolu.annote;

public interface ExceptionSupplier<E> {
    E supply() throws Exception;

    static <E> E run(ExceptionSupplier<E> supplier){
        try {
            return supplier.supply();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}

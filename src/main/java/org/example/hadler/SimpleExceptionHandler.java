package org.example.hadler;

import java.util.function.Consumer;

public class SimpleExceptionHandler<I extends Throwable> implements ExceptionHandler<I>{
    private final Consumer<I> operation;

    public SimpleExceptionHandler(Consumer<I> operation) {
        this.operation = operation;
    }

    @Override
    public void handle(I input) {
        operation.accept(input);
    }

    public static <I extends Throwable> SimpleExceptionHandler<I> of(Consumer<I> operation){
        return new SimpleExceptionHandler<>(operation);
    }
}

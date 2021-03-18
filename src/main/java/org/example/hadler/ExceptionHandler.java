package org.example.hadler;

import java.util.function.Consumer;

public interface ExceptionHandler<I extends Throwable>{
    void handle(I input);
}

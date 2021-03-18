package org.example.tester.chain;

import org.example.hadler.SimpleExceptionHandler;
import org.example.testAndHandler.TestAndHandle;
import org.example.tester.Tester;

import java.util.function.Consumer;
import java.util.function.Function;

public interface TesterChain extends Tester<Throwable> {
    <I extends Throwable> TesterChain custom(Function<I,Boolean> functionToApply);
    <I extends Throwable> TesterChain isClassOf(Class<I> clazz);
    <I extends Throwable> TesterChain isNotClassOf(Class<I> clazz);

    default <I extends Throwable> TestAndHandle<I> withHandler(Consumer<I> operation){
        return new TestAndHandle<>(this, SimpleExceptionHandler.of(operation));
    }

}

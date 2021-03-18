package org.example.testAndHandler;

import org.example.hadler.ExceptionHandler;
import org.example.tester.chain.TesterChain;

public class TestAndHandle<I extends Throwable> {
    private final TesterChain testerChain;
    private final ExceptionHandler<I> handler;

    public TestAndHandle(TesterChain testerChain, ExceptionHandler<I> handler) {
        this.testerChain = testerChain;
        this.handler = handler;
    }

    public TesterChain getTestChain() {
        return testerChain;
    }

    public ExceptionHandler<I> getHandler() {
        return handler;
    }
}

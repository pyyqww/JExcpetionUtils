package org.example.testAndHandler;

import java.util.LinkedList;
import java.util.List;

public class TestAndHandlerChain {
    private final List<TestAndHandle<Throwable>> list;

    public TestAndHandlerChain(List<TestAndHandle<Throwable>> list) {
        this.list = list;
    }

    public <I extends Throwable> TestAndHandlerChain add(TestAndHandle<I> chain) {
        List<TestAndHandle<Throwable>> newList = new LinkedList<>(list);
        newList.add((TestAndHandle<Throwable>) chain);
        return new TestAndHandlerChain(newList);
    }

    public static <I extends Throwable> TestAndHandlerChain of(TestAndHandle<I> chain) {
        List<TestAndHandle<Throwable>> newList = new LinkedList<>();
        newList.add((TestAndHandle<Throwable>) chain);
        return new TestAndHandlerChain(newList);
    }

    public <I extends Throwable> void testAndHandle(I input){
        for(TestAndHandle<Throwable> t : list) {
            if (t.getTestChain().test(input)) {
                t.getHandler().handle(input);
                return;
            }
        }
        throw new RuntimeException("");
    }
}

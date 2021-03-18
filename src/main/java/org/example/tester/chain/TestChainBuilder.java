package org.example.tester.chain;

import org.example.tester.*;

import java.util.LinkedList;
import java.util.function.Function;

public class TestChainBuilder {
    public static TesterChain skip(){
        LinkedList<Tester<Throwable>> list = new LinkedList<>();
        list.add(SkipTester.of());
        return new AndTesterChain(list);
    }

    public static TesterChain takeAnyway(){
        LinkedList<Tester<Throwable>> list = new LinkedList<>();
        list.add(TakeAnywayTester.of());
        return new AndTesterChain(list);
    }

    public static AndTesterChain andIsClassOf(Class<?> clazz){
        LinkedList<Tester<Throwable>> list = new LinkedList<>();
        list.add(IsClassOfTester.of(clazz));
        return new AndTesterChain(list);
    }

    public static AndTesterChain andIsNotClassOf(Class<?> clazz){
        LinkedList<Tester<Throwable>> list = new LinkedList<>();
        list.add(IsNotClassOfTester.of(clazz));
        return new AndTesterChain(list);
    }

    public static <T extends Throwable> AndTesterChain andCustomTester(Function<T, Boolean> functionToApply){
        LinkedList<Tester<Throwable>> list = new LinkedList<>();
        list.add(CustTester.of(functionToApply));
        return new AndTesterChain(list);
    }

    public static OrTesterChain orIsClassOf(Class<?> clazz){
        LinkedList<Tester<Throwable>> list = new LinkedList<>();
        list.add(IsClassOfTester.of(clazz));
        return new OrTesterChain(list);
    }

    public static OrTesterChain orIsNotClassOf(Class<?> clazz){
        LinkedList<Tester<Throwable>> list = new LinkedList<>();
        list.add(IsNotClassOfTester.of(clazz));
        return new OrTesterChain(list);
    }

    public static <T extends Throwable> OrTesterChain orCustomTester(Function<T, Boolean> functionToApply){
        LinkedList<Tester<Throwable>> list = new LinkedList<>();
        list.add(CustTester.of(functionToApply));
        return new OrTesterChain(list);
    }
}

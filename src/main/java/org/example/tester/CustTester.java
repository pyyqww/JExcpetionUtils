package org.example.tester;

import java.util.function.Function;

public class CustTester implements Tester<Throwable>{
    private final Function<Throwable, Boolean> functionToApply;
    private CustTester(Function<Throwable, Boolean> functionToApply){
        this.functionToApply = functionToApply;
    }

    @Override
    public Boolean test(Throwable o) {
        return functionToApply.apply(o);
    }

    public static <T extends Throwable> CustTester of(Function<T, Boolean> functionToApply){
        return new CustTester((Function<Throwable, Boolean>) functionToApply);
    }
}

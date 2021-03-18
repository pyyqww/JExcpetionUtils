package org.example.tester.chain;

import org.example.tester.*;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class AndTesterChain extends TesterChainImpl {
    protected AndTesterChain(List<Tester<Throwable>> list) {
        super(list);
    }

    @Override
    public <I extends Throwable> TesterChain custom(Function<I, Boolean> functionToApply) {
        List<Tester<Throwable>> tempList = new LinkedList<>(getList());
        tempList.add(CustTester.of((Function<Throwable, Boolean>) functionToApply));
        return new AndTesterChain(tempList);
    }

    @Override
    public <I extends Throwable> TesterChain isClassOf(Class<I> clazz) {
        List<Tester<Throwable>> tempList = new LinkedList<>(getList());
        tempList.add(IsClassOfTester.of(clazz));
        return new AndTesterChain(tempList);
    }

    @Override
    public <I extends Throwable> TesterChain isNotClassOf(Class<I> clazz) {
        List<Tester<Throwable>> tempList = new LinkedList<>(getList());
        tempList.add(IsNotClassOfTester.of(clazz));
        return new AndTesterChain(tempList);
    }

    @Override
    public Boolean test(Throwable t) {
        boolean result = false;
        for(Tester<Throwable> tester : getList()){
            if(!tester.test(t))
                return false;

            result = true;
        }

        return result;
    }
}

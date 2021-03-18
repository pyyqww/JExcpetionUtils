package org.example.tester.chain;

import org.example.tester.*;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class OrTesterChain extends TesterChainImpl {
    protected OrTesterChain(List<Tester<Throwable>> list) {
        super(list);
    }

    @Override
    public <I extends Throwable> TesterChain custom(Function<I, Boolean> functionToApply) {
        List<Tester<Throwable>> tempList = new LinkedList<>(getList());
        tempList.add(CustTester.of((Function<Throwable, Boolean>) functionToApply));
        return new OrTesterChain(tempList);
    }

    @Override
    public <I extends Throwable> TesterChain isClassOf(Class<I> clazz) {
        List<Tester<Throwable>> tempList = new LinkedList<>(getList());
        tempList.add(IsClassOfTester.of(clazz));
        return new OrTesterChain(tempList);
    }

    @Override
    public <I extends Throwable> TesterChain isNotClassOf(Class<I> clazz) {
        List<Tester<Throwable>> tempList = new LinkedList<>(getList());
        tempList.add(IsNotClassOfTester.of(clazz));
        return new OrTesterChain(tempList);
    }

    @Override
    public Boolean test(Throwable t) {
        for(Tester<Throwable> tester : getList()){
            if(tester.test(t))
                return true;
        }

        return false;
    }
}

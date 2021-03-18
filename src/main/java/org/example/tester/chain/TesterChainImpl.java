package org.example.tester.chain;

import org.example.tester.Tester;

import java.util.List;

public abstract class TesterChainImpl implements TesterChain {
    private final List<Tester<Throwable>> list;

    public List<Tester<Throwable>> getList() {
        return list;
    }

    protected TesterChainImpl(List<Tester<Throwable>> list) {
        this.list = list;
    }


}

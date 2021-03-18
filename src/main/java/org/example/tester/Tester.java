package org.example.tester;

public interface Tester<I extends Throwable> {
    Boolean test(I t);
}

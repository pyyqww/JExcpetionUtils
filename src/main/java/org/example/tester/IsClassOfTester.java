package org.example.tester;

public class IsClassOfTester<T extends Throwable> implements Tester<T>{
    private final Class<?> clazz;
    private IsClassOfTester(Class<?> clazz){
        this.clazz = clazz;
    }

    @Override
    public Boolean test(T o) {
        if(o==null) return false;
        return this.clazz.isAssignableFrom(o.getClass());
    }

    public static <T extends Throwable> IsClassOfTester<T> of(Class<?> clazz){
        return new IsClassOfTester<>(clazz);
    }
}

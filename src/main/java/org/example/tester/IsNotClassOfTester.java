package org.example.tester;

public class IsNotClassOfTester implements Tester<Throwable>{
    private final Class<?> clazz;
    private IsNotClassOfTester(Class<?> clazz){
        this.clazz = clazz;
    }

    @Override
    public Boolean test(Throwable o) {
        if(o==null) return false;
        return !this.clazz.isAssignableFrom(o.getClass());
    }

    public static IsNotClassOfTester of(Class<?> clazz){
        return new IsNotClassOfTester(clazz);
    }
}

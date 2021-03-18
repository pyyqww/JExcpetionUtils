package org.example.tester;

public class TakeAnywayTester implements Tester<Throwable>{
    private static final TakeAnywayTester _instance = new TakeAnywayTester();
    private TakeAnywayTester(){
    }

    @Override
    public Boolean test(Throwable o) {
        return true;
    }
    
    public static TakeAnywayTester of(){
        return _instance;
    }
}

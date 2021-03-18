package org.example.tester;

public class SkipTester implements Tester<Throwable>{
    private static SkipTester _instance = new SkipTester();
    private SkipTester(){
    }

    @Override
    public Boolean test(Throwable o) {
        return false;
    }

    public static SkipTester of(){
        return _instance;
    }
}

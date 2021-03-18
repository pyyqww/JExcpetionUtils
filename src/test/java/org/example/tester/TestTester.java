package org.example.tester;

import org.example.testAndHandler.TestAndHandlerChain;
import org.example.tester.chain.TesterChain;
import org.example.tester.chain.TestChainBuilder;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

public class TestTester {

    public static class A extends Exception{}
    public static class B extends Exception{}

    @Test
    public void test(){
        Tester<Throwable> tester = IsClassOfTester.of(A.class);
        assertEquals(false, tester.test(null));
        assertEquals(false, tester.test(new RuntimeException("")));
        assertEquals(true, tester.test(new A()));

        tester = IsNotClassOfTester.of(A.class);
        assertEquals(false, tester.test(null));
        assertEquals(true, tester.test(new RuntimeException("")));
        assertEquals(false, tester.test(new A()));

        tester = SkipTester.of();
        assertEquals(false, tester.test(null));
        assertEquals(false, tester.test(new RuntimeException("")));
        assertEquals(false, tester.test(new A()));

        tester = TakeAnywayTester.of();
        assertEquals(true, tester.test(null));
        assertEquals(true, tester.test(new RuntimeException("")));
        assertEquals(true, tester.test(new A()));

        tester = CustTester.of(i->true);
        assertEquals(true, tester.test(null));
        assertEquals(true, tester.test(new RuntimeException("")));
        assertEquals(true, tester.test(new A()));

        tester = CustTester.of(i->false);
        assertEquals(false, tester.test(null));
        assertEquals(false, tester.test(new RuntimeException("")));
        assertEquals(false, tester.test(new A()));

        tester = CustTester.of(i->i instanceof RuntimeException);
        assertEquals(false, tester.test(null));
        assertEquals(true, tester.test(new RuntimeException("")));
        assertEquals(false, tester.test(new A()));



    }

    public void testTestChain(){
        TesterChain testerChain = TestChainBuilder.skip();
        assertEquals(false, testerChain.test(null));
        assertEquals(false, testerChain.test(new RuntimeException("")));
        assertEquals(false, testerChain.test(new A()));

        testerChain = TestChainBuilder.takeAnyway();
        assertEquals(true, testerChain.test(null));
        assertEquals(true, testerChain.test(new RuntimeException("")));
        assertEquals(true, testerChain.test(new A()));

        testerChain = TestChainBuilder.andIsClassOf(Exception.class);
        assertEquals(false, testerChain.test(null));
        assertEquals(true, testerChain.test(new Exception("")));
        assertEquals(true, testerChain.test(new A()));
        assertEquals(false, testerChain.test(new Throwable()));

        testerChain = TestChainBuilder.andIsClassOf(Exception.class).isClassOf(RuntimeException.class);
        assertEquals(false, testerChain.test(null));
        assertEquals(false, testerChain.test(new Exception("")));
        assertEquals(false, testerChain.test(new A()));
        assertEquals(false, testerChain.test(new Throwable()));
        assertEquals(false, testerChain.test(new RuntimeException()));

        testerChain = TestChainBuilder.andIsNotClassOf(RuntimeException.class);
        assertEquals(false, testerChain.test(null));
        assertEquals(false, testerChain.test(new RuntimeException("")));
        assertEquals(true, testerChain.test(new Exception("")));
        assertEquals(true, testerChain.test(new Throwable("")));
        assertEquals(true, testerChain.test(new A()));

        testerChain = TestChainBuilder.andIsNotClassOf(RuntimeException.class).isNotClassOf(Exception.class);
        assertEquals(false, testerChain.test(null));
        assertEquals(false, testerChain.test(new RuntimeException("")));
        assertEquals(false, testerChain.test(new Exception("")));
        assertEquals(true, testerChain.test(new Throwable("")));
        assertEquals(false, testerChain.test(new A()));

        testerChain = TestChainBuilder.andCustomTester(it->it instanceof RuntimeException);
        assertEquals(false, testerChain.test(null));
        assertEquals(false, testerChain.test(new RuntimeException("")));
        assertEquals(true, testerChain.test(new Exception("")));
        assertEquals(true, testerChain.test(new Throwable("")));
        assertEquals(true, testerChain.test(new A()));

        testerChain = TestChainBuilder.andCustomTester(it->it instanceof RuntimeException).custom(it-> it instanceof Exception);
        assertEquals(false, testerChain.test(null));
        assertEquals(false, testerChain.test(new RuntimeException("")));
        assertEquals(false, testerChain.test(new Exception("")));
        assertEquals(true, testerChain.test(new Throwable("")));
        assertEquals(false, testerChain.test(new A()));

        testerChain = TestChainBuilder.orIsClassOf(RuntimeException.class);
        assertEquals(true, testerChain.test(new RuntimeException()));
        assertEquals(false, testerChain.test(new Throwable()));

        testerChain = TestChainBuilder.orIsClassOf(RuntimeException.class).isClassOf(Exception.class);
        assertEquals(true, testerChain.test(new Exception()));
        assertEquals(false, testerChain.test(new Throwable()));

        testerChain = TestChainBuilder.orIsClassOf(Error.class).isClassOf(Exception.class);
        assertEquals(true, testerChain.test(new Error()));
        assertEquals(false, testerChain.test(new Throwable()));
        assertEquals(true, testerChain.test(new A()));
    }

    @Test
    public void testTestAndHandle(){
        AtomicInteger a = new AtomicInteger(0);
        // TestAndHandlerChain testchain = TestChainBuilder.takeAnyway()
        //         .withHandler(it -> {
        //             a.incrementAndGet();
        //             assertEquals("12345", it.getMessage());
        //         });

        TestAndHandlerChain testChain = TestAndHandlerChain.of(TestChainBuilder.takeAnyway().withHandler(it -> {
            a.incrementAndGet();
            assertEquals("12345", it.getMessage());
        }));
        testChain.testAndHandle(new RuntimeException("12345"));

        assertEquals(1, a.get());

    }
}

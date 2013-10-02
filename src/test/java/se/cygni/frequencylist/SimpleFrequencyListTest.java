package se.cygni.frequencylist;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimpleFrequencyListTest {

    @Test
    public void testIsSane() throws Exception {
        Assert.assertTrue(true);
    }

    @Test
    public void testThreadedAdds() throws Exception {
        SimpleFrequencyList fList = new SimpleFrequencyList();


        ExecutorService tp = Executors.newFixedThreadPool(3);
        Runnable publisher1 = getRunner(fList, "1");
        Runnable publisher11 = getRunner(fList, "1");
        Runnable publisher2 = getRunner(fList, "2");

        tp.execute(publisher1);
        tp.execute(publisher11);
        tp.execute(publisher2);

        tp.shutdown();

        Assert.assertTrue("Shutdown complete", tp.awaitTermination(1200, TimeUnit.MILLISECONDS));
        Assert.assertEquals(100, fList.getFrequency("2"));
        Assert.assertEquals(200, fList.getFrequency("1"));
    }

    private Runnable getRunner(final SimpleFrequencyList fList, final String number) {
        return new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    try {
                        fList.add(number);
                        Thread.sleep(5);
                    } catch (InterruptedException e) { }
                }
            }
        };
    }
}

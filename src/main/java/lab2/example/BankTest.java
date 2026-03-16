package lab2.example;

public class BankTest {
    public void runTest(IBank b) {
        int i;
        Thread[] threads = new Thread[b.size()];
        for (i = 0; i < b.size(); i++){
            BankThread t = new BankThread(b, i,
                    b.getInitialBalance());
            threads[i] = t;
            t.setPriority(Thread.NORM_PRIORITY + i % 2);
            t.start () ;
        }
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {}
        }
        b.test();
    }
}

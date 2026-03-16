package lab2.example;

import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        BankTest tester = new BankTest();
        int accounts = 100;
        int initialBalance = 10000;
        BankReentrant br = new BankReentrant(accounts, initialBalance);
        BankAtomic ba =  new BankAtomic(accounts, initialBalance);
        BankSynchronized bs = new BankSynchronized(accounts, initialBalance);

        Thread brthread = new Thread( () -> tester.runTest(br));
        Thread bathread = new Thread( () -> tester.runTest(ba));
        Thread synthread = new Thread( () -> tester.runTest(bs));
        brthread.start();
        bathread.start();
        synthread.start();
        brthread.join();
        bathread.join();
        synthread.join();
    }
}
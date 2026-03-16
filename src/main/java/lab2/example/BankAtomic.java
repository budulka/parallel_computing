package lab2.example;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class BankAtomic implements IBank {
    public static final int NTEST = 10000;
    private final AtomicInteger[] accounts;
    private final AtomicLong ntransacts = new AtomicLong(0);
    private final int initialBalance;

    public BankAtomic(int n, int initialBalance) {
        accounts = new AtomicInteger[n];
        this.initialBalance = initialBalance;
        for (int i = 0; i < n; i++)
            accounts[i] = new AtomicInteger(initialBalance);
    }

    public void transfer(int from, int to, int amount) {
        accounts[from].addAndGet(-amount);
        accounts[to].addAndGet(amount);
        long n = ntransacts.incrementAndGet();
    }

    public void test() {
        int sum = 0;
        for (AtomicInteger acc : accounts)
            sum += acc.get();
        System.out.println("Transactions:" + ntransacts + " Sum: " + sum);
    }

    public int size() {
        return accounts.length;
    }
    @Override
    public int getInitialBalance() {
        return initialBalance;
    }
}


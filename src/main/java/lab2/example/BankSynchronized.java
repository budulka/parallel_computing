package lab2.example;

import java.util.concurrent.atomic.AtomicLong;


public class BankSynchronized implements IBank {
    public static final int NTEST = 10000;
    private final int[] accounts;
    private final AtomicLong ntransacts = new AtomicLong(0);
    private final int initialBalance;

    public BankSynchronized(int n, int initialBalance) {
        accounts = new int[n];
        this.initialBalance = initialBalance;
        for (int i = 0; i < n; i++)
            accounts[i] = initialBalance;
    }

    public synchronized void transfer(int from, int to, int amount) {
        accounts[from] -= amount;
        accounts[to] += amount;
        long n = ntransacts.incrementAndGet();

    }

    public void test() {
        int sum = 0;
        for (int acc : accounts)
            sum += acc;
        System.out.println("Transactions:" + ntransacts + " Sum: " + sum);

    }

    public int size() {
        return accounts.length;
    }

    public int getInitialBalance() {
        return initialBalance;
    }
}

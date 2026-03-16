package lab2.example;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

public class BankReentrant implements IBank {
    public static final int NTEST = 10000;
    private final int[] accounts;
    private final AtomicLong ntransacts = new AtomicLong(0);
    private final ReentrantLock[] locks;
    private final int initialBalance;

    public BankReentrant(int n, int initialBalance) {
        accounts = new int[n];
        locks = new ReentrantLock[n];
        this.initialBalance = initialBalance;
        for (int i = 0; i < n; i++) {
            accounts[i] = initialBalance;
            locks[i] = new ReentrantLock();
        }
    }

    public void transfer(int from, int to, int amount) {
        int first = Math.min(from, to);
        int last = Math.max(to, from);
        locks[first].lock();
        locks[last].lock();
        try {
            accounts[from] -= amount;
            accounts[to] += amount;
            long n = ntransacts.incrementAndGet();
        }
        finally {
        locks[last].unlock();
        locks[first].unlock();
        }
    }

    public void test() {
            int sum = 0;
            for (int acc : accounts)
                sum += acc;
            System.out.println("Transactions:" + ntransacts + " Sum: " + sum);
    }


    @Override
    public int getInitialBalance() {
        return initialBalance;
    }

    public int size() {
        return accounts.length;
    }
}



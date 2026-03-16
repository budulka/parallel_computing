package lab2.Journal;

import java.util.concurrent.locks.ReentrantLock;

public class Student {
    private int points = 0;
    ReentrantLock lock = new ReentrantLock();

    public void setPoints(int points) {
        if (lock.tryLock()) {
            try {
                if (this.points != 0) return;
                this.points = points;
            } finally {
                lock.unlock();
            }
        }
    }

    public int getPoints() { return points; }
}
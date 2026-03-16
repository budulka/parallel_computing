package lab2.producerconsumer;

import java.util.Random;

public class Producer implements Runnable {
    private Drop drop;
    private int size;

    public Producer(Drop drop, int size) {
        this.drop = drop;
        this.size = size;
    }

    public void run() {
        int[] importantInfo = new int[size];
        for (int i = 0; i < importantInfo.length; i++) {
            importantInfo[i] = new Random().nextInt(1000);
        }


        Random random = new Random();

        for (int i = 0;
             i < importantInfo.length;
             i++) {
            drop.put(importantInfo[i]);
            try {
                Thread.sleep(random.nextInt(50));
            } catch (InterruptedException e) {}
        }
        drop.put(1001);
    }
}

package org.example;

import java.util.Objects;
import java.util.concurrent.*;

public class FoxAlgorithm {
    private int[][] a;
    private int[][] b;
    private int[][] c;

    public FoxAlgorithm(int[][] a, int[][] b) {
        this.a = a;
        this.b = b;
        c = new int[a.length][b[0].length];
    }

    private int getQ(int n) {
        int cores = Runtime.getRuntime().availableProcessors();
        int sqrt = (int) Math.sqrt(cores);
        for (int q = sqrt; q >= 1; q--) {
            if (n % q == 0) return q;
        }
        return 1;
    }

    public int[][] calculate() throws ExecutionException, InterruptedException {
        int q = getQ(a.length);
        int len = a.length / q;
        int[][] result = new int[a.length][b[0].length];
        ExecutorService executor = Executors.newFixedThreadPool(q * q);

        Future<?>[] futures = new Future[q * q];
        int futureIndex = 0;
        for (int i = 0; i < q; i++) {
            for (int j = 0; j < q; j++) {
                final int fi = i;
                final int fj = j;
                futures[futureIndex] = executor.submit(() -> {
                    for (int l = 0; l < q; l++) {
                        int ak = (fi + l) % q;
                        new MultiplyTask(a, b, result, fi, fj, ak, len).run();
                    }
                });
                futureIndex++;
            }
        }

        for (Future f : futures) {
            f.get();
        }
        executor.shutdown();
        return result;
    }

}



class MultiplyTask implements Runnable {
    private int[][] a;
    private int[][] b;
    private int[][] result;
    private final int i;
    private final int j;
    private final int len;
    private final int k;
    public MultiplyTask(int[][] a, int[][] b, int[][] result, int i, int j, int k, int len) {
        this.a = a;
        this.b = b;
        this.result =  result;
        this.i = i;
        this.j = j;
        this.len = len;
        this.k = k;
        System.out.println(len);
    }

    private int[][] getBlock(int[][] matrix, int row, int col) {
        int[][] block = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                block[i][j] = matrix[i + row * len][j + col * len];
            }
        }
        return block;
    }

    public int[][] multiply(int[][] a, int[][] b) {
        int[][] result = new int[a.length][b[0].length];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                for (int k = 0; k < a[0].length; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;
    }

    @Override
    public void run() {
        int[][] blockA = getBlock(a, i, k);
        int[][] blockB = getBlock(b, k, j);
        int[][] c =  multiply(blockA, blockB);
        for (int  i = 0; i < len; i++) {
            for (int  j = 0; j < len; j++) {
                result[i + this.i * len][j + this.j * len] += c[i][j];
            }
        }
    }


}
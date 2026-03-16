package lab3.example;

import org.example.FoxAlgorithm;
import org.example.StripeThread;

import java.util.Random;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int size = 2200;
        int[][] a = new int[size][size];
        int[][] b = new int[size][size];
        Random random = new Random();

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                a[i][j] = random.nextInt(100);
            }
        }
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
                b[i][j] = random.nextInt(100);
            }
        }



        long start = System.nanoTime();
        //int[][] sq = multiply(a, b);
        long end = System.nanoTime();
        System.out.println("Regular result: ");
        System.out.printf("Time (sequential): %.3f ms%n", (end - start) / 1_000_000.0);
        start = System.nanoTime();
        int[][] c = multiplyParallel(a, b);
        end = System.nanoTime();

        System.out.printf("Time (parallel): %.3f ms%n", (end - start) / 1_000_000.0);
        start = System.nanoTime();
        FoxAlgorithm algorithm = new FoxAlgorithm(a, b);
        int[][] cp = algorithm.calculate();
        end = System.nanoTime();
        System.out.printf("Time (Fox): %.3f ms%n", (end - start) / 1_000_000.0);
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c[i].length; j++) {
                if (c[i][j] != cp[i][j]) {
                    System.out.println("Ти даун");
                }
            }
        }



    }

    public static int[][] multiply(int[][] a, int[][] b) {
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

    public static int[][] multiplyParallel(int[][] a, int[][] b) {
        int[][] result = new int[a.length][b[0].length];
        Thread[] threads = new Thread[a.length];
        for (int i = 0; i < a.length; i++) {
            Thread t = new Thread(new StripeThread(i, a[i], b, result));
            threads[i] = t;
            t.start();
        }
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {}
        }
        return result;
    }

    public static void print(int[][] a) {
        for (int[] ints : a) {
            for (int j = 0; j < a[0].length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
    }

//    public static int[][] getBlock(int[][] matrix, int q, int k, int l) {
//        int[][] block = new int[q][q];
//        for (int i = 0; i < q; i++) {
//            for (int j = 0; j < q; j++) {
//                block[i][j] = matrix[i + k * q][j + l * q];
//            }
//        }
//        return block;
//    }
}
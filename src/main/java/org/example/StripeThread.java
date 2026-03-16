package org.example;

public class StripeThread implements Runnable {
    private int idx;
    private int[] a;
    private int[][] b;
    private int[][] res;

    public StripeThread(int idx, int[] a, int[][] b, int[][] res) {
        this.idx = idx;
        this.a = a;
        this.b = b;
        this.res = res;
    }

    @Override
    public void run() {
        int colIdx = idx;
        for (int j = 0; j < b[0].length; j++) { //  j - номер рядка b
            colIdx = (colIdx + 1) % b[0].length;
            for (int l = 0; l < a.length; l++) {
                try {
                    res[idx][colIdx] += a[l] * b[l][colIdx];
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.printf("Tried to add to res[%d][%d] using a[%d] and b[%d][%d] while sizes are: res[%d][%d], a[%d], b[%d][%d]" , idx, j, l, j, l, res.length, res[0].length, a.length, b.length, b[0].length);
                }
            }
        }
    }
}

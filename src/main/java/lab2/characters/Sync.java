package lab2.characters;

public class Sync {
    private int permission;
    private int typed = 0;
    private boolean stopped = false;
    private int charsInLine = 200;

    public synchronized void waitAndChange(int threads, int control, char c) {
        while (permission % threads != control) {
            try {
                wait();
            }  catch (InterruptedException e) {
                System.out.print("Interrupted");
            }
        }
        System.out.print(c);
        permission++;
        typed++;
        notifyAll();
        if (typed % charsInLine == 0) {
            System.out.print("\n");
        }
        if (typed > charsInLine * 99) stopped = true;
    }
    public boolean isStopped() {
        return stopped;
    }
}

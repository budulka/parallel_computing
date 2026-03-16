package lab2.characters;

public class CharacterTyper implements Runnable{
    private char character;
    private int threads;
    private int control;
    private Sync synchronizer;

    public CharacterTyper(char character, int threads, int control, Sync synchronizer) {
        this.character = character;
        this.threads = threads;
        this.control = control;
        this.synchronizer = synchronizer;
    }

    public void run() {
        while (!synchronizer.isStopped()) {
            synchronizer.waitAndChange(threads, control, character);
        }

    }
}

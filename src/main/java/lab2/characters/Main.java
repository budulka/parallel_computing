package lab2.characters;

public class Main {
    public static void main(String[] args) {
        int control = 0;
        int threads = 3;
        Sync sync = new Sync();
        (new Thread(new CharacterTyper('|', threads, control, sync)) ).start();
        (new Thread(new CharacterTyper('\\', threads, ++control, sync)) ).start();
        (new Thread(new CharacterTyper('/', threads, ++control, sync)) ).start();
    }
}

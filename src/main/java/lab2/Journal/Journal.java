package lab2.Journal;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Journal {
    private final ArrayList<Student> groupA = new ArrayList<Student>();
    private final ArrayList<Student> groupB = new ArrayList<Student>() ;
    private final ArrayList<Student> groupC = new ArrayList<Student>() ;


    public Journal(int groupSize) {
        for (int i = 0; i < groupSize; i++){
            groupA.add(new Student());
            groupB.add(new Student());
            groupC.add(new Student());
        }
    }


    public void gradeStudents() throws InterruptedException {
        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            Thread t = new Thread(() -> {
                ThreadLocalRandom random = ThreadLocalRandom.current();
                for (Student student : groupA) {
                    student.setPoints(random.nextInt(60, 100));
                }
                for (Student student : groupB) {
                    student.setPoints(random.nextInt(60, 100));
                }
                for (Student student : groupC) {
                    student.setPoints(random.nextInt(60, 100));
                }
            });

            threads.add(t);
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
    }

    public void printPoints() {
        System.out.println("Group A:");
        for (Student student : groupA) {
            System.out.printf("%d ", student.getPoints());
        }

        System.out.println("\nGroup B:");
        for (Student student : groupB) {
            System.out.printf("%d ", student.getPoints());
        }
        System.out.println("\nGroup C:");
        for (Student student : groupC) {
            System.out.printf("%d ", student.getPoints());
        }
    }
}

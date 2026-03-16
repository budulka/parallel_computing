package lab2.Journal;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Journal journal = new Journal(30);
        journal.gradeStudents();
        journal.printPoints();
    }
}

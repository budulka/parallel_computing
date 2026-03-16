package lab2.example;

public interface IBank {
    int size();
    void transfer(int from, int to, int amount);
    void test();
    int getInitialBalance();
}

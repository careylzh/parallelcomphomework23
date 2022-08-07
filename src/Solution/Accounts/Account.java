package Solution.Accounts;

public class Account {
    private final int id;
    private int balance;

    public Account(int id, int bal) {
        this.id = id;
        this.balance = bal;
    }

    public int getId() {
        return this.id;
    }

    public synchronized int add(int amt) {
        assert (amt >= 0);
        this.balance += amt;
        return this.balance;
    }

    public synchronized int sub(int amt) throws InterruptedException {
        assert (amt >= 0);
        if (this.balance < amt)
            wait();
        this.balance -= amt;
        notifyAll();
        return this.balance;
    }

    public synchronized int get() {
        return this.balance;
    }
}
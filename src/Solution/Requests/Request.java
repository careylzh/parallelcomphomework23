package Solution.Requests;

public interface Request {

    void Process() throws InterruptedException;

    void setTimestamp(int timestamp);
}
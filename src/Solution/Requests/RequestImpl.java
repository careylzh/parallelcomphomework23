package Solution.Requests;

import Solution.Accounts.Account;
import Solution.DigitalLedger;

public abstract class RequestImpl implements Request {
    private DigitalLedger digitalLedger;
    private int timestamp = -1;

    public RequestImpl(DigitalLedger digitalLedger) {
        this.digitalLedger = digitalLedger;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    protected Account getAccount(Integer accountID) {
        Account account = DigitalLedger.getAccounts(accountID);
        return account;
    }

    protected void CheckProgress() {
        while (timestamp != digitalLedger.progress() - 1) ;//This is busy waiting. How to make it more efficient?
    }

    protected void UpdateProgress() {
        digitalLedger.updateProgress();
    }

    public abstract void _Process() throws InterruptedException;

    /**
     * This ensures that all requests are processed following timestamp sequence.
     * However, it falls back to sequential processing. Let's revise this in HW3.
     *
     * @throws InterruptedException
     */
    @Override
    public void Process() throws InterruptedException {
        CheckProgress();
        _Process();
        UpdateProgress();
    }
}
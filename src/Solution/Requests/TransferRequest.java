package Solution.Requests;

import Solution.Accounts.Account;
import Solution.DigitalLedger;

public class TransferRequest extends RequestImpl {

    private int transferAmt = -1;
    private int fromId = -1;
    private int toId = -1;

    public TransferRequest(DigitalLedger digitalLedger, int payload, int fromId, int toId) {
        super(digitalLedger);
        this.fromId = fromId;
        this.toId = toId;
        this.transferAmt = payload;
    }

    /**
     * Do we need synchronized here?
     *
     * @param fromAccount
     * @param toAccount
     */
    private void handleTransfer(Account fromAccount, Account toAccount) throws InterruptedException {
        fromAccount.sub(transferAmt);
        toAccount.add(transferAmt);
    }

    @Override
    public void _Process() throws InterruptedException {
        assert fromId != toId;//otherwise pointless.
        Account fromAccount = getAccount(fromId);
        Account toAccount = getAccount(toId);

        //avoid deadlock.
        if (this.toId < this.fromId) {
            synchronized (toAccount) {
                synchronized (fromAccount) {
                    handleTransfer(fromAccount, toAccount);
                }
            }
        } else if (this.fromId < this.toId) {
            synchronized (fromAccount) {
                synchronized (toAccount) {
                    handleTransfer(fromAccount, toAccount);
                }
            }
        }
    }
}

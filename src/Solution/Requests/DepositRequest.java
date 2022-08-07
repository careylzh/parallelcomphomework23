package Solution.Requests;

import Solution.Accounts.Account;
import Solution.DigitalLedger;

public class DepositRequest extends RequestImpl {
    private int depositId = -1;
    private int depositAmt = -1;
    public DepositRequest(DigitalLedger digitalLedger, int payload, int accountID) {
        super(digitalLedger);
        this.depositId = accountID;
        this.depositAmt = payload;
    }

    @Override
    public void _Process() {
        Account account = getAccount(depositId);
        account.add(depositAmt);
    }
}

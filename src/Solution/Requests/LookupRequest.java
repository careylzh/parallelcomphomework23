package Solution.Requests;

import Solution.Accounts.Account;
import Solution.DigitalLedger;

public class LookupRequest extends RequestImpl {
    int lookupId = -1;
    protected int lookupResults = -1;

    public LookupRequest(DigitalLedger digitalLedger, int lookupId) {
        super(digitalLedger);
        this.lookupId = lookupId;
    }

    @Override
    public void _Process() {
        Account account = getAccount(lookupId);
        lookupResults = account.get();
    }

}

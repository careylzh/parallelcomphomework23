package Solution;

import Solution.Requests.Request;

import static Solution.Config.NUM_REQUESTS;

/**
 * RequestsGenerator continuously generates requests
 * MODIFY ACCORDINGLY
 */
public class RequestsGenerator extends Thread {

    private final DigitalLedger ledger;
    private int rid;
    private final int lastId;

    public RequestsGenerator(DigitalLedger ledger, int clientId, int PThreads) {

        this.ledger = ledger;
        int numTasks = NUM_REQUESTS / PThreads;
        this.rid = clientId * numTasks;//starting point
        if (clientId != PThreads - 1)//! last thread, ClientId=0, 1
            lastId = (clientId + 1) * numTasks;//this is the last request that client thread needs to send
        else
            lastId = NUM_REQUESTS;//this is the last request that the last client thread needs to send
    }

    private Request nextRequest() {
        return ledger.getRequests(rid++);
    }

    @Override
    public void run() {
        while (rid != lastId) {//make sure all requests are sending out.
            ledger.sendRequest(nextRequest());
        }
    }
}
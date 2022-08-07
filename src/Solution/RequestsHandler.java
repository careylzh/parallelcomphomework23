package Solution;


import Solution.Requests.Request;

import static Solution.Config.NUM_REQUESTS;

/**
 * RequestsHandler continuously serves requests
 * MODIFY ACCORDINGLY
 */
public class RequestsHandler extends Thread {

    private DigitalLedger ledger;
    private int task = 0;
    private final int numTasks;

    public RequestsHandler(DigitalLedger ledger, int serverId, int CThreads) {
        this.ledger = ledger;

        if (serverId != CThreads - 1)
            this.numTasks = NUM_REQUESTS / CThreads;
        else
            this.numTasks = NUM_REQUESTS % CThreads;
    }

    private Request nextRequest() {
        return ledger.receiveRequest();
    }

    @Override
    public void run() {
        while (task != numTasks) {//resulting in each server thread processes equal amount of requests.
            // Is this the most efficient way? Revise it in HW3.
            Request request = nextRequest();
            request.setTimestamp(ledger.tick()); //ledger null????
            try {
                request.Process();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            task++;
        }
    }
}
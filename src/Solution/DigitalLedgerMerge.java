package Solution;

import java.util.concurrent.BrokenBarrierException;

public class DigitalLedgerMerge {
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {


        int PThreads = 100;
        int CThreads = 100;
        RequestsGenerator[] generators = new RequestsGenerator[PThreads];
        RequestsHandler[] handlers = new RequestsHandler[CThreads];

        DigitalLedger ledger = DigitalLedger.getInstance();

        int clientId = 0;
        for (int i=0; i<PThreads; i++) {
            generators[i] = new RequestsGenerator(ledger, clientId++, PThreads);
            generators[i].start();
        }

        int serverId = 0;
        for (int i=0; i<CThreads; i++) {
            handlers[i] = new RequestsHandler(ledger, serverId++, CThreads);
            handlers[i].start();
        }
    }
}
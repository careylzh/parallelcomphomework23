# parallelcomphomework23

### Goal: build a digital ledger system with **streaming** data
- basically a bank balance logbook that continuously accepts input statements from the user
- code template: given 6 classes
  - `Account`: should contain variables(eg. balanceAmt, possibly credit or debit values) which stores the values for each accountId
  - `DepositRequest`: **--TO BE DEFINED--**
  - `DigitalLedger`: processes up to 1000 requests(aka 'banks transactions' and stores last 100 requests in a `LinkedBlockingQueue` java data structure (derived from code template)
  - `LookupRequest`: read request
  - `Request`: **--TO BE DEFINED--**
  - `TransferRequest` <br/>

Qn 1: **global** data structure <br/>
Qn 2: It seems that there are only 3 types of requests <br/>
Qn 3: There is a TODO for `RequestGenerator` class - so are requests randomly simulated? Probably. can use mod 3 + java's rand

Given assumptions:
- `Account` class only has a mapping(given word: 'schema') of ID --> Amount. Can use hashmap to map id to amt
- Each request can touch only one or two accounts.
- Requests always arrive at the server side in the strict
order of their timestamp (aka 'later requests stored later')
# Infiniti Space Bank Virtual Wallet Library

Infiniti Space Bank is launching a brand new online banking platform and they want you to build a Java library to build the
virtual wallet to track users transaction account. https://en.wikipedia.org/wiki/Transaction_account .

## At a high level the library needs to solve the following business needs

* Track a user’s account balance
* Manage account transactions in the form of debits (withdrawals) and credits (deposits).
* Allow a user to transfer money to another user/account.
* Keep a history of last N transactions.

### Understand the classes

* Each user has one wallet. 
* Each wallet has one or multiple accounts.
* Each account handles its balance and makes transaction.
* An transaction contains information about the detail of the transaction.

### Assumptions

* Maximum number of users is Integer.MAX_VALUE, 2^31 -1
* Maximum number of transactions is Integer.MAX_VALUE, 2^31 -1
* Maximum account balance is Double.MAX_VALUE;

### The tests

The tests are created with Junit Testing in test file. Each test class focuses on testing one class in src file.

### Author
* **Ken Chiu**
### Acknowledgments
* This project was assigned by ** Upgrade **

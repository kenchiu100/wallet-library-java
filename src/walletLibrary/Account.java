package walletLibrary;
import java.util.ArrayList;

/**
 * This class allows create to account object. An account can manage account balance, deposit, withdrawal, 
 * transferring money to another account, and reading Transaction history.
 * @author Ken Chiu
 */

public class Account {
	//Assume maximum balance is Double.MAX_VALUE;
	private double balance;
	private ArrayList<Transaction> transactions;

	/** Create an account without an initial balance.
	 */
	public Account() {
		this.balance = 0.0;
		this.transactions = new ArrayList<>();
	}
	
	/** Create an account with a specified amount of initial balance.
	 * @param amount A specified amount of initial balance.
	 */
	public Account(double amount){
		this.balance = amount;
		this.transactions = new ArrayList<>();
	}
	
	/** Get the account's balance.
	 * @return A double representing the account's balance.
	 */
	public double getBalance(){
		return this.balance;
	}
	
	/** Make a deposit to the account with a specified amount.
	 * @param amount A specified amount to be deposit.
	 */
	public synchronized void deposit(double amount){

		adjBalance(true, amount);

		Transaction t = new Transaction(true, amount, this);

		this.transactions.add(t);

	}

	/** Make a withdraw from the account with a specified amount.
	 * @param amount A specified amount to be withdrawal.
	 * @throws NotEnoughBalanceException An exception to be thrown when there is not enough balance to be withdrawal.
	 */
	public synchronized void withdrawal(double amount) throws NotEnoughBalanceException{
			if(amount < this.balance){
				adjBalance(false, amount);
				Transaction t = new Transaction(false, amount, this);
				transactions.add(t);
			}else{
				throw new NotEnoughBalanceException("Not enough balance to withdrawal");
			}
	}
	
	/** Increase or decrease the account's balance.
	 * @param isIncrease A boolean that is true if it is going to increase the account's balance.
	 * @param amount A specified amount to be increased or decreased at the account's balance.
	 */
	private synchronized void adjBalance(boolean isIncrease, double amount){
		if(isIncrease){
			this.balance += amount;
		}else{
			this.balance -=amount;
		}
	}
	
	/** Transfer money from this account to the another account.
	 * @param amount A specified amount to be transferred.
	 * @param account2 A specified account to receive the transfer.
	 * @throws NotEnoughBalanceException An exception to be thrown when there is not enough balance make a transfer.
	 */
	public void transfer(double amount, Account account2) throws NotEnoughBalanceException{

			if(amount < this.balance){
				this.withdrawal(amount);
				account2.deposit(amount);
			}else
				throw new NotEnoughBalanceException("Not enough balance to withdrawal");
	}
	
	/** Read the last N transactions.
	 * @param n A specified integer representing the number of last transactions that needs to be returned.
	 * @return An ArrayList of last N Transaction.
	 */
	public ArrayList<Transaction> readTransactions(int n){// no history
				ArrayList<Transaction> transactionList = new ArrayList<>();
				for(int i = Math.max(0, transactions.size()-n); i< transactions.size(); i++){
					transactionList.add(transactions.get(i));
				}
				return transactionList;
	}
	
	/** Get all of the past transactions of this account.
	 * @return An ArrayList of Transaction.
	 */
	public ArrayList<Transaction> getTransactions(){
		return this.transactions;
	}
	

}

package walletLibrary;
import java.sql.Timestamp;

/**
 * This class allows create to transaction object. Each transaction provide information of the transaction. It provide the 
 * amount of debit, credit, end of balance, timestamp, and a unique ID
 * @author Ken Chiu
 */

public class Transaction {
	//assume the maximum number transaction is 2^31 -1;
	private static int numOfTransactions;
	private double debit;
	private double credit;
	private double endOfBalance;
	private Timestamp timeStamp;
	private int transactionID = 0;

	/** Create a transaction with the specified transaction information.
	 * @param isDebit A boolean that is true if it is a debit transaction.
	 * @param amount A specified amount of the transaction.
	 * @param account A specified account of the transaction.
	 */
	public Transaction(boolean isDebit, double amount, Account account) {
		if(isDebit){
			this.debit = amount;
			this.credit = 0.0;
			this.endOfBalance = account.getBalance();
		}else{
			this.credit = amount;
			this.debit = 0.0;
			this.endOfBalance = account.getBalance();
		}
		incrementNum();
		this.transactionID = numOfTransactions;
		this.timeStamp = new Timestamp(System.currentTimeMillis());
	}
	
	/** Increase the number of transactions when a new transaction created.
	 */
	private synchronized static void incrementNum(){
		numOfTransactions++;
	}
	
	/** Get the number of transactions.
	 * @return An integer that represents the number of transactions.
	 */
	public static int getNumOfTransactions(){
		return Transaction.numOfTransactions;
	}
	
	/** Get the amount of debit in this transaction.
	 * @return A double representing the amount of debit in this transaction.
	 */
	public double getDebit(){
		return this.debit;
	}
	
	/** Get the amount of credit in this transaction.
	 * @return A double representing the amount of credit in this transaction.
	 */
	public double getCredit(){
		return this.credit;
	}
	
	/** Get the amount of end of balance in this transaction.
	 * @return A double representing the amount of end of balance in this transaction.
	 */
	public double getEndOfBalance(){
		return this.endOfBalance;
	}
	
	/** Get the timestamp of the transaction.
	 * @return A Timestamp representing when the transaction occurs.
	 */	
	public Timestamp getTimeStamp(){
		return this.timeStamp;
	}
	
	/** Get the unique ID of the transaction.
	 * @return A integer representing the unique ID of the transaction.
	 */	
	public int getTransactionID(){
		return this.transactionID;
	}
	
}

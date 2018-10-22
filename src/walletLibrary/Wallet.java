package walletLibrary;
import java.util.ArrayList;

/**
 * This class allows create to wallet object. Each wallet is created by a user. 
 * A user will have a single wallet. A wallet provide all access to the transaction account(s).
 * @author Ken Chiu
 */

public class Wallet {
	private ArrayList<Account> myAccounts;

	/** Create a wallet with an ArrayList which keeps track of account(s).
	 */
	public Wallet() {
		myAccounts = new ArrayList<>();
	}
	
	/** Create an account and add it to myAccounts.
	 */
	public void createAccount(){
		Account acc = new Account();
		myAccounts.add(acc);
	}
	
	/** Create an account with a specified initial amount of balance and add it to myAccounts.
	 * @param amount The initial amount of balance when create an account.
	 */
	public void createAccount(double amount){
		Account acc = new Account(amount);
		myAccounts.add(acc);
	}
	
	/** Get the wallet's account(s).
	 * @return An ArrayList of Account.
	 */
	public ArrayList<Account> getAccounts(){
		return this.myAccounts;
	}
}

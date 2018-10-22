package walletLibrary;

/**
 * This class allows to create user object. Each user has one wallet.
 * @author Ken Chiu
 */
public class User {
	//Assume maximum numOfUsers is 2^31 -1;
	private static int numOfUsers = 0;
	private int userId;
	private Wallet myWallet;
	private String userName;
	
	/** Create an user with the specified name.
	 * @param name The user's name.
	 */
	public User(String name){
		this.userName = name;
		this.userId = numOfUsers;
		incrementNumOfUsers();
		myWallet = new Wallet();
	}
	
	/** Increase the number of users.
	 */
	private synchronized static void incrementNumOfUsers(){
		numOfUsers++;
	}
	
	/** Gets the user's name.
	 * @return A string representing the user's name.
	 */
	public String getName(){
		return this.userName;
	}
	
	/** Gets the number of users.
	 * @return An integer representing the number of total users.
	 */
	public static int getNumOfUsers(){
		return numOfUsers;
	}
	
	/** Gets the user's wallet.
	 * @return A wallet of the user.
	 */
	public Wallet getWallet(){
		return this.myWallet;
	}
	
	
}

package walletLibrary;

/**
 * This exception will be thrown when there is a withdrawal transaction that makes an account balance goes to $0.
 * @author Ken Chiu
 */
public class NotEnoughBalanceException extends Exception{

	/** Create a exception when there is a withdrawal transaction that makes an account balance goes to $0.
	 * @param s A String of the exception's message.
	 */
	public NotEnoughBalanceException(String s) {
		super(s);
	}

}

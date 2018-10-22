package walletLibrary;

import static org.junit.Assert.*;

import org.junit.Test;

public class WalletTest {

	@Test
	//Create a new wallet for a user
	public void testCreateWalle() {
		User ben = new User("Ben");
		Wallet w = ben.getWallet();
		assertNotNull(w);
		
	}
	
	@Test
	public void testCreateAccount() {
		User ben = new User("Ben");
		Wallet w = ben.getWallet();
		w.createAccount();
		assertEquals(1, w.getAccounts().size());
		w.createAccount(100.00);
		assertEquals(2, w.getAccounts().size());

		User tom = new User("Tom");
		Wallet tomW = tom.getWallet();
		tomW.createAccount();
		assertEquals(1, tomW.getAccounts().size());
	}
	

}

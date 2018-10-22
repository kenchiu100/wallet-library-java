package walletLibrary;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {

	@Test
	public void testUserCreation() {
		User ken = new User("Ken");
		assertEquals("Ken", ken.getName());
	}

	@Test
	public void testIncrementID() {
			User ken = new User("Ken");
			assertEquals(1, ken.getNumOfUsers());
			User ben = new User("Ben");
			assertEquals(2, ben.getNumOfUsers());
	}
	

}

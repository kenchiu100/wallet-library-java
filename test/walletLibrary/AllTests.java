package walletLibrary;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AccountTest.class, TransactionTest.class, UserTest.class, WalletTest.class })
public class AllTests {

}

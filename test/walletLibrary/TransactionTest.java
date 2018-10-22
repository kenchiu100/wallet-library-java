package walletLibrary;

import static org.junit.Assert.*;

import java.util.concurrent.CountDownLatch;

import org.junit.Test;

public class TransactionTest {
	

	@Test
	public void testTransactionDebitCreation() {
		Account acc = new Account();
		Transaction t = new Transaction(true, 10, acc);
		assertEquals(10, t.getDebit(),0);
	}
	
	@Test
	public void testTransactionCreditCreation() {
		Account acc = new Account(20);
		Transaction t = new Transaction(false, 10, acc);
		assertEquals(10, t.getCredit(),0);	

		}
	
	@Test
	public void testConcurrentTransaction() throws InterruptedException{
		int numOfTransactionsBefore = Transaction.getNumOfTransactions();

		final CountDownLatch latch = new CountDownLatch(1);
		final CountDownLatch doneSignal = new CountDownLatch(10);
		Account acc = new Account();
		Runnable runner = new Runnable(){
		      public void run() {
		        try {
		          latch.await();
		          Transaction t = new Transaction(true, 10, acc);
		        } catch (InterruptedException ie) { }
		          doneSignal.countDown();
		      }
		    };
		    
		   for (int i=0; i<10; i++) {	    
		    new Thread(runner, "TestThread"+i).start();
		  }
		  // all threads are waiting on the latch.
		  latch.countDown(); // release the latch
		  doneSignal.await(); //
		  // all threads are now running concurrently.
		  assertEquals(numOfTransactionsBefore +10, Transaction.getNumOfTransactions(),0);
		  
	}
}

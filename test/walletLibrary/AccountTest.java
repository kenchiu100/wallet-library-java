package walletLibrary;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import org.junit.Test;
//import org.junit.*;



public class AccountTest {

	@Test
	public void testAccountCreation() {
		Account acc = new Account();
		assertNotNull(acc);
		assertNotNull(acc.getTransactions());
		assertEquals(0, acc.getBalance(),0);
		
		Account acc2 = new Account(10);
		assertNotNull(acc2);
		assertNotNull(acc2.getTransactions());
		assertEquals(10, acc2.getBalance(),0);
	}
	
	@Test
	public void testDeposit(){
		Account acc = new Account();
		acc.deposit(100);
		assertEquals(100,acc.getBalance(),0);
	}
	
	@Test
	public void testWithdrawal() throws NotEnoughBalanceException{
		Account acc = new Account(100);
		acc.withdrawal(20);
		assertEquals(80,acc.getBalance(),0);
	}
	
	@Test (expected = NotEnoughBalanceException.class)
	public void testWithdrawalException() throws NotEnoughBalanceException{
			Account acc2 = new Account(100);
			acc2.withdrawal(101);
	}
	
	@Test
	public void testTransfer() throws NotEnoughBalanceException{
		Account acc1 = new Account(100);
		Account acc2 = new Account(0);
		acc1.transfer(40, acc2);
		assertEquals(60,acc1.getBalance(),0);
		assertEquals(40,acc2.getBalance(),0);

	}
	
	@Test
	public void testReadTransactions() throws NotEnoughBalanceException{
		Account acc1 = new Account(100);
		acc1.deposit(10);
		acc1.deposit(5);
		acc1.deposit(20);
		acc1.withdrawal(10);
		acc1.withdrawal(50);
		acc1.withdrawal(30);
		ArrayList<Transaction> transactionList = acc1.readTransactions(4);
		assertEquals(4, transactionList.size());
		
		assertEquals(20,transactionList.get(0).getDebit(),0);
		assertEquals(10,transactionList.get(1).getCredit(),0);
		assertEquals(50,transactionList.get(2).getCredit(),0);
		assertEquals(30,transactionList.get(3).getCredit(),0);
	}
	
	@Test
	public void testConcurrentDeposit() throws InterruptedException{
		Account acc3 = new Account();
		final CountDownLatch latch = new CountDownLatch(1);
		final CountDownLatch doneSignal = new CountDownLatch(50);
		Runnable runner = new Runnable(){
		      public void run() {
		        try {
		          latch.await();
		          acc3.deposit(1);
		        } catch (InterruptedException ie) { }
		          doneSignal.countDown();
		      }
		    };
		    
		   for (int i=0; i<50; i++) {	    
		    new Thread(runner, "TestThread"+i).start();
		  }
		   latch.countDown();
		   doneSignal.await();
		   assertEquals(50, acc3.getBalance(),0);
	}
	
	@Test
	public void testConcurrentWithdrawal() throws InterruptedException{
		Account acc4 = new Account(100);
		final CountDownLatch latch = new CountDownLatch(1);
		final CountDownLatch doneSignal = new CountDownLatch(50);
		Runnable runner = new Runnable(){
		      public void run() {
		        try {
		          latch.await();
		          acc4.withdrawal(1);
		        } catch (InterruptedException | NotEnoughBalanceException ie) { }
		          doneSignal.countDown();
		      }
		    };
		    
		   for (int i=0; i<50; i++) {	    
		    new Thread(runner, "TestThread"+i).start();
		  }
		   latch.countDown();
		   doneSignal.await();
		   assertEquals(50, acc4.getBalance(),0);
	}
	
	@Test
	public void testConcurrentTransfer() throws InterruptedException{
		Account acc1 = new Account(100);
		Account acc2 = new Account(100);		
		final CountDownLatch latch = new CountDownLatch(1);
		final CountDownLatch doneSignal = new CountDownLatch(20);
		//acc1 transfer to acc2
		Runnable runner1 = new Runnable(){
		      public void run() {
		        try {
		          latch.await();
		          acc1.transfer(1, acc2);
		          doneSignal.countDown();
		        } catch (InterruptedException | NotEnoughBalanceException ie) { 
		        	System.out.println(ie.getMessage());}
		      }
		    };
		 //acc2 transfer to acc1
		 Runnable runner2 = new Runnable(){
			      public void run() {
			        try {
			          latch.await();
			          acc2.transfer(1,acc1);
			          doneSignal.countDown();
			        } catch (InterruptedException | NotEnoughBalanceException ie) { 
			        	System.out.println(ie.getMessage());
			        	}
			      }
			    };
			    
			 for (int i=0; i<10; i++) {	    
				 new Thread(runner1, "TestThread"+i).start();
			}
				  
			 for (int i=0; i<10; i++) {	 
			    new Thread(runner2, "TestThread"+i).start();
			  }
		   
		   latch.countDown();
		   doneSignal.await();
		   assertEquals(100, acc1.getBalance(),0);
		   assertEquals(100, acc2.getBalance(),0);
	}
	
	
	
	

}

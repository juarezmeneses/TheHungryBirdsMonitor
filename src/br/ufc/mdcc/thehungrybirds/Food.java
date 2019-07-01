package br.ufc.mdcc.thehungrybirds;

import java.util.concurrent.locks.*; 


public class Food {
	
	private int portions = 0;
	private final int maxPortions = 100;
	
	private Lock aLock = new ReentrantLock(); 	
	private Condition full = aLock.newCondition(); 
	private Condition empty = aLock.newCondition(); 
	
	public int getNumPortions(){
		return portions;
		
	}
	
	public boolean eat(int id) {
		aLock.lock();
		
		while(portions==0) {
			try {
				//aLock.lock();
				System.out.println("Pássaro "+id+" Acordou o Pássaro Mãe, portions: "+portions);
				
				empty.signal();
						
				Thread.sleep(200);
				full.await();
			} catch (InterruptedException e) {				
				e.printStackTrace();
			} finally {
				//aLock.unlock();	
			}
		}
		
		if(portions > 0) {
			//aLock.lock();
			portions=getNumPortions() - 1;
			System.out.println("Pássaro "+id+" Comeu 1 Porção, portions: "+portions);
		
			full.signal(); 
			
			//aLock.unlock();	
		}
		aLock.unlock();
		return true;
	}

	public synchronized boolean refills() {	
		aLock.lock();
		try {
			empty.await();
			portions=maxPortions; 
			System.out.println("Pássaro Mãe Trouxe +100 Porções, portions: "+portions);
			full.signalAll(); 
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			aLock.unlock();	
		}
				
		return true;
	}

	public static void main(String[] args) {
		int numBirds = 5;
		
		Food food = new Food();		
		ParentBird p = new ParentBird(food, 200);
		
		/*// Setup for 5 Birds
		Bird c1 = new Bird(1, food, 200, semaphore, semaphore2);
		Bird c2 = new Bird(2, food, 200, semaphore, semaphore2);
		Bird c3 = new Bird(3, food, 200, semaphore, semaphore2);
		Bird c4 = new Bird(4, food, 200, semaphore, semaphore2);
		Bird c5 = new Bird(5, food, 200, semaphore, semaphore2);

		//Start Birds and Bird Parent
		//...
		p.start();
		c1.start();
		c2.start();
		c3.start();
		c4.start();
		c5.start();*/
		
		// Start Bird Parent
		p.start();
		
		// Setup for N Birds
		Bird[] birds = new Bird[numBirds+1];
	    for (int i = 1; i <= numBirds; i++) {
	        birds[i] = new Bird(i, food, 200);
	        birds[i].start();
	    }
				
	    
	    
		System.out.println("Execucao da main da classe Food terminada");
		}

}

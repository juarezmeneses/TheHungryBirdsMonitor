package br.ufc.mdcc.thehungrybirds;

public class ParentBird extends Thread {
	
	private Food food;
	private int sleep;
		
	public ParentBird (Food food, int sleep) {
		super();
		this.food = food;
		this.sleep = sleep;		
	}
	
	@Override
	public void run() {		
		while(true) {			
			
			this.food.refills();
			
			try {
				Thread.sleep(this.sleep);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}		
		//System.out.println("Pássaro Mãe finalizou. Agora temos: " + food.getNumPortions());		
	}
}

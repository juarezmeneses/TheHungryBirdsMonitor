package br.ufc.mdcc.thehungrybirds;

public class Bird extends Thread {
		
	private int id;
	private Food food;
	private int sleep;		
	
	public Bird (int id, Food food, int sleep) {
		super();
		this.id=id;
		this.food = food;
		this.sleep = sleep;
		
	}
			
	
	@Override
	public void run() {		
		while(true) {
			
			this.food.eat(id);		
			
			try {
				Thread.sleep(this.sleep);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}	
	//System.out.println("O Passaro "+id+" finalizou. Ainda resta: " + food.getNumPortions());		
	}
	

}

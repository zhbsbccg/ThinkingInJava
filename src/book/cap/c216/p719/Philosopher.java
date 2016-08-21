package book.cap.c216.p719;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Philosopher implements Runnable {
	private Chopstick left;
	private Chopstick right;
	private final int id;
	private int ponderFactor;
	private Random rand = new Random(47);
	private void pause() throws InterruptedException {
		if(ponderFactor == 0) return;
		TimeUnit.MILLISECONDS.sleep(rand.nextInt(ponderFactor * 250));
		
	}
	
	public Philosopher(Chopstick left, Chopstick right, int id, int ponderFactor) {
		this.left = left;
		this.right = right;
		this.id = id;
		this.ponderFactor = ponderFactor;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(!Thread.interrupted()) {
				System.out.println(this + " " + "thinking");
				pause();
				//Philosopher becomes hungry
				right.take();
				System.out.println(this + " " + "grabbing right");
				left.take();
				System.out.println(this + " " + "grabbing left");
				System.out.println(this + " " + "eating");
				pause();
				right.drop();
				left.drop();
				
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println(this + " " + "exiting via interrupt");
		}
		
		
	}
	@Override
	public String toString() { return "Philosopher " + id; }
}

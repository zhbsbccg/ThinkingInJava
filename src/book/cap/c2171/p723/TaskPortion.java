package book.cap.c2171.p723;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class TaskPortion implements Runnable {
	private static int counter = 0;
	private final int id = counter++;
	private static Random rand = new Random(47);
	private final CountDownLatch latch;
	public TaskPortion(CountDownLatch latch) {
		this.latch = latch;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	public void doWork() throws InterruptedException {
		TimeUnit.MILLISECONDS.sleep(rand.nextInt(2000));
		System.out.println(this + "completed");
	}
	
	public String toString() {
		return String.format("%1$-3d", id);
	}

}

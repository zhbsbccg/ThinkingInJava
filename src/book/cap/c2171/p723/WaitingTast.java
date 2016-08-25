package book.cap.c2171.p723;

import java.util.concurrent.CountDownLatch;

public class WaitingTast implements Runnable {
	private static int counter = 0;
	private final int id = counter++;
	private final CountDownLatch latch;
	WaitingTast(CountDownLatch latch) {
		this.latch = latch;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			latch.await();
			System.out.println("Latch brrier passed for " + this);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println(this + " interrupted");
		}
	}
	public String toString() {
		return String.format("WaitingTask %1$-3d", id);
	}

}

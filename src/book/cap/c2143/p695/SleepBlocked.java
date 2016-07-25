package book.cap.c2143.p695;

import java.util.concurrent.TimeUnit;

public class SleepBlocked implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			TimeUnit.SECONDS.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("catch InterruptedException");
		}
		System.out.println("ÍË³öSleepBlocked");
	}

}

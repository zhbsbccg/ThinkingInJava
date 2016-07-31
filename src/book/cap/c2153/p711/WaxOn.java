package book.cap.c2153.p711;

import java.util.concurrent.TimeUnit;

public class WaxOn implements Runnable {
	private Car car;
	public WaxOn(Car car){
		this.car=car;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(!Thread.interrupted()){
				System.out.println("Wax on!");
				TimeUnit.MILLISECONDS.sleep(200);
				car.waxed();
				car.waitForBuffing();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Exiting via interrupt");
		}
		System.out.println("Ending Wax On task");
		
	}

}

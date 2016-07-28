package book.cap.c2151.p704;

import java.util.concurrent.TimeUnit;

public class WaxOff implements Runnable {
	private Car car;
	public WaxOff(Car car){
		this.car=car;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(!Thread.interrupted()){
				car.waitForWaxing();
				System.out.println("Wax off!");
				TimeUnit.MILLISECONDS.sleep(200);
				car.buffed();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Exiting via interrupt");
		}
		System.out.println("Ending Wax Off task");
		
	}

}

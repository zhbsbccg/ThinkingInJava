package book.cap.c2151.p704;

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
				TimeUnit.SECONDS.sleep(200);
				
				
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

package book.cap.c2154.p714;

import java.util.concurrent.BlockingQueue;

public class LiftOffRunner implements Runnable {
	private BlockingQueue<LiftOff> rockets;
	public LiftOffRunner(BlockingQueue<LiftOff> queue){
		this.rockets=queue;
	}
	public void add(LiftOff liftOff){
		try {
			rockets.put(liftOff);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//可以被打断，奇怪
			System.out.println("Interrupted during put()");
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(!Thread.interrupted()){
				LiftOff rocket = rockets.take();
				rocket.run();//Use this thread
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Waking from take()");
		}
		System.out.println("Exiting LiftOffRunner");
	}

}

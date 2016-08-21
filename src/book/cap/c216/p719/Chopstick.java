package book.cap.c216.p719;

public class Chopstick {
	private boolean taken = false;
	
	public synchronized void take() throws InterruptedException {
		while(taken) {
			wait();
		}
		taken = true; //chopstick is be userd
	}
	
	public synchronized void drop() {
		taken = false;
		notifyAll();
	}
	
}

package book.cap.c2151.p704;

public class Car {
	private boolean waxOn=false;//on «waxed,false buffed
	public synchronized void waxed(){
		waxOn=true;
		notifyAll();
	}
	public synchronized void buffed(){
		waxOn=false;
		notifyAll();
	}
	public synchronized void waitForWaxing() throws InterruptedException{
		while(!waxOn){
			wait();
		}
	}
	public synchronized void waitForBuffing() throws InterruptedException{
		while(waxOn){
			wait();
		}
	}
}

package book.cap.c2151.p704;

public class Car {
	private boolean waxOn=false;//on «waxed,false buffed
	public synchronized void waxed(){
		waxOn=true;
		notify();
	}
	public synchronized void buffed(){
		waxOn=false;
		notify();
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

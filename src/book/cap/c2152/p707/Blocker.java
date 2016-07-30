package book.cap.c2152.p707;

public class Blocker {
	synchronized void waitingCall(){
		try {
			while(!Thread.interrupted()){
				wait();
				System.out.println(Thread.currentThread() + " ");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
	}
	synchronized void prod(){
		notify();
	}
	synchronized void prodAll(){
		notifyAll();
	}
}

package book.cap.c2153.p711;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Car {
	private Lock lock=new ReentrantLock();
	private Condition condition=lock.newCondition();
	private boolean waxOn=false;//on «waxed,false buffed
	public void waxed(){
		lock.lock();
		try {
			waxOn=true;
			//notify();
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}
	public void buffed(){
		lock.lock();
		try {
			waxOn=false;
			//notify();
			condition.signalAll();
		} finally {
			// TODO Auto-generated catch block
			lock.unlock();
		}
	}
	public void waitForWaxing() throws InterruptedException{
		lock.lock();
		try {
			while(!waxOn){
				//wait();
				condition.await();
			}
		} finally {
			// TODO Auto-generated catch block
			lock.unlock();
		}
	}
	public void waitForBuffing() throws InterruptedException{
		lock.lock();
		try {
			while(waxOn){
				//wait();
				condition.await();
			}
		} finally {
			// TODO Auto-generated catch block
			lock.unlock();
		}
	}
}

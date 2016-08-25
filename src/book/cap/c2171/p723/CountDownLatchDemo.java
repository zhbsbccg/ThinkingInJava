package book.cap.c2171.p723;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo {
	static final int SIZE = 100;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService exec = Executors.newCachedThreadPool();
		CountDownLatch latch = new CountDownLatch(SIZE);
		for(int i = 0; i < 10; i++) {
			exec.execute(new WaitingTast(latch));
		}
		for(int i = 0; i < SIZE; i++) {
			exec.execute(new TaskPortion(latch));
		}
		System.out.println("Launched all tasks");
		exec.shutdown();
	}

}

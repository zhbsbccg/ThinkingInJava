package book.cap.c2154.p714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class TestBlockingQueues {
	static void getkey(){
		try {
			new BufferedReader(new InputStreamReader(System.in)).readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static void getkey(String msg){
		System.out.println(msg);
		getkey();
	}
	static void test(String msg, BlockingQueue<LiftOff> queue){
		System.out.println(msg);
		LiftOffRunner runner=new LiftOffRunner(queue);
		Thread t = new Thread(runner);
		t.start();
		for(int i = 0; i < 5; i++){
			runner.add(new LiftOff(5));
		}
		getkey("Press 'Enter' (" + msg + ")");
		t.interrupt();
		System.out.println("Finished " + msg + " test");
	}
	public static void main(String[] args){
		//test("LinkedBlockingQueue" , new LinkedBlockingQueue<LiftOff>());
		test("ArrayBlockingQueue" , new ArrayBlockingQueue<LiftOff>(3));
		//test("SynchronousQueue" , new SynchronousQueue<LiftOff>());
		
	}
}

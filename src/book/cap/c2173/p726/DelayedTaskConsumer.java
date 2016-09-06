package book.cap.c2173.p726;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DelayedTaskConsumer implements Runnable {
	private DelayQueue<DelayedTask> q;
	public DelayedTaskConsumer(DelayQueue<DelayedTask> q) {
		// TODO Auto-generated constructor stub
		this.q = q;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(!Thread.interrupted()) {
				q.take().run();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		System.out.println("Finished DelayedTaskConsumer");
	}
	
	public static class DelayQueueDemo{
		public static void main(String[] args) {
			Random rand = new Random(47);
			ExecutorService exec = Executors.newCachedThreadPool();
			DelayQueue<DelayedTask> q = new DelayQueue<DelayedTask>();
			for (int i = 0; i < 20; i++) {
				q.put(new DelayedTask(rand.nextInt(5000)));
			}
			q.add(new DelayedTask.EndSentinel(5000, exec));
			exec.execute(new DelayedTaskConsumer(q));
		}
		
	}

}

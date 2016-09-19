package book.cap.c2174.p728;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class PrioritizedTask implements Runnable, Comparable<PrioritizedTask> {
	private Random rand = new Random(47);
	private static int counter = 0;
	private final int id = counter++;
	private final int priority;
	protected static List<PrioritizedTask> sequeuece = new ArrayList<PrioritizedTask>();
	public PrioritizedTask(int priority) {
		this.priority = priority;
		sequeuece.add(this);
	}
	@Override
	public int compareTo(PrioritizedTask o) {
		// TODO Auto-generated method stub
		
		return priority < o.priority ? 1 : (priority > o.priority ? -1 : 0);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			TimeUnit.MILLISECONDS.sleep(rand.nextInt(250));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		System.out.println(this);
	}
	public String toString(){
		return String.format("[%1$-3d]", priority) + " Task " + id;
	}
	
	public String summary() {
		return "(" + id +":" + priority + ")";
	}
	public static class EndSentinel extends PrioritizedTask {
		private ExecutorService exec;
		public EndSentinel(ExecutorService e) {
			// TODO Auto-generated constructor stub
			super(-1);//永远排在最后，用作结束
			exec = e;
		}
		
		public void run() {
			int count = 0;
			for(PrioritizedTask pt : sequeuece) {
				System.out.print(pt.summary());
				if(++count % 5 == 0 ) {
					System.out.println(" ");
				}
			}
			System.out.println(" ");
			System.out.println(this + " Calling shutdownNow()");
			exec.shutdown();
		}
		
	}
}

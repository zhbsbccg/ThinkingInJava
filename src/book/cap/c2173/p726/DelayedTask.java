package book.cap.c2173.p726;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.TimeUnit.*;

public class DelayedTask implements Runnable, Delayed {
	private static int counter = 0;
	private final int id = counter++;
	private final int delta;
	private final long trigger;
	protected static List<DelayedTask> sequence = new ArrayList<DelayedTask>();
	public DelayedTask(int delayInMilliseconds) {
		delta = delayInMilliseconds;
		trigger = System.nanoTime() + TimeUnit.NANOSECONDS.convert(delta, TimeUnit.MILLISECONDS);
		sequence.add(this);
	}
	@Override
	public int compareTo(Delayed o) {
		// TODO Auto-generated method stub
		DelayedTask _this = (DelayedTask)o;
		if(trigger < _this.trigger) return -1;
		if(trigger > _this.trigger) return 1;
		return 0;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		// TODO Auto-generated method stub
		return unit.convert(trigger - System.nanoTime(), TimeUnit.NANOSECONDS);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(this + " ");
	}
	
	public String toString(){
		return String.format("[%1$-4d] Task %s", delta, id);
	}
	
	public String summary() {
		return "(" + id +":" + delta + ")";
	}
	public static class EndSentinel extends DelayedTask{
		private ExecutorService exec;
		public EndSentinel(int delay, ExecutorService e) {
			super(delay);
			exec = e;
			// TODO Auto-generated constructor stub
		}
		public void run() {
			for(DelayedTask pt: sequence) {
				System.out.println(pt.summary() + " ");
			}
			System.out.println(this + " Calling shutdownNow()");
			exec.shutdownNow();
		}
		
	}
}

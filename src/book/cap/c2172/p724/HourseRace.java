package book.cap.c2172.p724;

import java.awt.print.Printable;
import java.util.*;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
public class HourseRace {
	 static final int FINISH_LINE = 75;
	 private List<Horse> horses = new ArrayList<Horse>();
	 private ExecutorService exec = Executors.newCachedThreadPool();
	 private CyclicBarrier barrier;
	 public HourseRace(int nHorses, final int pause) {
		 barrier = new CyclicBarrier(nHorses, new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				StringBuilder s = new StringBuilder();
				for(int i = 0; i < FINISH_LINE; i++) {
					s.append("=");
				}
				System.out.print(s);
				for(Horse horse : horses) {
					System.out.print(horse.tracks());
				}
				for(Horse horse : horses) {
					if(horse.getStrides() >= FINISH_LINE) {
						System.out.print(horse + "won!");
						exec.shutdownNow();
						return;
					}
				}
				try {
					TimeUnit.MILLISECONDS.sleep(pause);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					System.out.println("barrier-action sleep interrupted");
				}
			}
		});
		 for(int i = 0; i < nHorses; i++) {
			 Horse horse = new Horse(barrier);
			 horses.add(horse);
			 exec.execute(horse);
		 }
	 }
	 public static void main(String[] args) {
		 int nHorses = 7;
		 int pause = 200;
		 new HourseRace(nHorses, pause);
		 
	 }
}

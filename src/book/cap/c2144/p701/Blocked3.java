package book.cap.c2144.p701;

import java.util.concurrent.TimeUnit;

public class Blocked3 implements Runnable {
	private volatile double d=0.0;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(!Thread.interrupted()){
			//while(true){
				//point1
				NeedsCleanup n1=new NeedsCleanup(1);
				try {
					System.out.println("Sleeping");
					TimeUnit.SECONDS.sleep(1);
					//point2
					NeedsCleanup n2=new NeedsCleanup(2);
					try {
						System.out.println("Calculating");
						for(int i=1; i<2500000 ;i++){
							d=d+(Math.PI + Math.E) /d;
						}
						System.out.println("Finish time-consuming operation");
					} finally{
						n2.cleanup();
					}
				} finally{
					n1.cleanup();
				}
				
			}
			//System.out.println("Exiting via while() test");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("Exiting via InterrupedException");
			e.printStackTrace();
		}
	}

	public static class InterruptingIdiom{
		public static void main(String[] args){
			try {
				Thread t=new Thread(new Blocked3());
				t.start();
				TimeUnit.MILLISECONDS.sleep(3000);
				t.interrupt();
				//���ۣ���sleep�����н����жϡ�point2֮��intreeuptedѭ��һ�ν�����ͨ��Thread.interrupted()������
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
		
	}
}
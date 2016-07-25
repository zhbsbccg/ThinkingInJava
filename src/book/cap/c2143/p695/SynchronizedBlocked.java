package book.cap.c2143.p695;

public class SynchronizedBlocked implements Runnable {
	public synchronized void f(){
		//死循环
		while(true)
			Thread.yield();
	}
	public SynchronizedBlocked(){
		//另开一个任务，占用锁
		new Thread(){
			public void run(){
				f();
			}
		}.start();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Try to call f()");
		f();//进入阻塞，无法获取锁
		System.out.println("Exiting SynchronizedBlocked.run()");
		
	}

}

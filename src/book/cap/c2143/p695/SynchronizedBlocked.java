package book.cap.c2143.p695;

public class SynchronizedBlocked implements Runnable {
	public synchronized void f(){
		//��ѭ��
		while(true)
			Thread.yield();
	}
	public SynchronizedBlocked(){
		//��һ������ռ����
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
		f();//�����������޷���ȡ��
		System.out.println("Exiting SynchronizedBlocked.run()");
		
	}

}

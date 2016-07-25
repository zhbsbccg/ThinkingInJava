package book.cap.c2143.p695;

import java.io.IOException;
import java.io.InputStream;

public class IOBlocked implements Runnable {
	private InputStream in;
	public IOBlocked(InputStream is){in=is;}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("�ȴ����룺");
		try {
			in.read();//��������ȴ�����
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(Thread.currentThread().isInterrupted()){
				System.out.println("IOBlocked���ж�");
			}else{
				throw new RuntimeException(e);
			}
		}
		System.out.println("Exiting IOBlocked.run()");
	}

}

package book.cap.c2143.p695;

import java.io.IOException;
import java.io.InputStream;

public class IOBlocked implements Runnable {
	private InputStream in;
	public IOBlocked(InputStream is){in=is;}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("等待输入：");
		try {
			in.read();//进入输入等待阻塞
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(Thread.currentThread().isInterrupted()){
				System.out.println("IOBlocked被中断");
			}else{
				throw new RuntimeException(e);
			}
		}
		System.out.println("Exiting IOBlocked.run()");
	}

}

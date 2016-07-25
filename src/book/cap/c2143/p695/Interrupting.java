package book.cap.c2143.p695;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Interrupting {
	private static ExecutorService exec=Executors.newCachedThreadPool();
	public static void test(Runnable r) throws InterruptedException{
		Future<?> f=exec.submit(r);
		TimeUnit.SECONDS.sleep(2);
		System.out.println("Interrupting "+r.getClass().getName());
		f.cancel(true);
		System.out.println("Interrupt send to "+ r.getClass().getName());
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			//test(new SleepBlocked());//可以中断，并报错进入catch
			//test(new IOBlocked(System.in));//发送中断信息无用，无法中断，并且在做一些操作时不需要catchInterruptedException
			test(new SynchronizedBlocked());//同上
			TimeUnit.SECONDS.sleep(5);
			System.out.println("run System.exit(0)");
			System.exit(0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

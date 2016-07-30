package book.cap.c2153.p709;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Restaurant {
	Meal meal;
	ExecutorService exec=Executors.newCachedThreadPool();
	WaitPerson waitPerson=new WaitPerson(this);
	Chef chef=new Chef(this);
	public Restaurant(){
		exec.execute(waitPerson);
		exec.execute(chef);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Restaurant();
	}

}

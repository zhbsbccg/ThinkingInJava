package book.cap.c2153.p709;

import java.util.concurrent.TimeUnit;

public class Chef implements Runnable {
	//必须知道为哪座餐厅服务，餐厅为数据交互集中点。其中用来交互meal来传递信息
	private Restaurant restaurant;
	private int count=0;
	public Chef(Restaurant r){
		restaurant=r;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(!Thread.interrupted()){
				synchronized(this){
					while(restaurant.meal != null){
						wait();
					}
					if(++count == 10){
						System.out.println("Out of food, closing");
						//超过负荷。。。只制作10份食物
						restaurant.exec.shutdownNow();
					}
					System.out.println("Order up!");//下单了
					synchronized(restaurant.waitPerson){//获取服务员的锁，防止其进行meal判断
						restaurant.meal=new Meal(count);//制作食物
						restaurant.waitPerson.notifyAll();//通知服务员端菜
					}
					TimeUnit.MILLISECONDS.sleep(100);
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("Chef interrupted");
		}
	}

}

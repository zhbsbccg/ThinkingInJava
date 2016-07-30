package book.cap.c2153.p709;

public class WaitPerson implements Runnable {
	private Restaurant restaurant;
	public WaitPerson(Restaurant restaurant) {
		// TODO Auto-generated constructor stub
		this.restaurant=restaurant;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(!Thread.interrupted()){
				synchronized(this){
					//判断菜是否做好
					while(restaurant.meal==null){
						wait();// for the chef to produce a meal
						//没有拿到菜单，进行等待
						
					}
				}
				System.out.println("Waitperson got " + restaurant.meal);
				//让厨师开始做菜
				synchronized(restaurant.chef){//获取厨师类的锁，防止厨师类进行meal判断
					restaurant.meal=null;//食物已经被端出
					restaurant.chef.notifyAll();//通知厨师开始做菜
				}
				
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("WaitPerson interrupted");
		}
	}

}

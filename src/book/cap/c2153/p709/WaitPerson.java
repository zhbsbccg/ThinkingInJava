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
					//�жϲ��Ƿ�����
					while(restaurant.meal==null){
						wait();// for the chef to produce a meal
						//û���õ��˵������еȴ�
						
					}
				}
				System.out.println("Waitperson got " + restaurant.meal);
				//�ó�ʦ��ʼ����
				synchronized(restaurant.chef){//��ȡ��ʦ���������ֹ��ʦ�����meal�ж�
					restaurant.meal=null;//ʳ���Ѿ����˳�
					restaurant.chef.notifyAll();//֪ͨ��ʦ��ʼ����
				}
				
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("WaitPerson interrupted");
		}
	}

}

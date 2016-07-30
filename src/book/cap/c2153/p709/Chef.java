package book.cap.c2153.p709;

import java.util.concurrent.TimeUnit;

public class Chef implements Runnable {
	//����֪��Ϊ�����������񣬲���Ϊ���ݽ������е㡣������������meal��������Ϣ
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
						//�������ɡ�����ֻ����10��ʳ��
						restaurant.exec.shutdownNow();
					}
					System.out.println("Order up!");//�µ���
					synchronized(restaurant.waitPerson){//��ȡ����Ա��������ֹ�����meal�ж�
						restaurant.meal=new Meal(count);//����ʳ��
						restaurant.waitPerson.notifyAll();//֪ͨ����Ա�˲�
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

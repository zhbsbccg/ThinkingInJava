package book.cap.c2154.p715;
//Consume the toast
public class Eater implements Runnable {
	private ToastQueue finishedQueue;
	private int counter = 0;
	public Eater(ToastQueue finished) {
		this.finishedQueue = finished;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(!Thread.interrupted()) {
				Toast t = finishedQueue.take();
				if (t.getId() != counter ++ || t.getStatus() != Toast.Status.JAMMED) {
					System.out.println(">>>>>>> Error£º " + t);
					System.exit(1);
				} else {
					System.out.println("Chomp! " + t);
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Eater interrupted");
		}
		System.out.println("Eater off");
	}

}

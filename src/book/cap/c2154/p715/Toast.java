package book.cap.c2154.p715;

public class Toast {
	public enum Status { DRY, BUTTERED, JAMMED }
	private Status status = Status.DRY;//default is dry
	private final int id;
	
	public Toast(int id) {
		this.id = id;
	}
	//update status to buttered
	public void butter() {
		status = Status.BUTTERED;
	}
	//update status to jam
	public void jam() {
		status = Status.JAMMED;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public int getId() {
		return id;
	}
	
	@Override
	public String toString(){
		return "Toast" + id + ": " + status;
	}
}

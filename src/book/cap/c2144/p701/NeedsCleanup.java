package book.cap.c2144.p701;

public class NeedsCleanup {
	private final int id;
	public NeedsCleanup(int ident){
		id=ident;
		System.out.println("NeedCleanup:"+id);
	}
	public void cleanup(){
		System.out.println("cleanup!" + id);
	}
}

public interface Library 
{
	// METHODS FOR BORROWING, LOOKUP AND SUBMIT
	void borrow(String book);
	boolean lookup(String book);
	void submit(String book);
}

import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateful;
@Stateful
public class librarybean implements Library 
{
	public HashMap<String , Integer> shelf = new HashMap<>(); 
	
	public librarybean()
	{
		shelf.put("CV",1);
		shelf.put("C++",1);
		shelf.put("DC",2);
	}
	
	public static void main(String[] args) 
	{
		librarybean lb = new librarybean();
		lb.borrow("CV");
		lb.lookup("DC");
		lb.submit("C++");		
	}

	@Override
	public void borrow(String book) 
	{
		shelf.put(book, shelf.get(book)-1);
	}

	@Override
	public boolean lookup(String book) 
	{
		return shelf.containsKey(book);
	}

	@Override
	public void submit(String book) 
	{
		shelf.put(book, shelf.get(book)+1);
	}
}

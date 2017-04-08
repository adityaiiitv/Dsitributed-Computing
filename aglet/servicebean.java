import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.Stateless;
import com.sun.beans.finder.FieldFinder;

@Stateless
public class servicebean implements ServiceInterface 
{
	public static void main(String[] args) 
	{
		// Auto-generated method stub
		servicebean s = new servicebean();
		System.out.println(s.finddate().toString());
	}
	@Override
	public Date finddate() 
	{
		// Auto-generated method stub
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return date;
	}
}

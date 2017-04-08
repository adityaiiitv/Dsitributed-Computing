import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class DayTimeServer
{

	public static void main(String[] args) throws IOException
	{
		ServerSocket server = null;
		try
		{
			server = new ServerSocket(9999);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		Socket socket = server.accept();
		b = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		
		Date date = new Date();
		b.write(date.toString());
		b.flush();
		b.close();
		
	}

	OutputStreamWriter out ;
	static BufferedWriter b ;
}

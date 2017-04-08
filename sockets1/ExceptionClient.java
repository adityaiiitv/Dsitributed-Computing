import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;import java.net.UnknownHostException;
public class ExceptionClient
{
	public static void main(String[] args) throws UnknownHostException, IOException
	{
		int i;
		Socket socket=new Socket("127.0.0.1",9999);
		System.out.println("The client is now connected.");
		socket.setSoTimeout(3000);
		System.out.println("Wait.");
		BufferedReader reader=null;
		InputStream is=socket.getInputStream();
		try
		{
			reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String res = reader.readLine();
			System.out.println("Answer: " + res);
		}
		catch(SocketTimeoutException e)
		{
			System.out.println("Something wrong with the server.");
			System.exit(0);
		}	
		socket.close();
	}
}

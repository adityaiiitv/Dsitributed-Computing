import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class DayTimeClient
{
	public static void main(String[] args) throws IOException
	{
		Socket client = null;
		
		try
		{
			client = new Socket("127.0.0.1",9999);
		}
		catch(UnknownHostException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		br = new BufferedReader(new InputStreamReader(client.getInputStream()));
		String time = br.readLine();
		System.out.println(time);
		br.close();
		client.close();
	}
	static BufferedReader br;
	static InputStreamReader in;
}

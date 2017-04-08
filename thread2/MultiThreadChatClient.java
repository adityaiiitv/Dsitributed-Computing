import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class MultiThreadChatClient implements Runnable
{
	private static BufferedReader inputLine = null;
	private static boolean closed = false;
	private static Socket clientSocket = null;
	private static PrintStream os = null;
	private static DataInputStream is = null;
  
	public static void main(String[] args) 
	{
		int portNumber = 9999;
		String host = "localhost";
	
		if (args.length < 2) 
		{
			//System.out.println("\n Error in usage.");
		} 
	
		else 
		{
			host = args[0];
			portNumber = Integer.valueOf(args[1]).intValue();
		}

		try 
		{
			clientSocket = new Socket(host, portNumber);
			inputLine = new BufferedReader(new InputStreamReader(System.in));
			
			os = new PrintStream(clientSocket.getOutputStream());
			is = new DataInputStream(clientSocket.getInputStream());
		} 
	
		catch (UnknownHostException e) 
		{
			System.err.println("Error with: " + host);
		} 
	
		catch (IOException e) 
		{
			System.err.println("Cannot connect to the host. "+ host);
		}
	
		if (clientSocket != null && os != null && is != null) 
		{
			try 
			{
				new Thread(new MultiThreadChatClient()).start();
			
				while (!closed) 
				{
					os.println(inputLine.readLine().trim());
				}
				os.close();
				is.close();
				clientSocket.close();
			} 
	
			catch (IOException e) 
			{
				System.err.println("IOException:  " + e);
			}
		}
	}

	public void run() 
	{
		String responseLine;
	
		try 
		{
	
			while ((responseLine = is.readLine()) != null) 
			{
				System.out.println(responseLine);
				if (responseLine.indexOf("Exiting") != -1)
					break;
			}
	
			closed = true;
		} 
		
		catch (IOException e) 
		{
			System.err.println("IOException: " + e);
		}
	}
}

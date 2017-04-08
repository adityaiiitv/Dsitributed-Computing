import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;

public class MultiThreadChatServer 
{
	private static final int max = 3;
	private static final clientThread[] threads = new clientThread[max];
	private static ServerSocket serverSocket = null;
	private static Socket clientSocket = null;

	public static void main(String args[]) 
	{
		int portNumber = 9999;
		try 
		{
			serverSocket = new ServerSocket(portNumber);
		} 
		catch (IOException e) 
		{
			System.out.println(e);
		}

		while (true) 
		{
			try 
			{
				clientSocket = serverSocket.accept();
				int i=0;
				for(i=0;i<max;i++) 
				{
					if(threads[i] == null) 
					{
						(threads[i] = new clientThread(clientSocket, threads)).start();
						break;
					}
				}
				if (i == max) 
				{
					PrintStream os = new PrintStream(clientSocket.getOutputStream());
					os.println("Maximum limit reached.");
					os.close();
					clientSocket.close();
				}
			} 
			catch (IOException e) 
			{
				System.out.println(e);
			}
		}
	}
}

class clientThread extends Thread 
{
	private final clientThread[] threads;
	private int max;
	private DataInputStream is = null;
	private PrintStream os = null;
	private Socket clientSocket = null;

	public clientThread(Socket clientSocket, clientThread[] threads) 
	{
		this.clientSocket = clientSocket;
		this.threads = threads;
		max = threads.length;
	}

	public void run() 
	{
		int max = this.max;
		clientThread[] threads = this.threads;
	
		try 
		{
			is = new DataInputStream(clientSocket.getInputStream());
			os = new PrintStream(clientSocket.getOutputStream());
			os.println(" Enter the username you want to keep.");
			String name = is.readLine().trim();
			os.println(" Start chatting. To end just type exit.");
			
			for (int i = 0; i < max; i++) 
			{
				if (threads[i] != null && threads[i] != this) 
				{
					threads[i].os.println(" " + name+ " has joined.");
				}
			}
			
			while (true) 
			{
				String line = is.readLine();
				if (line.startsWith("exit")) 
				{
					break;
				}
				for (int i = 0; i < max; i++) 
				{
					if (threads[i] != null) 
					{
						threads[i].os.println(" @" + name + " says: " + line);
					}
				}
			}
			
			for (int i = 0; i < max; i++) 
			{
				if (threads[i] != null && threads[i] != this) 
				{
					threads[i].os.println(" Person: " + name+ " has left.");
				}
			}

			for (int i = 0; i < max; i++) 
			{
				if (threads[i] == this) 
				{
					threads[i] = null;
				}
			}

			is.close();
			os.close();
			clientSocket.close();
		} 
		catch (IOException e) 
		{
		}
	}
}

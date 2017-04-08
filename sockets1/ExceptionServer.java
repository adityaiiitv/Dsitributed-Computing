import java.io.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class ExceptionServer
{
	public static void main(String[] args) throws IOException, InterruptedException
	{
		ServerSocket socket=null;
		try
		{
			socket=new ServerSocket(9999);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		Socket socket1=null;
		try
		{
			socket1 = socket.accept();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		Thread.sleep(1000);
		BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(socket1.getOutputStream()));
		writer.write("Wait is over.");
		writer.flush();
		}
	}

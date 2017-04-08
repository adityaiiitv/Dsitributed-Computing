import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException
	{
		ServerSocket server = null;
		try
		{
			server = new ServerSocket(PORT);
			System.out.println("The server has started.");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		Socket socket = server.accept();
		
		br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		
		while(true)
		{	
	     	int num = br.read(); 
	        
	        switch(num)
	        {
				case 1:
					int a = br.read();
					int b = br.read();
					System.out.println("First number is: " +a );
					System.out.println("Second number is: " +b);
					bw.write((a+b));
					bw.flush();
					break;
					
				case 2:
					int d = br.read();
					System.out.println("Number is: "+d);
					bw.write(fact(d));
					bw.flush();
					break;
			
				case 3:
					int c = br.read();
					System.out.println("Number is: " +c);
					String result = Integer.toBinaryString(c);
					bw.write(""+result.toString());
					bw.newLine();
					bw.flush();
					break;
			
				default:
					System.out.println("Waiting for the client.");
					break;
			}
		}
	}

	static int fact(int n)
	{    
		if(n==0)
		{    
			return 1;
		}    
		else
		{    
		    return(n*fact(n-1));    
		}    
	}
	static BufferedReader br;
	static BufferedWriter bw;
	static int PORT = 9999;
}

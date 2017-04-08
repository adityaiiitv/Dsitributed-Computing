import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client
{
	public static void main(String[] args) throws IOException
	{
		Socket client = null;
		
		try
		{
			client = new Socket(SERVER,PORT);
		}
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		
		br = new BufferedReader(new InputStreamReader(client.getInputStream()));
		bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		
		Scanner sc = new Scanner(System.in);
		int keep = 1, num = -1;
		System.out.println("Press:\n 1. For sum of two numbers.\n " + "2. Factorial of a number.\n " + "3. Decimal to binary.\n");
		while(keep==1){
			num = sc.nextInt();
			bw.write(num);
			bw.flush();
			switch(num)
			{
				case 1:
					System.out.println("Enter two number to add.\n");
					int a = sc.nextInt();
					int b = sc.nextInt();
					bw.write(a);
					bw.flush();
					bw.write(b);
					bw.flush();
					System.out.println("Result : " +br.read());
					break;
					
				case 2:
					System.out.println("Enter number to find the factorial\n");
					int c = sc.nextInt();
					bw.write(c);
					bw.flush();
					System.out.println("Result : " +br.read());
					break;
				
				case 3:
					System.out.println("Enter number to convert to binary.\n");
					int d = sc.nextInt();
					bw.write(d);
					bw.flush();
					String result = br.readLine();
					System.out.println("Result : " +result);
					break;	
			
				default:
					System.out.println("Press:\n 1. For sum of two numbers.\n " + "2. Factorial of a number.\n " + "3. Decimal to binary.\n");
					keep = 1;
					break;
			}
			System.out.println("Press 1 to continue any other number to finish.");
			keep = sc.nextInt();
		}	
	}
	static BufferedReader br;
	static BufferedWriter bw;
	final static int PORT = 9999;
	final static String SERVER = "127.0.0.1";
}

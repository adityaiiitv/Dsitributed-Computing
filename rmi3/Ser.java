import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class Ser
{
	public static void main(String[] args) throws Exception
	{
		ServerSocket Server = new ServerSocket(1245);
		System.out.println("The server has started.");
		Socket client = Server.accept();
        byte[] data = null;
        DataInputStream In = new DataInputStream(client.getInputStream());
		int len = In.readInt();
        double[] arr = new double[len];
        for(int i=0;i<len;i++)
        {
        	arr[i] = In.readDouble();
        	System.out.println(arr[i]+"\n");
        	arr[i] = Math.sqrt(arr[i]);
        }
		DataOutputStream Out = new DataOutputStream(client.getOutputStream());
		Out.writeInt(arr.length);
		for(int i=0;i<len;i++)
		{
			Out.writeDouble(arr[i]);
		}
	}
}

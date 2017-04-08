import java.io.*;
import java.net.*;

public class Cli
{
	public static void main(String[] args) throws Exception
	{
		Socket client = new Socket("localhost",1245);
		double[] data = new double[5];
   
		data[0] = 99;
		data[1] = 88;
		data[2] = 77;
		data[3] = 66;
		data[4] = 55;
		
		DataOutputStream Out = new DataOutputStream(client.getOutputStream());
		Out.writeInt(data.length);
		for(int i=0;i<data.length;i++)
		{
			Out.writeDouble(data[i]);		
	    }
	    
		DataInputStream In = new DataInputStream(client.getInputStream());
		int len = In.readInt();
		for(int i=0;i<len;i++)
		{
			data[i] = In.readDouble();
			System.out.println(data[i] + "\n");
		}
	}
}

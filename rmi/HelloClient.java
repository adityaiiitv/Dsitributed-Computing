import java.io.*;
import java.rmi.*;

public class HelloClient
{
	public static void main(String args[])
	// Main function
	{
		try
		{
			int RMIPort;	// RMI Port number
			String hostName;
			InputStreamReader is = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(is);
			System.out.println("RMI registry host name: ");
			hostName = br.readLine();	// Read hostname
			System.out.println("RMI registry port number: ");
			String portNum = br.readLine();
			RMIPort = Integer.parseInt(portNum);
			String registryUrl = "rmi://" + hostName+ ":" + portNum + "/hello";
			// Puts the URL of the registry in registryURL
			HelloInterface h = (HelloInterface)Naming.lookup(registryUrl);
			// Object of HelloInterface looks up the URL in the registry.
			System.out.println("Searching done");
			String message = h.sayHello(" Aditya Prakash ");
			// Call the function.
			System.out.println("HelloClient: "+ message);
		}
		catch(Exception e)
		{
			System.out.println("Some Error: "+e);
		}
	}
}
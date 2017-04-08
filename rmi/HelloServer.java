import java.rmi.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.net.*;
import java.io.*;

public class HelloServer
{
	public static void main(String args[])
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(is);
		String portNum, registryUrl;
		// String for port number and URL for registry
		try
		{
			System.out.println("Enter RMIregistry port number: ");
			portNum = (br.readLine()).trim();
			int RMIPortNum = Integer.parseInt(portNum);
			startRegistry(RMIPortNum);
			HelloImpl exportedObj = new HelloImpl();
			// Object of HelloIMpl.
			registryUrl = "rmi://localhost:" + portNum + "/hello";
			// Create the registry for URL again.
			Naming.rebind(registryUrl, exportedObj);
			// Bind the URL with the object.
			System.out.print("Server registered. Registry currently contains: \n");
			listRegistry(registryUrl);
			// List the URL in the registry.
			System.out.print("Hello Server is ready\n");
		}
		catch(Exception re)
		{
			System.out.println("Exception: "+ re);
		}
	}

	private static void listRegistry(String registryUrl)throws RemoteException, MalformedURLException
	{
		System.out.print("Registry " + registryUrl + "has: \n");
		String [] names = Naming.list(registryUrl);
		for(int i=0;i<names.length; i++)
		{
			System.out.println(names[i]);
		}
	}

	private static void startRegistry(int rMIPortNum)throws RemoteException 
	{	
		// Method to start the Registry.
		try
		{
			Registry registry = LocateRegistry.getRegistry(rMIPortNum);
			// Object of registry locates.
			registry.list();
		}
		catch(RemoteException e)
		{
			System.out.print("RMI registry cannot be located at: " + rMIPortNum);
			Registry registry = LocateRegistry.createRegistry(rMIPortNum);
			System.out.print("RMI registry at port: "+rMIPortNum);
			// Show in case of error.
		}
	}
}

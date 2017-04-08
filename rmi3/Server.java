import java.io.*;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server
{
	public static void main(String[] args)
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(is);
		String portNum, registryURL, registryURL2;
		try
		{
			System.out.println("Enter the Port Number:");
			portNum = (br.readLine()).trim();	
			int RMIPortNum = Integer.parseInt(portNum);
			startRegistry(RMIPortNum);
		
			ATMImpl exportedObj = new ATMImpl();
			registryURL = "rmi://localhost:" + portNum + "/ATM";
			Naming.rebind(registryURL, exportedObj);
			System.out.println("The server is registered and ready.");
			listRegistry(registryURL);
		}
		catch(Exception e)
		{
			System.out.println("Exception in HelloServer.main: " + e );	
		}

	}
	
	private static void startRegistry(int RMIPortNum) throws RemoteException
	{	
		try
		{
			Registry reg = LocateRegistry.getRegistry(RMIPortNum);	
            reg.list();
		}
		catch(RemoteException e)
		{	
			System.out.println("Registry cannot be located at: " + RMIPortNum);
			LocateRegistry.createRegistry(RMIPortNum);
			System.out.println("Registry Created at: " + RMIPortNum);
		}
	}
	private static void listRegistry(String registryURL) throws RemoteException, MalformedURLException
	{	
		System.out.println("Registry " + registryURL + " has: ");	
		String[] n = Naming.list(registryURL);
		for(int i=0; i < n.length; i++)
		{
			System.out.println(n[i]);
		}
	}
}

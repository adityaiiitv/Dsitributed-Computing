import java.rmi.*;
import java.rmi.server.*;
import java.lang.*;

public class HelloImpl extends UnicastRemoteObject implements HelloInterface
{
	public HelloImpl() throws RemoteException
	{
		super();
		// Contructor
	}
	public double[] sayHello(double[] arr,int size) throws RemoteException
	{
		for(int i=0;i<size;i++)
		{
			arr[i]=Math.sqrt(arr[i]);
		}
		return arr;
		//return "Hello World" + name;
		// Prints Hello World "name entered"
	}
}

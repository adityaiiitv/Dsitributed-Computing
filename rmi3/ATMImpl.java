import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class ATMImpl extends UnicastRemoteObject implements ATMInterface
{ 
	private HashMap<Integer,Double> h = new HashMap<Integer,Double>();

	public ATMImpl() throws RemoteException
	{
		 super();
	}

	public void CreateAcc()
	{
		h.put(100,400.0);
		h.put(1,300.0);
		h.put(2,200.0);
		h.put(3,100.0);
	}
	
	public void deposit(int account, double amount) throws RemoteException
	{	
		h.put(account,h.get(account)+amount);
	}

	public void withdraw(int account,double amount) throws RemoteException
	{
		if(h.get(account)>0)
		{ 
		    h.put(account,h.get(account)-amount);
		}
	}
	
	public double inquiry(int account) throws RemoteException
	{	
		return h.get(account);
	}
}

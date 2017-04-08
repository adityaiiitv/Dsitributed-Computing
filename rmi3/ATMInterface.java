import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ATMInterface extends Remote
{	
	public void deposit(int account, double amount) throws RemoteException;
	public void withdraw(int account, double amount) throws RemoteException;
	public double inquiry(int account) throws RemoteException;
	public void CreateAcc() throws RemoteException;
}

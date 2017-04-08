import java.rmi.Remote;

public interface HelloInterface extends Remote {
	public String sayHello(String name)	
	// A method which accepts a string.
	//  Only the prototype
		throws java.rmi.RemoteException;	
}

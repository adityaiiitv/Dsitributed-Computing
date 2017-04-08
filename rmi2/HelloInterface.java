import java.rmi.Remote;

public interface HelloInterface extends Remote {
	public double[] sayHello(double[] arr,int size)	
	// A method which accepts a string.
	//  Only the prototype
		throws java.rmi.RemoteException;	
}

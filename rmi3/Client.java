import java.io.*;
import java.rmi.Naming;
import java.util.*;

public class Client
{
	public static void main(String[] args)
	{
		
		try
		{	
			int RMIPort;
			String hostName;
			InputStreamReader is = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(is);
			System.out.println("Enter the Host Name: ");
			hostName = br.readLine();
			System.out.println("Enter the Port Number: ");
			String portNum = br.readLine();
			RMIPort = Integer.parseInt(portNum);
			String registryURL = "rmi://" + hostName + ":" + RMIPort + "/ATM";
			 
			ATMInterface h = (ATMInterface)Naming.lookup(registryURL);
		    System.out.println("Lookup Done.");
		    h.CreateAcc();
		    Scanner s = new Scanner(new InputStreamReader(System.in));
		    
		    int num=1,acc;
		    double amt;
		    while(num !=0)
		    {
				System.out.println("Enter 1 to deposit.\nEnter 2 to Withdraw.\nEnter 3 for balance.\nEnter 0 to end session.\n");
				num = s.nextInt();
				switch(num)
				{
					case 1:
							System.out.println("Enter the Account Number: ");
							acc = s.nextInt();
							System.out.println("Enter amount to deposit: ");
							amt = s.nextDouble();
							h.deposit(acc,amt);
							break;
						
					case 2: 
							System.out.println("Enter the Account Number: ");
							acc = s.nextInt();
							System.out.println("Enter the amount to withdraw: ");
							amt = s.nextDouble();
							h.withdraw(acc, amt);
							break;
		    		 
					case 3:
							System.out.println("Enter the Account Number: ");
							acc = s.nextInt();
							System.out.println("Balance in account: " + acc + " is: " + h.inquiry(acc));
							break;
		    	      
					case 0:
							num = 0;
							break;
		    	}
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception: " + e);			 
		}
	}
}



import java.net.InetAddress;
import java.util.Map;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class PlayerClient extends UnicastRemoteObject implements ClientRMIInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected PlayerClient() throws RemoteException
	{
		
	}
	
	public static void main(String[] args)
    {
        try
        {
            //Lookup for the service
            String url = "rmi://" + InetAddress.getLocalHost().getHostAddress() + ":52369/Hello";
            Remote lRemote = Naming.lookup(url);
            ServerRMIInterface lRemoteServer = (ServerRMIInterface) lRemote;
            
            System.out.println("Enter player number: ");
            Scanner input = new Scanner(System.in);
            String playerName = input.next();
            
            //Create a temperature monitor and register it as a Listener
            PlayerClient player = new PlayerClient();
            lRemoteServer.addPlayerListener(player);
            
            while(true) {
            	System.out.println("Enter move: ");
            	String value = input.next();
            	lRemoteServer.receiveMove(playerName, value);
            }
        }
        catch (Exception aInE)
        {
            System.out.println(aInE);
        }
    }

    public void mapChanged(Map<String, String> response) throws RemoteException
    {	System.out.println("----------------------------------------------------\n");
    	for (Map.Entry<String,String> entry : response.entrySet())  
            System.out.println(entry.getKey() + 
                             "-------->" + entry.getValue()+ "\n"); 
    
    }
}

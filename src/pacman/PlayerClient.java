

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
	private static ServerRMIInterface lRemoteServer;
	private static volatile boolean isGameOngoing=false;
	private static String playerName;
	private static Scanner input;

	protected PlayerClient() throws RemoteException
	{
		
	}
	
	public static void main(String[] args)
    {
        try
        {
            //Lookup for the service
        	System.out.println("Enter server IP: ");
            input = new Scanner(System.in);
            String url = "rmi://" + input.next() + ":52369/Hello";
            Remote lRemote = Naming.lookup(url);
            lRemoteServer = (ServerRMIInterface) lRemote;
            
            System.out.println("Enter player number: ");
            input = new Scanner(System.in);
            playerName = input.next();
            
            //Create a temperature monitor and register it as a Listener
            PlayerClient player = new PlayerClient();
            lRemoteServer.addPlayerListener(player);
            System.out.println("Waiting for all players to join");
            while(true) {
//            	System.out.println(isGameOngoing);
            	if(isGameOngoing) {
            		System.out.println("Enter move: ");
                	String value = input.next();
                	lRemoteServer.receiveMove(playerName, value);
            	}
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
    
    public void startGame() throws RemoteException
    {	
    	 try
         {
			isGameOngoing=true;
		}
		catch (Exception aInE)
		{
		    System.out.println(aInE);
		}
    }
    
//    private void sendInputs() throws RemoteException{
//    	try
//        {
//		    while(true) {
//		    	System.out.println("Enter move: ");
//		    	String value = input.next();
//		    	lRemoteServer.receiveMove(playerName, value);
//		    }
//        }
//        catch (Exception aInE)
//        {
//            System.out.println(aInE);
//        }
//    }
    
    public void getGameDetails() {
    	 try
         {
	    	System.out.println("Enter the number of players : ");
	    	int n = input.nextInt();
	    	lRemoteServer.setMaxCount(n);
         }
         catch (Exception aInE)
         {
             System.out.println(aInE);
         }
    }
}

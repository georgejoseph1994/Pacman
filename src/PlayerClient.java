

import java.net.InetAddress;
import java.util.Map;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import pacman.Occupant;
import pacman.PacmanMap;

public class PlayerClient extends UnicastRemoteObject implements ClientRMIInterface {


	private static final long serialVersionUID = 1L;

	protected PlayerClient() throws RemoteException
	{
		
	}
	
	public static void main(String[] args)
    {
        try
        {
            //Lookup for the service
        	System.out.println("Enter server IP: ");
            Scanner input = new Scanner(System.in);
//            String url = "rmi://" + input.next() + ":52369/Hello";
        	String url = "rmi://" + "192.168.1.145" + ":52369/Hello";
            Remote lRemote = Naming.lookup(url);
            ServerRMIInterface lRemoteServer = (ServerRMIInterface) lRemote;
            
            System.out.println("Enter player number: ");
            input = new Scanner(System.in);
            String playerName = input.next();
            

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

    public void mapChanged(String[][] grid) throws RemoteException
    {	
    	for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				
				System.out.print(grid[i][j]); 
			}
			System.out.println();
		}
    }
}

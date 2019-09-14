

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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ServerRMIInterface lRemoteServer;
	private static volatile boolean isGameOngoing=false;
	private static int playerName;
	private static Scanner input = new Scanner(System.in);

	protected PlayerClient() throws RemoteException
	{
		
	}
	
	public static void main(String[] args)
    {
        try
        {
            //Lookup for the service
//        	System.out.println("Enter server IP: ");
//            String url = "rmi://" + input.next() + ":52369/Hello";
        	String url = "rmi://" + "192.168.1.9" + ":52369/Hello";
            Remote lRemote = Naming.lookup(url);
            lRemoteServer = (ServerRMIInterface) lRemote;
            
            System.out.println("Enter player number: ");
            input = new Scanner(System.in);
            playerName = Integer.parseInt(input.nextLine());
            
            //Create a temperature monitor and register it as a Listener
            PlayerClient client = new PlayerClient();
            lRemoteServer.addPlayerListener(client, playerName);
            System.out.println("Waiting for all players to join");
            while(true) {
//            	System.out.println(isGameOngoing);
            	if(isGameOngoing) {
            		System.out.println("Enter move: ");
                	String value = input.next();
                	switch (value.charAt(0)) {
						case 'W':
						case 'w':
		                	lRemoteServer.receiveMove(playerName, "U");
							break;
						case 'A':
						case 'a':
		                	lRemoteServer.receiveMove(playerName, "L");
							break;
						case 'S':
						case 's':
		                	lRemoteServer.receiveMove(playerName, "D");
							break;
						case 'D':
						case 'd':
		                	lRemoteServer.receiveMove(playerName, "R");
							break;
						
					}
            	}
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

	public void stopGame() throws RemoteException
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

	@Override
	public void playerFailed() throws RemoteException {
		System.out.println("Player Failed");
	}

	@Override
	public void playerWon() throws RemoteException {
		System.out.println("Congrats!!!!You won the game!!!!!!");
	}
}

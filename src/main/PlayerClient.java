package main;


import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.Scanner;

import ui.ApplicationWindow;
import ui.ChooseMapScene;
import ui.ChooseStartingScene;
import ui.GameScene;
import ui.HostJoinScene;
import ui.PlayScene;

public class PlayerClient extends UnicastRemoteObject implements ClientRMIInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static ServerRMIInterface lRemoteServer;
	public boolean isGameOngoing=false;
	public int playerNo;
	public String playerName;
	public static Scanner input = new Scanner(System.in);
	public static ApplicationWindow aw;
	public PlayScene playScene;
	public static PlayerClient client;
	public int startingLocation;
	public boolean playerStatus = false;

	public int getPlayerNo() {
		return playerNo;
	}

	public void setPlayerNo(int playerNo) {
		this.playerNo = playerNo;
	}

	public String getPlayerName() {
		return playerName;
	}
	public int getStartingLocation() {
		return startingLocation;
	}

	public void setStartingLocation(int location) {
		this.startingLocation = location;
		this.playerStatus = true;
	}

	public boolean getPlayerStatus() {
		return playerStatus;
	}


	protected PlayerClient() throws RemoteException
	{
		
	}
	
	public static void main(String[] args)
    {
        try
        {
            PlayerClient client = new PlayerClient();
            aw = new ApplicationWindow();
            aw.launchWindow(args);
        }
        catch (Exception aInE)
        {
            System.out.println(aInE);
        }
    }

    public void mapChanged(String[][] grid) throws RemoteException
    {	

		playScene = new PlayScene(aw.getStage(), grid);
		aw.setScene(playScene);
    }
    
    public static boolean connectServer(String ip, String playerName) {
    	try {
    		System.out.println(ip);
	    	String url = "rmi://" + ip + ":52369/Hello";
	        Remote lRemote = Naming.lookup(url);
	        lRemoteServer = (ServerRMIInterface) lRemote;

            client = new PlayerClient();
            client.playerName = playerName;
            lRemoteServer.addPlayerListener(client);
	        return true;
	    }
	    catch (Exception aInE)
	    {
	        System.out.println(aInE);
	        return false;
	    }
    }
    
    public static void changePlayerDirection(String direction) {
		try {
	    	System.out.println(direction);
	    	if(direction.compareTo("UP")==0) {
					lRemoteServer.receiveMove(client.playerNo, "U");
	    	} else if(direction.compareTo("DOWN")==0) {
	    		lRemoteServer.receiveMove(client.playerNo, "D");
	    	} else if(direction.compareTo("LEFT")==0) {
	    		lRemoteServer.receiveMove(client.playerNo, "L");
	    	} else if(direction.compareTo("RIGHT")==0) {
	    		lRemoteServer.receiveMove(client.playerNo, "R");
	    	} 
		} catch (Exception aInE)
        {
            System.out.println(aInE);
        }
    }
    
	public void startGame() throws RemoteException
    {	
    	 try
         {
    		 System.out.println("Starting Game");
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
		GameScene hostJoinScene = new HostJoinScene(aw.getStage());
		aw.setScene(hostJoinScene);
	}

	public void getMapDetails() {
		GameScene chooseMapScene = new ChooseMapScene(aw.getStage());
		aw.setScene(chooseMapScene);
	}

	public static void setMaxCount(int playerCount) {
    	try {
			lRemoteServer.setMaxCount(client, playerCount);
		} catch (RemoteException e) {
	        System.out.println(e);
		}
	}

	public static void setMap(int mapId) {
		try {
			lRemoteServer.setMap(client, mapId);
		} catch (RemoteException e) {
	        System.out.println(e);
		}
	}

	public static boolean setLocation(int location) {
    	try {
    		if(lRemoteServer.setLocation(client, location)) {
    			System.out.println(client.getPlayerName()+" status is "+ client.getPlayerStatus());
    			return true;
    		}else {
    			client.playerStatus = false;
    		}
    		return false;
		} catch (RemoteException aInE)
	    {
	        System.out.println(aInE);
	    }
		return false;
	}

	public void getStartCorner(boolean[] availableLocation) throws RemoteException{
		System.out.println(Arrays.toString(availableLocation));
		GameScene chooseStartingScene = new ChooseStartingScene(aw.getStage(),availableLocation);
		aw.setScene(chooseStartingScene);
	 }

	@Override
	public void playerFailed() throws RemoteException {
		System.out.println("Player Failed");
	}

	@Override
	public void playerWon() throws RemoteException {
		System.out.println("Congrats!!!!You won the game!!!!!!");
	}

	@Override
	public void updateAvailableLocation(boolean[] availableLocation) throws RemoteException {
		System.out.println(Arrays.toString(availableLocation));
		if(aw.getScene().getClass().getSimpleName()=="ChooseStartingScene") {
			GameScene chooseStartingScene = new ChooseStartingScene(aw.getStage(),availableLocation);
			aw.setScene(chooseStartingScene);
		}
	}

}

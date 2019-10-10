package main;


import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import javax.swing.JFrame;

import ui.ApplicationWindow;
import ui.ChooseMapScene;
import ui.ChooseStartingScene;
import ui.ConnectScene;
import ui.GameScene;
import ui.HostJoinScene;

public class PlayerClient extends UnicastRemoteObject implements ClientRMIInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static ServerRMIInterface lRemoteServer;
	private boolean isGameOngoing=false;
	private int playerNo;
	private String playerName;
	private static Scanner input = new Scanner(System.in);
	private Renderer renderer;
	private static ApplicationWindow aw;
	private static PlayerClient client;

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
//            
//            System.out.println("Enter player number: ");
//            input = new Scanner(System.in);
//            client.playerName = Integer.parseInt(input.nextLine());
//            
//            //Create a temperature monitor and register it as a Listener
//            client.lRemoteServer.addPlayerListener(client, client.playerNo);
//            System.out.println("Waiting for all players to join");
//            while(true) {
////            	System.out.println(isGameOngoing);
//            	if(client.isGameOngoing) {
//            		System.out.println("Enter move: ");
//                	String value = input.next();
//                	switch (value.charAt(0)) {
//						case 'W':
//						case 'w':
//		                	client.lRemoteServer.receiveMove(client.playerName, "U");
//							break;
//						case 'A':
//						case 'a':
//		                	client.lRemoteServer.receiveMove(client.playerName, "L");
//							break;
//						case 'S':
//						case 's':
//		                	client.lRemoteServer.receiveMove(client.playerName, "D");
//							break;
//						case 'D':
//						case 'd':
//		                	client.lRemoteServer.receiveMove(client.playerName, "R");
//							break;
//						
//					}
//            	}
//            }
        }
        catch (Exception aInE)
        {
            System.out.println(aInE);
        }
    }

    public void mapChanged(String[][] grid) throws RemoteException
    {	
    	renderer.setMap(grid);
    	for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {			
				System.out.print(grid[i][j]); 
			}
			System.out.println();
		}

    }
    
    public static boolean connectServer(String ip) {
    	try {
	    	String url = "rmi://" + ip + ":52369/Hello";
	        Remote lRemote = Naming.lookup(url);
	        lRemoteServer = (ServerRMIInterface) lRemote;

            client = new PlayerClient();
            client.playerNo = 1;
            client.playerName = "Kevin";
            lRemoteServer.addPlayerListener(client, client.playerNo);
	        return true;
	    }
	    catch (Exception aInE)
	    {
	        System.out.println(aInE);
	        return false;
	    }
    }
    
	public void startGame() throws RemoteException
    {	
    	 try
         {
			isGameOngoing=true;
			renderer = new Renderer();
			JFrame frame = new JFrame();
			frame.setTitle(Renderer.TITLE);
			frame.add(renderer);
			frame.setResizable(false);
			frame.pack();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLocationRelativeTo(null);
			
			frame.setVisible(true);
			
			renderer.start();
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

	public static void setMaxCount(int playerCount) {
    	try {
			lRemoteServer.setMaxCount(client, playerCount);
		} catch (Exception aInE)
	    {
	        System.out.println(aInE);
	    }
	}
	
	public void getStartCorner() throws RemoteException{
		GameScene chooseStartingScene = new ChooseStartingScene(aw.getStage());
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
}

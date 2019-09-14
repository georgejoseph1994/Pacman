
import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import pacman.Cell;
import pacman.Monster;
import pacman.PacmanMap;
import pacman.Player;

import java.util.HashMap;

public class GameServer extends UnicastRemoteObject implements ServerRMIInterface {

	private static final long serialVersionUID = 1L;
	public static Map< Integer,String> hm = new HashMap< Integer,String>();
	public static Map< Integer, ClientRMIInterface> clients = new HashMap< Integer, ClientRMIInterface>();
	public static PacmanMap pacmanMap;
	public static Cell startingCell;
	public static Player player;
	public static Cell monsterCell;
	public static Monster monster;
	public static String userInput = "";
	public static ArrayList<Player> players = new ArrayList<Player>();
	private static int playerCount=0;
	private static int maxCount=4;
	private static GameServer lServer;
	public static int[][] initialCellPos = {
			{1,1},
			{9,9},
			{1,9},
			{9,1}
	}; 

	protected GameServer() throws RemoteException {
	}

	public void receiveMove(int name, String value) throws RemoteException {
		//userInput = value;
		hm.put(name, value);
		
//		game(value);

	}

	private void notifyPlayers() {
		for (Entry<Integer, ClientRMIInterface> client : clients.entrySet()) {
			try {
				client.getValue().mapChanged(pacmanMap.displayGrid());
			} catch (RemoteException aInE) {
				clients.remove(client.getKey());
			}
		}
	}

	public void addPlayerListener(ClientRMIInterface client, int playerName) throws RemoteException {
		try {
			clients.put(playerName, client);
			System.out.println(initialCellPos[playerName-1][0] + " : " + initialCellPos[playerName-1][1]);
			startingCell = pacmanMap.getCell(initialCellPos[playerName-1][0], initialCellPos[playerName-1][1]);
			player = new Player(playerName, startingCell);
			players.add( player );
			pacmanMap.addPlayer(player);
			playerCount++;
			if (playerCount==1) {
				System.out.println(playerCount);
				System.out.println("Host Player has joined");
				client.getGameDetails();
			}
			else {
				System.out.println("Another player has joined");
			}
			if(playerCount==maxCount) {
				for (ClientRMIInterface sclient : clients.values())
				{
						sclient.startGame();	
				}
			}
		} catch (Exception aInE) {
			System.out.println("Remote error- " + aInE);
		}
	}
	
	public void setMaxCount(int n) throws RemoteException {
		System.out.println(maxCount);
		maxCount=n;
		System.out.println(maxCount);
	}

	@Override
	public void removePlayerListener(ClientRMIInterface player) throws RemoteException {
		clients.remove(player);
	}

	public static void main(String[] args) {

		try {
			GameServer lServer = new GameServer();
			// Binding the remote object (stub) in the registry
			Registry reg = LocateRegistry.createRegistry(52369);
			String url = "rmi://localhost:52369/Hello";

			Naming.rebind(url, lServer);

			/*
			 * Initialising the pacman game objects
			 */
			pacmanMap = new PacmanMap();
			pacmanMap.initialiseTestMap();

			monsterCell = pacmanMap.getCell(5, 5);
			monster = new Monster(1, monsterCell);
			pacmanMap.addMonster(monster);
			

			while (true) {
				Thread.sleep(1000);

            	if(playerCount==maxCount) {
					for( int i=0; i<players.size(); i++) {
						pacmanMap.movePlayer(players.get(i), hm.get(i+1));
					}
					Player playerFailed = pacmanMap.moveMonster(monster, players);
					if(playerFailed!=null) {
						int playerNumber = Character.getNumericValue(playerFailed.getRepresentation().charAt(2));
						System.out.println(playerFailed.getRepresentation());
						ClientRMIInterface failedClient = clients.get(playerNumber);
						System.out.println("Player Failed "+playerFailed.getRepresentation().charAt(2) + " : " + failedClient);
						try {
							failedClient.mapChanged(pacmanMap.displayGrid());
							failedClient.playerFailed();
							playerCount--;
							maxCount--;
							players.remove(players.indexOf(playerFailed));
							clients.remove(playerNumber);
							if(maxCount==1) {
								Player playerWon = players.get(0);
								int wonPlayerNumber = Character.getNumericValue(playerWon.getRepresentation().charAt(2));
								ClientRMIInterface wonClient = clients.get(wonPlayerNumber);

								wonClient.mapChanged(pacmanMap.displayGrid());
								wonClient.playerWon();
								wonClient.stopGame();
								playerCount = -1;
							}
						} catch (RemoteException aInE) {
							clients.remove(playerNumber);
						}
					}
					lServer.notifyPlayers();
            	}
			}

		} catch (Exception aInE) {
			System.out.println("Remote error- " + aInE);
		}

	}


//	public static void game(String input) {
//		try {
//			System.out.println("this is input"+input);
//				switch (input) {
//					case "w":{
//						pacmanMap.movePlayer(player1, "U");
//						pacmanMap.moveMonster(monster, players);
//						break;
//					}case "a":{
//						pacmanMap.movePlayer(player1, "L");
//						pacmanMap.moveMonster(monster, players);
//						break;
//					}case "s":{
//						pacmanMap.movePlayer(player1, "D");
//						pacmanMap.moveMonster(monster, players);
//						break;
//					}case "d":{
//						pacmanMap.movePlayer(player1, "R");
//						pacmanMap.moveMonster(monster, players);
//						break;
//					}
//				}
//				pacmanMap.displayGrid();
//			
//		}catch(Exception e) {
//			System.out.println(e);
//		}
//		
//	}

}

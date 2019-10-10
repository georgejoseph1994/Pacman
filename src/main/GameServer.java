package main;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import pacman.Cell;
import pacman.Monster;
import pacman.PacmanMap;
import pacman.Player;

import java.util.HashMap;

public class GameServer extends UnicastRemoteObject implements ServerRMIInterface {

	private static final long serialVersionUID = 1L;
	public Map< Integer,String> hm = new HashMap< Integer,String>();
	public Map< Integer, ClientRMIInterface> clients = new HashMap< Integer, ClientRMIInterface>();
	public PacmanMap pacmanMap;
	public Cell startingCell;
	public Player player;
	public Cell monsterCell;
	public Monster monster;
	public String userInput = "";
	public ArrayList<Player> players = new ArrayList<Player>();
	private int playerCount=0;
	private int maxCount=4;
	public int[][] initialCellPos = {
			{1,1},
			{9,9},
			{1,9},
			{9,1}
	}; 
	public Map<Integer,String> playerStartLocation = new HashMap<Integer, String>();
	
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
//				System.out.println(playerCount);
				System.out.println("Host Player has joined");
				client.getGameDetails();
			}
			else {
				System.out.println("Another player has joined");
				System.out.println("Player asked for start corner");
				client.getStartCorner();
			}
			
			System.out.println("Locations");
			for (int key : playerStartLocation.keySet()) {
			    System.out.println(key + "   " + playerStartLocation.get(key));
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
	
	public void setMaxCount(ClientRMIInterface client, int n) throws RemoteException {
		System.out.println(maxCount);
		maxCount=n;
		System.out.println(maxCount);
		
		System.out.println("Player asked for start corner");
		client.getStartCorner();
	}
	
	public void setStartCorner(int name, String location) throws RemoteException {
		
		playerStartLocation.put(name, location);
		//check if location already in use and if not call it again
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
			lServer.pacmanMap = new PacmanMap();
			lServer.pacmanMap.initialiseTestMap();

			lServer.monsterCell = lServer.pacmanMap.getCell(5, 5);
			lServer.monster = new Monster(1, lServer.monsterCell);
			lServer.pacmanMap.addMonster(lServer.monster);
			

			while (true) {
				Thread.sleep(2000);
            	if(lServer.playerCount==lServer.maxCount) {
					for( int i=0; i<lServer.players.size(); i++) {
						lServer.pacmanMap.movePlayer(lServer.players.get(i), lServer.hm.get(i+1));
					}
					Player playerFailed = lServer.pacmanMap.moveMonster(lServer.monster, lServer.players);
					System.out.println(playerFailed+"*");
					if(playerFailed!=null) {
						System.out.println(playerFailed+"*");
						int playerNumber = Character.getNumericValue(playerFailed.getRepresentation().charAt(2));
						System.out.println(playerFailed.getRepresentation());
						ClientRMIInterface failedClient = lServer.clients.get(playerNumber);
						System.out.println("Player Failed "+playerFailed.getRepresentation().charAt(2) + " : " + failedClient);
						try {
							failedClient.mapChanged(lServer.pacmanMap.displayGrid());
							failedClient.playerFailed();
							lServer.playerCount--;
							lServer.maxCount--;
							lServer.players.remove(lServer.players.indexOf(playerFailed));
							lServer.clients.remove(playerNumber);
							if(lServer.maxCount==1) {
								Player playerWon = lServer.players.get(0);
								int wonPlayerNumber = Character.getNumericValue(playerWon.getRepresentation().charAt(2));
								ClientRMIInterface wonClient = lServer.clients.get(wonPlayerNumber);

								wonClient.mapChanged(lServer.pacmanMap.displayGrid());
								wonClient.playerWon();
								wonClient.stopGame();
								lServer.playerCount = -1;
							}
						} catch (RemoteException aInE) {
							lServer.clients.remove(playerNumber);
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

package main;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import exception.InvalidPlayerPositionException;
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
	public int mapID;
	public Monster monster;
	public String userInput = "";
	public ArrayList<Player> players = new ArrayList<Player>();
	private int playerCount=0;
	private int maxCount=4;
	private boolean[] availableLocation = {true, true, true, true};
	private boolean gameStart = false;
	public int[][] initialCellPos = {
			{1,1},
			{1,17},
			{19,1},
			{19,17}
	};
	public Map<Integer,String> playerStartLocation = new HashMap<Integer, String>();

	protected GameServer() throws RemoteException {
	}

	public void receiveMove(int name, String value) throws RemoteException {
		hm.put(name, value);
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

	@Override
	public void addPlayerListener(ClientRMIInterface client) throws RemoteException {
		if(playerCount<maxCount) {
			try {
				playerCount++;
				System.out.println(playerCount);
				clients.put(playerCount, client);
				client.setPlayerNo(playerCount);
				if (playerCount==1) {
					System.out.println("Host Player has joined");
					System.out.println("Getting game Details");
					client.getGameDetails();
				}
				else {
					System.out.println("Another player has joined");
					System.out.println("Player asked for start corner");
					client.getStartCorner(availableLocation);
				}
			} catch (Exception aInE) {
				playerCount--;
				System.out.println("Remote error 1- " + aInE);
			}
		} else {
			client.rejectLogin();
		}
	}

	@Override
	public void setMaxCount(ClientRMIInterface client, int n) throws RemoteException {
		maxCount=n;
		System.out.println("Max Count "+maxCount);
		System.out.println("Player asked for start corner");
		client.getMapDetails();
	}

	@Override
	public void setMap(ClientRMIInterface client, int mapId) throws RemoteException {
		this.mapID = mapId;
		System.out.println("Map ID: "+ this.mapID);
		client.getStartCorner(availableLocation);
	}

	public boolean setLocation(ClientRMIInterface client, int location) throws RemoteException  {
		boolean returnValue = false;
		if( availableLocation[location] == true ) {
			availableLocation[location] = false;
			client.setStartingLocation(location);
			returnValue = true;
			if(playerCount==maxCount) {
				boolean startGame = true;
				for(ClientRMIInterface sclient : clients.values()) {
					if(sclient.getPlayerStatus()==false) {
						startGame = false;
						System.out.println(sclient.getPlayerName() + " not Ready");
					}else {
						System.out.println(sclient.getPlayerName() + " Ready");
					}
				}
				if(startGame == true) {
					System.out.println("Game Started");
					for (ClientRMIInterface sclient : clients.values())
					{
						sclient.startGame();
					}
					intializeGameEnvironment();
				}
			}
		}
		for (ClientRMIInterface sclient : clients.values())
		{
			sclient.updateAvailableLocation(availableLocation);
		}
		return returnValue;
	}

	public void intializeGameEnvironment() {
		try {
			pacmanMap = new PacmanMap();
			pacmanMap.initialiseMap(mapID);

			Cell monsterCell = pacmanMap.getCell(10, 9);
			monster = new Monster(1, monsterCell);
			pacmanMap.addMonster(monster);

			for (ClientRMIInterface sclient : clients.values())
			{
				int playerNo = sclient.getPlayerNo();
				int playerLocation = sclient.getStartingLocation();
				Cell startingCell = pacmanMap.getCell(initialCellPos[playerLocation][0], initialCellPos[playerLocation][1]);
				Player player = new Player(playerNo, startingCell);
				players.add( player );
				pacmanMap.addPlayer(player);
				gameStart = true;

				System.out.println(players.size());
			}
		} catch (InvalidPlayerPositionException e) {
			System.out.println("Invalid Position ");
		} catch (Exception aInE) {
			System.out.println("Remote error 1- " + aInE);
			gameStart = false;
		}
	}

	@Override
	public void removePlayerListener(ClientRMIInterface player) throws RemoteException {
		clients.remove(player);
	}

	public static void main(String[] args) {

		GameServer lServer = null;
		try {
			System.setProperty("java.rmi.server.hostname","192.168.43.117");
			lServer = new GameServer();
			// Binding the remote object (stub) in the registry
			
			Registry reg = LocateRegistry.createRegistry(52369);
			String url = "rmi://0.0.0.0:52369/Hello";

			Naming.rebind(url, lServer);
		} catch (RemoteException e) {
			System.out.println("Error while initiating");
			return;
		} catch (MalformedURLException e) {
			System.out.println("Error  while binding the server");
			return;
		}

		/*
		 * Initialising the pacman game objects
		 */


		int monsterSleep = 3;
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Error while thread sleeping");
			}
        	if(lServer.gameStart==true) {
				for( int i=0; i<lServer.players.size(); i++) {
					lServer.players.set(i, lServer.pacmanMap.movePlayer(lServer.players.get(i), lServer.hm.get(i+1)));
				}
				Player playerFailed = null;
				if(monsterSleep==0)
					playerFailed = lServer.pacmanMap.moveMonster(lServer.monster, lServer.players);
				else
					monsterSleep--;
				
				if(playerFailed!=null) {
					monsterSleep = 3;
					int playerNumber = Character.getNumericValue(playerFailed.getRepresentation().charAt(2));
					System.out.println(playerFailed.getRepresentation());
					ClientRMIInterface failedClient = lServer.clients.get(playerNumber);
					System.out.println("Player Failed "+playerFailed.getRepresentation().charAt(2) + " : " + failedClient);
					try {
						failedClient.mapChanged(lServer.pacmanMap.displayGrid());
						failedClient.playerFailed();
						lServer.players.remove(lServer.players.indexOf(playerFailed));
						System.out.println(lServer.players.size());
						if(lServer.players.size()==1) {
							Player playerWon = lServer.players.get(0);
							int wonPlayerNumber = Character.getNumericValue(playerWon.getRepresentation().charAt(2));
							ClientRMIInterface wonClient = lServer.clients.get(wonPlayerNumber);

							wonClient.mapChanged(lServer.pacmanMap.displayGrid());
							wonClient.playerWon();
							wonClient.stopGame();
							lServer.playerCount = -1;
							lServer.gameStart = false;
						}
					} catch (RemoteException aInE) {
						lServer.clients.remove(playerNumber);
					}
				}
				lServer.notifyPlayers();
        	}
		}
	}


}


import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import pacman.Cell;
import pacman.Monster;
import pacman.PacmanMap;
import pacman.Player;

import java.util.HashMap;

public class GameServer extends UnicastRemoteObject implements ServerRMIInterface {

	private static final long serialVersionUID = 1L;
//	public static Map<String, String> hm = new HashMap<String, String>();
	private List<ClientRMIInterface> clients = new ArrayList<>();
	public static PacmanMap pacmanMap;
	public static Cell startingCell;
	public static Player player1;
	public static Cell monsterCell;
	public static Monster monster;
	public static String userInput = "";
	public static ArrayList<Player> players = new ArrayList<Player>();

	protected GameServer() throws RemoteException {
	}

	public void receiveMove(String name, String value) throws RemoteException {
		userInput = value;
//		hm.put(name, value);
		
//		game(value);

	}

	private void notifyPlayers() {
		for (ClientRMIInterface player : clients) {
			try {
				player.mapChanged(pacmanMap.displayGrid());
			} catch (RemoteException aInE) {
				clients.remove(player);
			}
		}
	}

	public void addPlayerListener(ClientRMIInterface player) throws RemoteException {
		clients.add(player);
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

			startingCell = pacmanMap.getCell(1, 1);
			player1 = new Player(1, startingCell);
			players.add( player1 );

			monsterCell = pacmanMap.getCell(5, 5);
			monster = new Monster(1, monsterCell);

			pacmanMap.addPlayer(player1);
			pacmanMap.addMonster(monster);
			

			while (true) {
				Thread.sleep(2000);
				if(userInput != "") {
					game(userInput);
				}
				lServer.notifyPlayers();
			}

		} catch (Exception aInE) {
			System.out.println("Remote error- " + aInE);
		}

	}

	public static void game(String input) {
		try {
			System.out.println("this is input"+input);
				switch (input) {
					case "w":{
						pacmanMap.movePlayer(player1, "U");
						pacmanMap.moveMonster(monster, players);
						break;
					}case "a":{
						pacmanMap.movePlayer(player1, "L");
						pacmanMap.moveMonster(monster, players);
						break;
					}case "s":{
						pacmanMap.movePlayer(player1, "D");
						pacmanMap.moveMonster(monster, players);
						break;
					}case "d":{
						pacmanMap.movePlayer(player1, "R");
						pacmanMap.moveMonster(monster, players);
						break;
					}
				}
				pacmanMap.displayGrid();
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}

}

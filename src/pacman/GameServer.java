
import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class GameServer extends UnicastRemoteObject implements ServerRMIInterface {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int playerCount=0;
	private static int maxCount=4;
	private static GameServer lServer;
	Map< String,String> hm = new HashMap< String,String>();
	private List<ClientRMIInterface> players = new ArrayList<>();
	protected GameServer() throws RemoteException {
	}

	public void receiveMove(String name, String value) throws RemoteException{
		//		System.out.println(name + " is trying to contact!");
		//        return "Server says hello to " + name;
		hm.put(name, value);
//		System.out.println("Received " + value + " from " + name);

	}

	private void notifyPlayers()
	{
		for (ClientRMIInterface player : players)
		{
			try
			{
				player.mapChanged(hm);
			}
			catch (RemoteException aInE)
			{
				players.remove(player);
			}
		}
	}

	public void addPlayerListener(ClientRMIInterface player) throws RemoteException
	{
		players.add(player);
		playerCount++;
		if (playerCount==1) {
			System.out.println("Host Player has joined");
			player.getGameDetails();
		}
		else {
			System.out.println("Another player has joined");
		}
		if(playerCount==maxCount) {
//			startGame();
			for (ClientRMIInterface splayer : players)
			{
					splayer.startGame();	
			}
		}

	}
	
	public void setMaxCount(int n) throws RemoteException {
		maxCount=n;
	}

//	private void startGame() {
//		try
//		{
//			for (ClientRMIInterface player : players)
//			{
//				player.startGame();	
//			}
//			System.out.println("Game started");
//			while(true) {
//				Thread.sleep(1000);
//				lServer.notifyPlayers();
//			}
//		}
//		catch (Exception aInE)
//		{
//			System.out.println("Remote error- " + aInE);
//		}
//	}

	@Override
	public void removePlayerListener(ClientRMIInterface player) throws RemoteException
	{
		players.remove(player);
	}



	public static void main(String[] args){

		try
		{
			lServer = new GameServer();
			// Binding the remote object (stub) in the registry
			Registry reg = LocateRegistry.createRegistry(52369);
			String url = "rmi://localhost:52369/Hello";

			Naming.rebind(url, lServer);

			//Create the thread and change the temperature
			            while(true) {
			            	Thread.sleep(1000);
			            	if(playerCount==maxCount)
			            		lServer.notifyPlayers();
			            }

		}
		catch (Exception aInE)
		{
			System.out.println("Remote error- " + aInE);
		}

	}

}

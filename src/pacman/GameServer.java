
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
	Map< String,String> hm = new HashMap< String,String>();
	private List<ClientRMIInterface> players = new ArrayList<>();
	protected GameServer() throws RemoteException {
    }

	public void receiveMove(String name, String value) throws RemoteException{
//		System.out.println(name + " is trying to contact!");
//        return "Server says hello to " + name;
		hm.put(name, value);
		
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
	    }

	    @Override
	    public void removePlayerListener(ClientRMIInterface player) throws RemoteException
	    {
	    	players.remove(player);
	    }
	
	
	
	public static void main(String[] args){

		try
        {
			GameServer lServer = new GameServer();
            // Binding the remote object (stub) in the registry
            Registry reg = LocateRegistry.createRegistry(52369);
            String url = "rmi://" + InetAddress.getLocalHost().getHostAddress() + ":52369/Hello";

            Naming.rebind(url, lServer);

            //Create the thread and change the temperature
            while(true) {
            	Thread.sleep(1000);
            	lServer.notifyPlayers();
            }

        }
        catch (Exception aInE)
        {
            System.out.println("Remote error- " + aInE);
        }

    }

}

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface ClientRMIInterface extends Remote
{
    void mapChanged(String[][] response) throws RemoteException;
    void getGameDetails() throws RemoteException;
    void startGame() throws RemoteException;
    void stopGame() throws RemoteException;
	void playerFailed() throws RemoteException;
	void playerWon() throws RemoteException;
	void getStartCorner() throws RemoteException;
}

package main;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientRMIInterface extends Remote
{

    void mapChanged(String[][] response) throws RemoteException;
    void getGameDetails() throws RemoteException;
    void startGame() throws RemoteException;
    void stopGame() throws RemoteException;
	void playerFailed() throws RemoteException;
	void playerWon() throws RemoteException;
	void getMapDetails() throws RemoteException;
	void getStartCorner(boolean[] availableLocation) throws RemoteException;
	int getPlayerNo() throws RemoteException;
	void setPlayerNo(int playerNo) throws RemoteException;
	String getPlayerName() throws RemoteException;
	boolean getPlayerStatus() throws RemoteException;
	void setStartingLocation(int location) throws RemoteException;
	int getStartingLocation() throws RemoteException;
	void updateAvailableLocation(boolean[] availableLocation) throws RemoteException;
}

package pacman;

public class InvalidPlayerPositionException extends Exception{
	
	/*
	 * Exception is thrown when player is assigned an invalid position
	 */
	public InvalidPlayerPositionException(String exception) {
		super(exception);
	}
}

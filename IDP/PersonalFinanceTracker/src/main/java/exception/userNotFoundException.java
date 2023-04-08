package exception;

public class userNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public userNotFoundException(String message) {
		super(message);
		
	}
}

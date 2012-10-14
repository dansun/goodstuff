package nu.danielsundberg.goodstuff.application.exception;

import javax.xml.ws.WebFault;

@WebFault(name = "PlayerNotFound", targetNamespace = "nu.danielsundberg.goodstuff.application.exception")
public class PlayerNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public PlayerNotFoundException(String message) {
		super(message);
	}
	
}

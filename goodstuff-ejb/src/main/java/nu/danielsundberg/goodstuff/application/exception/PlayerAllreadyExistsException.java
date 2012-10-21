package nu.danielsundberg.goodstuff.application.exception;

import javax.xml.ws.WebFault;

/**
 * PlayerAllreadyExists exception and webfault configuration.
 * 
 * @author dansun
 *
 */
@WebFault(name = "PlayerAllreadyExists", targetNamespace = "urn:nu.danielsundberg.goodstuff:exception")
public class PlayerAllreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public PlayerAllreadyExistsException(String message) {
		super(message);
	}
	
}

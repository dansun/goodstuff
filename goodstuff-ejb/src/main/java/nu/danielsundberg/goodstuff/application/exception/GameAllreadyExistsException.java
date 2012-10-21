package nu.danielsundberg.goodstuff.application.exception;

import javax.xml.ws.WebFault;

/**
 * GameAllreadyExists exception and webfault configuration.
 * 
 * @author dansun
 *
 */
@WebFault(name = "GameAllreadyExists", targetNamespace = "urn:nu.danielsundberg.goodstuff:exception")
public class GameAllreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public GameAllreadyExistsException(String message) {
		super(message);
	}
	
}

package nu.danielsundberg.goodstuff.application.exception;

import javax.xml.ws.WebFault;

/**
 * WrongPassword exception and webfault configuration.
 * 
 * @author dansun
 *
 */
@WebFault(name = "WrongPassword", targetNamespace = "urn:nu.danielsundberg.goodstuff:exception")
public class WrongPasswordException extends Exception {

	private static final long serialVersionUID = 1L;

	public WrongPasswordException(String message) {
		super(message);
	}
	
}

package nu.danielsundberg.goodstuff.business.service;

import java.util.Set;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.persistence.EntityManager;

import nu.danielsundberg.goodstuff.access.entity.Game;
import nu.danielsundberg.goodstuff.access.entity.Player;
import nu.danielsundberg.goodstuff.application.exception.PlayerNotFoundException;
import nu.danielsundberg.goodstuff.application.service.impl.GameControllServiceBean;

@Stateless
@WebService(name="GameControllService", targetNamespace="service.business.goodstuff.danielsundberg.nu")
@SOAPBinding(style = javax.jws.soap.SOAPBinding.Style.DOCUMENT)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class GameControllServiceWS extends GameControllServiceBean {

	@Override
	@WebMethod(exclude=true)
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@WebMethod(operationName="getGamesForPlayer")
	public @WebResult(name="Game", 
						partName="Game", 
						targetNamespace="business.goodstuff.danielsundberg.nu") Set<Game> 
		getGamesForPlayer(
				@WebParam(name="PlayerName", 
							partName = "PlayerName", 
							targetNamespace="business.goodstuff.danielsundberg.nu") String playerName) throws PlayerNotFoundException {
		return super.getGamesForPlayer(playerName);
	}
	
	@Override
	@WebMethod(operationName="registerPlayer")
	public @WebResult(name="Player", 
			partName="Player", 
			targetNamespace="business.goodstuff.danielsundberg.nu") Player 
		registerPlayer(
				@WebParam(name="PlayerName", 
					partName = "PlayerName", 
					targetNamespace="business.goodstuff.danielsundberg.nu") String playerName, 
				@WebParam(name="PlayerPassword", 
					partName = "PlayerPassword", 
					targetNamespace="business.goodstuff.danielsundberg.nu") String password) {
		return super.registerPlayer(playerName, password);
	}
	
}

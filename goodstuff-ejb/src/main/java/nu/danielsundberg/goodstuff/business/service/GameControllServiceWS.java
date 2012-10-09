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
import nu.danielsundberg.goodstuff.application.service.impl.GameControllServiceBean;

@Stateless
@WebService
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
	public @WebResult(name="Game") Set<Game> getGamesForPlayer(@WebParam(name="PlayerName") String playerName) {
		return super.getGamesForPlayer(playerName);
	}
	
}

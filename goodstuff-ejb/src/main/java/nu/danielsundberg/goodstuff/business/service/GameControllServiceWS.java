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
import nu.danielsundberg.goodstuff.application.exception.GameAllreadyExistsException;
import nu.danielsundberg.goodstuff.application.exception.PlayerAllreadyExistsException;
import nu.danielsundberg.goodstuff.application.exception.PlayerNotFoundException;
import nu.danielsundberg.goodstuff.application.exception.WrongPasswordException;
import nu.danielsundberg.goodstuff.application.service.impl.GameControllServiceBean;

/**
 * GameControllerService Webservice wrapper
 * 
 * @author dansun
 *
 */
@Stateless
@WebService(name="GameControllService", 
	targetNamespace="urn:nu.danielsundberg.goodstuff:gamecontrollservice")
@SOAPBinding(style = javax.jws.soap.SOAPBinding.Style.DOCUMENT)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class GameControllServiceWS extends GameControllServiceBean {
	
	/**
	 * Excluded from webservice.
	 */
	@Override
	@WebMethod(exclude=true)
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	/**
	 * Define getGamesForPlayer operation.
	 */
	@Override
	@WebMethod(operationName="getGamesForPlayer")
	public @WebResult(name="Game", 
						partName="Game", 
						targetNamespace="urn:nu.danielsundberg.goodstuff:gamecontrollservice:game") Set<Game> 
		getGamesForPlayer(
				@WebParam(name="PlayerId", 
					partName = "PlayerId", 
					targetNamespace="urn:nu.danielsundberg.goodstuff:gamecontrollservice:player") Long playerId, 
				@WebParam(name="PlayerPassword", 
					partName = "PlayerPassword", 
					targetNamespace="urn:nu.danielsundberg.goodstuff:gamecontrollservice:player") String password) throws PlayerNotFoundException, WrongPasswordException {
		return super.getGamesForPlayer(playerId, password);
	}
	
	/**
	 * Define registerPlayer operation.
	 */
	@Override
	@WebMethod(operationName="registerPlayer")
	public @WebResult(name="Player", 
			partName="Player", 
			targetNamespace="urn:nu.danielsundberg.goodstuff:gamecontrollservice:player") Player 
		registerPlayer(
				@WebParam(name="PlayerName", 
					partName = "PlayerName", 
					targetNamespace="urn:nu.danielsundberg.goodstuff:gamecontrollservice:player") String playerName, 
				@WebParam(name="PlayerPassword", 
					partName = "PlayerPassword", 
					targetNamespace="urn:nu.danielsundberg.goodstuff:gamecontrollservice:player") String password) throws PlayerAllreadyExistsException {
		return super.registerPlayer(playerName, password);
	}
	
	/**
	 * Define createGame operation.
	 */
	@Override
	@WebMethod(operationName="createGame")
	public @WebResult(name="Game", 
			partName="Game", 
			targetNamespace="urn:nu.danielsundberg.goodstuff:gamecontrollservice:game") Game 
		createGame(
				@WebParam(name="PlayerId", 
					partName = "PlayerId", 
					targetNamespace="urn:nu.danielsundberg.goodstuff:gamecontrollservice:player") Long playerId, 
				@WebParam(name="PlayerPassword", 
					partName = "PlayerPassword", 
					targetNamespace="urn:nu.danielsundberg.goodstuff:gamecontrollservice:player") String password, 
				@WebParam(name="GameName", 
					partName = "GameName", 
					targetNamespace="urn:nu.danielsundberg.goodstuff:gamecontrollservice:game") String gameName) throws GameAllreadyExistsException, PlayerNotFoundException, WrongPasswordException {
		return super.createGame(playerId, password, gameName);
	}
	
	/**
	 * Define acceptGame operation.
	 */
	@Override
	@WebMethod(operationName="acceptGame")
	public void acceptGame(
				@WebParam(name="PlayerId", 
					partName = "PlayerId", 
					targetNamespace="urn:nu.danielsundberg.goodstuff:gamecontrollservice:player") Long playerId, 
				@WebParam(name="PlayerPassword", 
					partName = "PlayerPassword", 
					targetNamespace="urn:nu.danielsundberg.goodstuff:gamecontrollservice:player") String password, 
				@WebParam(name="GameId", 
					partName = "GameId", 
					targetNamespace="urn:nu.danielsundberg.goodstuff:gamecontrollservice:game") Long gameId) {
		super.acceptGame(playerId, password, gameId);
	}
	
	/**
	 * Define invitePlayers operation.
	 */
	@Override
	@WebMethod(operationName="invitePlayers")
	public void invitePlayers(@WebParam(name="PlayerId", 
			partName = "PlayerId", 
			targetNamespace="urn:nu.danielsundberg.goodstuff:gamecontrollservice:player") Long playerId, 
		@WebParam(name="PlayerPassword", 
			partName = "PlayerPassword", 
			targetNamespace="urn:nu.danielsundberg.goodstuff:gamecontrollservice:player") String password, 
		@WebParam(name="GameId", 
			partName = "GameId", 
			targetNamespace="urn:nu.danielsundberg.goodstuff:gamecontrollservice:game") Long gameId,	
		@WebParam(name="Player", 
			partName="Player", 
			targetNamespace="urn:nu.danielsundberg.goodstuff:gamecontrollservice:player") Set<Player> playersToInvite) {
		super.invitePlayers(playerId, password, gameId, playersToInvite);
	}
	
}

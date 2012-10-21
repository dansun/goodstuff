package nu.danielsundberg.goodstuff.application.service;

import java.util.Set;

import nu.danielsundberg.goodstuff.access.entity.Game;
import nu.danielsundberg.goodstuff.access.entity.Player;
import nu.danielsundberg.goodstuff.application.exception.GameAllreadyExistsException;
import nu.danielsundberg.goodstuff.application.exception.PlayerAllreadyExistsException;
import nu.danielsundberg.goodstuff.application.exception.PlayerNotFoundException;
import nu.danielsundberg.goodstuff.application.exception.WrongPasswordException;

/**
 * GameControllService interface
 * 
 * @author dansun
 *
 */
public interface GameControllService {
	
	/**
	 * Gets games for given player ID.
	 * @param playerId
	 * @param password
	 * @return Set<Game>
	 * @throws PlayerNotFoundException
	 * @throws WrongPasswordException
	 */
	public Set<Game> getGamesForPlayer(Long playerId, String password) throws PlayerNotFoundException, WrongPasswordException;
	
	/**
	 * Registers a new player.
	 * @param playerName
	 * @param password
	 * @return Player
	 * @throws PlayerAllreadyExistsException
	 */
	public Player registerPlayer(String playerName, String password) throws PlayerAllreadyExistsException;
	
	/**
	 * Creates a new game, registering player will automatically be added to the game.
	 * @param playerId
	 * @param password
	 * @param gameName
	 * @return Game
	 * @throws GameAllreadyExistsException
	 * @throws PlayerNotFoundException
	 * @throws WrongPasswordException
	 */
	public Game createGame(Long playerId, String password, String gameName) throws GameAllreadyExistsException, PlayerNotFoundException, WrongPasswordException;
	
	/**
	 * Invite players to created game.
	 * @param playerId
	 * @param password
	 * @param gameId
	 * @param playersToInvite
	 */
	public void invitePlayers(Long playerId, String password, Long gameId, Set<Player> playersToInvite);
	
	/**
	 * Accept invitation to game.
	 * @param playerId
	 * @param password
	 * @param gameId
	 */
	public void acceptGame(Long playerId, String password, Long gameId);

}

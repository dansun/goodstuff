package nu.danielsundberg.goodstuff.application.service;

import java.util.Set;

import nu.danielsundberg.goodstuff.access.entity.Game;
import nu.danielsundberg.goodstuff.access.entity.Player;
import nu.danielsundberg.goodstuff.application.exception.PlayerNotFoundException;

public interface GameControllService {
	
	public Set<Game> getGamesForPlayer(String playerName) throws PlayerNotFoundException;
	
	public Player registerPlayer(String playerName, String password);
	
	public Game createGame(Integer playerId, String gameName);
	
	public void invitePlayers(Integer playerId, String password, Set<Player> playersToInvite);
	
	public void acceptGame(Integer playerId, String password, Integer gameId);

}

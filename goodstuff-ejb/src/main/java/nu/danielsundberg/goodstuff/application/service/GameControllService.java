package nu.danielsundberg.goodstuff.application.service;

import java.util.Set;

import nu.danielsundberg.goodstuff.access.entity.Game;

public interface GameControllService {
	
	public Set<Game> getGamesForPlayer(String playerId);

}

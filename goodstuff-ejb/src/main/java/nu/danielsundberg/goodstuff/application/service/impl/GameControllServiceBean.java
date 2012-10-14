package nu.danielsundberg.goodstuff.application.service.impl;

import java.util.HashSet;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import nu.danielsundberg.goodstuff.access.entity.Game;
import nu.danielsundberg.goodstuff.access.entity.Gameplayer;
import nu.danielsundberg.goodstuff.access.entity.Player;
import nu.danielsundberg.goodstuff.application.exception.PlayerNotFoundException;
import nu.danielsundberg.goodstuff.application.service.GameControllService;

@Stateless
public class GameControllServiceBean implements GameControllService {

	@PersistenceContext(unitName = "goodstuffPersitenceUnit")
    private EntityManager entityManager;
    
	public void setEntityManager(final EntityManager entityManager) {
            this.entityManager = entityManager;
    }

	@Override
	public Set<Game> getGamesForPlayer(String playerName) throws PlayerNotFoundException {
		Query playerQuery = entityManager.createNamedQuery("player.findByPlayerName");
		playerQuery.setParameter("playerName", playerName);
		Player player = null;
		
		try {
			player = (Player) playerQuery.getSingleResult();	
		} catch (NoResultException e) {
			throw new PlayerNotFoundException("No player with playername '"+playerName+"' could be found.");
		}
		
		Set<Game> games = new HashSet<Game>();
		for(Gameplayer gp : player.getGames()) {
			games.add(gp.getGame());
		}
		return games;
	}

	@Override
	public Player registerPlayer(String playerName, String password) {
		Player newPlayer = new Player();
		newPlayer.setPlayerName(playerName);
		newPlayer.setPassword(password);
		entityManager.persist(newPlayer);
		return newPlayer;
	}

	@Override
	public Game createGame(Integer playerId, String gameName) {
		// TODO Implement creation of game.
		return null;
	}

	@Override
	public void invitePlayers(Integer playerId, String password, Set<Player> playersToInvite) {
		// TODO Implement invitation of players.
		
	}

	@Override
	public void acceptGame(Integer playerId, String password, Integer gameId) {
		// TODO Implement acceptance of game invite.
		
	}
	
}

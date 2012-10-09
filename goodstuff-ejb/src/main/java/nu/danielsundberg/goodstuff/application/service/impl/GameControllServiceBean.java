package nu.danielsundberg.goodstuff.application.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import nu.danielsundberg.goodstuff.access.entity.Game;
import nu.danielsundberg.goodstuff.access.entity.Gameplayer;
import nu.danielsundberg.goodstuff.access.entity.Player;
import nu.danielsundberg.goodstuff.application.service.GameControllService;

@Stateless
public class GameControllServiceBean implements GameControllService {

	@PersistenceContext(unitName = "goodstuffPersitenceUnit")
    private EntityManager entityManager;
    
	public void setEntityManager(final EntityManager entityManager) {
            this.entityManager = entityManager;
    }

	@Override
	public Set<Game> getGamesForPlayer(String playerName) {
		Query playerQuery = entityManager.createNamedQuery("player.findByPlayerName");
		playerQuery.setParameter("playerName", playerName);
		Player player = (Player) playerQuery.getSingleResult();
		
		Query gameplayerQuery = entityManager.createNamedQuery("gameplayer.findByPlayerId");
		gameplayerQuery.setParameter("playerId", player.getPlayerId());
		@SuppressWarnings("unchecked")
		Set<Gameplayer> gamesPlayerIsPlaying = new HashSet<Gameplayer>((List<Gameplayer>) gameplayerQuery.getResultList());
		
		Set<Game> games = new HashSet<Game>();
		for(Gameplayer gp : gamesPlayerIsPlaying) {
			games.add(entityManager.find(Game.class, gp.getGameId()));
		}
		return games;
	}
	
}

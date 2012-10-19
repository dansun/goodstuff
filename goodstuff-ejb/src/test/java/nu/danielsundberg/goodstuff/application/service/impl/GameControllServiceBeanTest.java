package nu.danielsundberg.goodstuff.application.service.impl;

import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import nu.danielsundberg.goodstuff.access.entity.Game;
import nu.danielsundberg.goodstuff.access.entity.Gameplayer;
import nu.danielsundberg.goodstuff.access.entity.Player;
import nu.danielsundberg.goodstuff.test.GoodstuffTestCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class GameControllServiceBeanTest extends GoodstuffTestCase {

	private GameControllServiceBean service = new GameControllServiceBean();
	private Set<Gameplayer> games;
	@Mock EntityManager entityManager;
	@Mock Query query;
	@Mock Player player;
	@Mock Gameplayer gameplayer;
	@Mock Game game;
	
	@Before
	public void setUp() throws Exception {
		service.setEntityManager(entityManager);
		games = new HashSet<Gameplayer>();
		games.add(gameplayer);
		when(entityManager.createNamedQuery("player.findByPlayerName")).thenReturn(query);
		when(query.getSingleResult()).thenReturn(player);
		when(player.getGames()).thenReturn(games);
		when(gameplayer.getGame()).thenReturn(game);
		when(gameplayer.getPlayer()).thenReturn(player);
	}
	
	@Test
	public void testGetGamesForPlayer() throws Exception {
		Set<Game> games = service.getGamesForPlayer(PLAYER_NAME);
		assert(games.contains(game));
	}
	
}
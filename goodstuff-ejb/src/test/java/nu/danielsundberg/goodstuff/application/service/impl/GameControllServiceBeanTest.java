package nu.danielsundberg.goodstuff.application.service.impl;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import nu.danielsundberg.goodstuff.access.entity.Game;
import nu.danielsundberg.goodstuff.access.entity.Gameplayer;
import nu.danielsundberg.goodstuff.access.entity.Player;
import nu.danielsundberg.goodstuff.application.exception.PlayerNotFoundException;
import nu.danielsundberg.goodstuff.application.exception.WrongPasswordException;
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
		when(entityManager.find(Player.class, PLAYER_ID)).thenReturn(player);
		when(entityManager.createNamedQuery("player.findByPlayerName")).thenReturn(query);
		when(query.getSingleResult()).thenReturn(player);
		when(game.getGameName()).thenReturn(GAME_NAME);
		when(game.getGameId()).thenReturn(GAME_ID);
		when(player.getPassword()).thenReturn(PLAYER_PASSWORD);
		when(player.getPlayerName()).thenReturn(PLAYER_NAME);
		when(player.getGames()).thenReturn(games);
		when(gameplayer.getGame()).thenReturn(game);
		when(gameplayer.getPlayer()).thenReturn(player);
		when(gameplayer.getGameId()).thenReturn(GAME_ID);
		when(gameplayer.getPlayerId()).thenReturn(PLAYER_ID);
	}
	
	@Test
	public void testGetGamesForPlayer() throws Exception {
		Set<Game> games = service.getGamesForPlayer(PLAYER_ID, PLAYER_PASSWORD);
		assert(games.contains(game));
	}
	
	@Test(expected = PlayerNotFoundException.class)
	public void testGetGamesForNonExistingPlayer() throws Exception {
		when(query.getSingleResult()).thenThrow(new NoResultException());
		service.getGamesForPlayer(NON_EXISTING_PLAYER_ID, PLAYER_PASSWORD);
		verify(entityManager).find(Player.class, NON_EXISTING_PLAYER_ID);
	}
	
	@Test(expected = WrongPasswordException.class)
	public void testGetGamesForPlayerWrongPassword() throws Exception {
		service.getGamesForPlayer(PLAYER_ID, WRONG_PASSWORD);
		verify(entityManager).find(Player.class, PLAYER_ID);
	}
	
}
package nu.danielsundberg.goodstuff.access.entity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import nu.danielsundberg.goodstuff.test.GoodstuffJpaTestCase;

import org.junit.Test;

public class PlayerTest extends GoodstuffJpaTestCase {

	@Test
	public void testPersistPlayer() {
		Player Player = new Player();
		Player.setPlayerName(PLAYER_NAME);
		entityManager.persist(Player);
		entityManager.flush();
	}
	
	@Test
	public void testFindPlayer() {
		Player Player = new Player();
		Player.setPlayerName(PLAYER_NAME);
		entityManager.persist(Player);
		entityManager.flush();
		Player persistedPlayer = entityManager.find(Player.class, Player.getPlayerId());
		assertThat(persistedPlayer.getPlayerName(), is(equalTo(Player.getPlayerName())));
	}
	
	@Test
	public void testRemovePlayer() {
		Player Player = new Player();
		Player.setPlayerName(PLAYER_NAME);
		entityManager.persist(Player);
		entityManager.flush();
		long id = Player.getPlayerId();
		entityManager.remove(Player);
		entityManager.flush();
		Player deletedPlayer = entityManager.find(Player.class, id);
		assertThat(deletedPlayer, is(equalTo(null)));
	}
	
	
}

package nu.danielsundberg.goodstuff.access.entity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import nu.danielsundberg.goodstuff.access.entity.Game.GameState;
import nu.danielsundberg.goodstuff.test.GoodstuffJpaTestCase;

import org.joda.time.DateTime;
import org.junit.Test;
import org.powermock.core.classloader.annotations.PowerMockIgnore;

@PowerMockIgnore(value={"Persistence.class"})
public class GameTest extends GoodstuffJpaTestCase {

	@Test
	public void testPersistGame() {
		Game game = new Game();
		game.setGameName(GAME_NAME);
		entityManager.persist(game);
		entityManager.flush();
	}
	
	@Test
	public void testFindGame() {
		Game game = new Game();
		game.setGameName(GAME_NAME);
		entityManager.persist(game);
		entityManager.flush();
		Game persistedGame = entityManager.find(Game.class, game.getGameId());
		assertThat(persistedGame.getGameName(), is(equalTo(game.getGameName())));
	}
	
	@Test
	public void testRegistrationTimeGeneration() {
		Game game = new Game();
		game.setGameName(GAME_NAME);
		entityManager.persist(game);
		entityManager.flush();
		Game persistedGame = entityManager.find(Game.class, game.getGameId());
		assertThat(persistedGame.getRegistrationTime(), is(notNullValue()));
	}
	
	@Test
	public void testRemoveGame() {
		Game game = new Game();
		game.setGameName(GAME_NAME);
		entityManager.persist(game);
		entityManager.flush();
		long id = game.getGameId();
		entityManager.remove(game);
		entityManager.flush();
		Game deletedGame = entityManager.find(Game.class, id);
		assertThat(deletedGame, is(equalTo(null)));
	}
	
	@Test
	public void testCreatedGameState() {
		Game game = new Game();
		assertThat(game.getGameState(), is(equalTo(GameState.CREATED)));
	}
	
	@Test
	public void testRunningGameState() {
		Game game = new Game();
		game.setStartingTime(new DateTime());
		assertThat(game.getGameState(), is(equalTo(GameState.RUNNING)));
	}
	
	@Test
	public void testFinishedGameState() {
		Game game = new Game();
		DateTime dateTime = new DateTime();
		game.setStartingTime(dateTime);
		game.setFinishingTime(dateTime.plusMillis(1));
		assertThat(game.getGameState(), is(equalTo(GameState.FINISHED)));
	}
	
}

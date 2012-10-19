package nu.danielsundberg.goodstuff.access.entity;
import nu.danielsundberg.goodstuff.test.GoodstuffJpaTestCase;

import org.junit.Test;

public class GameplayerTest extends GoodstuffJpaTestCase {

	@Test
	public void testConnectingPlayerWithGame() throws Exception {
		Player player = new Player();
		player.setPlayerName(PLAYER_NAME);
		
		Game game = new Game();
		game.setGameName(GAME_NAME);
		
		Gameplayer gameplayer = new Gameplayer();
		gameplayer.setGame(game);
		gameplayer.setPlayer(player);
		
		entityManager.persist(player);
	}
	
}

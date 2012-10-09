package nu.danielsundberg.goodstuff.access.entity;

public class GameplayerId {

	private long gameId;
	private long playerId;
	
	public GameplayerId(long gameId, long playerId) {
		this.setGameId(gameId);
		this.setPlayerId(playerId);
	}

	public void setGameId(long gameId) {
		this.gameId = gameId;
	}

	public long getGameId() {
		return gameId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public long getPlayerId() {
		return playerId;
	}
	
	
}

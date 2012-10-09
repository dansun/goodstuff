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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (gameId ^ (gameId >>> 32));
		result = prime * result + (int) (playerId ^ (playerId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameplayerId other = (GameplayerId) obj;
		if (gameId != other.gameId)
			return false;
		if (playerId != other.playerId)
			return false;
		return true;
	}
	
}

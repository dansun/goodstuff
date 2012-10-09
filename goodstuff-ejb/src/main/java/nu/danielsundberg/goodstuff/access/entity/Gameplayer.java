package nu.danielsundberg.goodstuff.access.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="GAMEPLAYER")
@IdClass(GameplayerId.class)
@NamedQueries({
        @NamedQuery(name = "gameplayer.findByPlayerId", query = "SELECT gp FROM Gameplayer AS gp WHERE gp.playerId = :playerId"),
        @NamedQuery(name = "gameplayer.findByGameId", query = "SELECT gp FROM Gameplayer AS gp WHERE gp.gameId = :gameId")
})
public class Gameplayer {

	@Id
	@Column
	private long gameId;
	
	@Id
	@Column
	private long playerId;

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

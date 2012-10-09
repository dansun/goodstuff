package nu.danielsundberg.goodstuff.access.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Gameplayer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable=false)
	private long gameId;
	
	@Id
	@Column(nullable=false)
	private long playerId;
	
	@ManyToOne
	@JoinColumn(name="gameId", insertable=false, updatable=false)
	private Game game;
	
	@ManyToOne
	@JoinColumn(name="playerId", insertable=false, updatable=false)
	private Player player;

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

	public void setGame(Game game) {
		this.game = game;
	}

	public Game getGame() {
		return game;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Player getPlayer() {
		return player;
	}
	
}

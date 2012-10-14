package nu.danielsundberg.goodstuff.access.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PLAYER")
@NamedQueries({
        @NamedQuery(name = "player.findByPlayerName", query = "SELECT p FROM Player AS p WHERE p.playerName = :playerName")
})
public class Player implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "PLAYER_SEQUENCE")
    @SequenceGenerator(name = "PLAYER_SEQUENCE", sequenceName = "PLAYER_SEQUENCE")
	@Column(name="PLAYERID")
    private long playerId;
	
	@Column(name="PLAYERNAME")
	private String playerName;
	
	@Column(name="PASSWORD")
	private String password;
	
	@OneToMany(mappedBy="playerId")
	private Set<Gameplayer> games;
	

	public void setGames(Set<Gameplayer> games) {
		this.games = games;
	}

	public Set<Gameplayer> getGames() {
		return games;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getPlayerName() {
		return playerName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}

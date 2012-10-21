package nu.danielsundberg.goodstuff.access.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Player entity for persistence and propagation.
 *
 * @author dansun
 *
 */
@Entity
@Table(name = "PLAYER")
@NamedQueries({
        @NamedQuery(name = "player.findByPlayerName", query = "SELECT p FROM Player AS p WHERE p.playerName = :playerName")
})
@XmlAccessorType(XmlAccessType.NONE)
public class Player implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "PLAYER_SEQUENCE")
    @SequenceGenerator(name = "PLAYER_SEQUENCE", sequenceName = "PLAYER_SEQUENCE")
	@Column(name="PLAYERID")
	@XmlAttribute(name="playerId", required=true)
	private long playerId;
	
	@Column(name="PLAYERNAME", unique=true)
	@XmlAttribute(name="playerName", required=true)
	private String playerName;
	
	@Column(name="PASSWORD")
	private String password;
	
	@OneToMany(mappedBy="playerId", cascade=CascadeType.ALL)
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

package nu.danielsundberg.goodstuff.access.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import org.joda.time.DateTime;

/**
 * Game entity for persistence and propagation.
 *
 * @author dansun
 *
 */
@Entity
@Table(name = "GAME")
@NamedQueries({
    @NamedQuery(name = "game.findByGameName", query = "SELECT g FROM Game AS g WHERE g.gameName = :gameName")
})
@XmlAccessorType(XmlAccessType.NONE)
public class Game implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "GAME_SEQUENCE")
    @SequenceGenerator(name = "GAME_SEQUENCE", sequenceName = "GAME_SEQUENCE")
	@Column(name="GAMEID")
	@XmlAttribute(name="gameId", required=true)
    private Long gameId;
	
	@Column(unique=true)
	@XmlAttribute(name="gameName", required=true)
	private String gameName;
	
	@Column(name="CREATING_PLAYER_ID")
	private Long creatingPlayerId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="REGISTRATION_TIME")
	private Date registrationTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="STARTING_TIME")
	private Date startingTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FINISHING_TIME")
	private Date finishingTime;
	
	@OneToMany(mappedBy="gameId", cascade=CascadeType.ALL)
	private Set<Gameplayer> players;

	public void setPlayers(Set<Gameplayer> players) {
		this.players = players;
	}

	public Set<Gameplayer> getPlayers() {
		return players;
	}

	public void setGameId(long gameId) {
		this.gameId = gameId;
	}

	public long getGameId() {
		return gameId;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getGameName() {
		return gameName;
	}
	
	@XmlAttribute(name="gameState", required=true)
	public GameState getGameState() {
		if(this.startingTime!=null) {
			if(this.finishingTime!=null) {
				return GameState.FINISHED;	
			} else {
				return GameState.RUNNING;
			}
		} else {
			return GameState.CREATED;
		}
	}

	public DateTime getRegistrationTime() {
		return registrationTime!=null?new DateTime(registrationTime):null;
	}

	public DateTime getFinishingTime() {
		return finishingTime!=null?new DateTime(finishingTime):null;
	}

	public void setFinishingTime(DateTime finishingTime) {
		this.finishingTime = finishingTime.toDate();
	}

	public DateTime getStartingTime() {
		return startingTime!=null?new DateTime(startingTime):null;
	}

	public void setStartingTime(DateTime startingTime) {
		this.startingTime = startingTime.toDate();
	}

	public Long getCreatingPlayerId() {
		return creatingPlayerId;
	}

	public void setCreatingPlayerId(Long creatingPlayerId) {
		this.creatingPlayerId = creatingPlayerId;
	}
	
	@PrePersist
	@SuppressWarnings("unused")
	private void onCreate() {
		this.registrationTime = new DateTime().toDate();
	}

	public enum GameState {
		CREATED,
		RUNNING,
		FINISHED
	}
	
}

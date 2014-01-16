package com.djr.cards.entities.golf;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: djr4488
 * Date: 1/4/14
 * Time: 4:40 PM
 */
@Entity
@Table(name="golf_cards")
public class GolfCard {
    @Id
    private Integer id;
    private Integer position;
    private Long playerId;
    private Long golfId;
    private Boolean faceUp;
    private Integer points;
    private Integer cardId;

    public Integer getId() {
        return id;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getGolfId() {
        return golfId;
    }

    public void setGolfId(Long golfId) {
        this.golfId = golfId;
    }

    public Boolean getFaceUp() {
        return faceUp;
    }

    public void setFaceUp(Boolean faceUp) {
        this.faceUp = faceUp;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }
}

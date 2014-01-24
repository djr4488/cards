package com.djr.cards.data.entities.golf;

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
@Table(name="golf_players")
public class GolfPlayer {
    @Id
    private Long id;
    private String userId;
}

package com.djr.cards.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: djr4488
 * Date: 1/4/14
 * Time: 4:41 PM
 */
@Entity
@Table(name="cards")
public class Card {
    @Id
    public Long id;
    public String fileName;
    public Boolean isBacking;

    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Boolean getBacking() {
        return isBacking;
    }

    public void setBacking(Boolean backing) {
        isBacking = backing;
    }
}

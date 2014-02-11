package com.djr.cards.games.selector.model;

import java.util.List;

/**
 * User: djr4488
 * Date: 2/10/14
 * Time: 6:46 PM
 */
public class SelectorModel {
    private List<String> gameTypes;
    private String selectedGameType;
    private Integer selectedOption;

    public List<String> getGameTypes() {
        return gameTypes;
    }

    public void setGameTypes(List<String> gameTypes) {
        this.gameTypes = gameTypes;
    }

    public String getSelectedGameType() {
        return selectedGameType;
    }

    public void setSelectedGameType(String selectedGameType) {
        this.selectedGameType = selectedGameType;
    }

    public Integer getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(Integer selectedOption) {
        this.selectedOption = selectedOption;
    }
}

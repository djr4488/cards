package com.djr.cards.games.golf.actions;

import com.djr.cards.BaseAction;
import org.slf4j.Logger;

import javax.inject.Inject;

/**
 * User: djr4488
 * Date: 2/18/14
 * Time: 8:09 PM
 */
public class GolfAction extends BaseAction {
    @Inject
    private Logger logger;
    public String execute() {
        logger.info("execute() - not implemented");
        removeAndSetSessionAttribute("msgbold", "error.unimplemented.bold");
        removeAndSetSessionAttribute("msgtext", "error.unimplemented.text");
        return "error";
    }
}

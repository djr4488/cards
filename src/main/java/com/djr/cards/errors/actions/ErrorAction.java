package com.djr.cards.errors.actions;

import com.djr.cards.BaseAction;
import com.djr.cards.audit.AuditService;
import org.slf4j.Logger;
import javax.inject.Inject;
import java.util.Calendar;

/**
 * User: djr4488
 * Date: 2/11/14
 * Time: 8:41 PM
 */
public class ErrorAction extends BaseAction  {
    @Inject
    private Logger logger;
    @Inject
    private AuditService auditSvc;

    public String execute() {
        logger.info("execute() - tracking:{}, msgbold:{}, msgtext:{}", getSessionAttribute("tracking"),
                getSessionAttribute("msgbold"), getSessionAttribute("msgtext"));
        auditSvc.writeAudit(auditSvc.getAuditLog(getSessionAttribute("tracking"), getSessionAttribute("msgbold"),
                getIp(), Calendar.getInstance()));
        String msgBoldKey = (String)getAndRemoveSessionAttribute("msgbold");
        String msgTextKey = (String)getAndRemoveSessionAttribute("msgtext");
        if (msgBoldKey == null) {
            msgBoldKey = "error.general.bold";
            msgTextKey = "error.general.text";
        }
        getRequest().setAttribute("msgbold", getText(msgBoldKey));
        getRequest().setAttribute("msgtext", getText(msgTextKey));
        return "success";
    }
}

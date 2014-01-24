package com.djr.cards.audit;

import com.djr.cards.data.entities.AuditLog;

import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: djr4488
 * Date: 1/12/14
 * Time: 9:28 PM
 */
public interface AuditService {
    public void writeAudit(AuditLog auditLog);
    public AuditLog getAuditLog(String who, String what, String info, Calendar timestamp);
}

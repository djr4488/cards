package com.djr.cards.audit;

import com.djr.cards.entities.AuditLog;

import javax.annotation.Resource;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: djr4488
 * Date: 1/12/14
 * Time: 9:37 PM
 */
public class AuditServiceImpl implements AuditService {
    @Inject
    private org.slf4j.Logger logger;
    @PersistenceContext
    private EntityManager em;
    @Resource
    private UserTransaction userTx;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void writeAudit(AuditLog auditLog) {
        logger.debug("writeAudit() auditLog:{}", auditLog);
        try {
            userTx.begin();
            em.persist(auditLog);
            userTx.commit();
        } catch (Exception ex) {
            logger.error("writeAudit() ex:{}", ex);
        }
    }

    @Override
    public AuditLog getAuditLog(String who, String what, String info, Calendar timestamp) {
        logger.debug("getAuditLog() who:{}, what:{}, info:{}, timestamp:{}", who, what, info, timestamp);
        AuditLog auditLog = new AuditLog();
        auditLog.who=who;
        auditLog.what=what;
        auditLog.info=info;
        auditLog.time=timestamp;
        return auditLog;
    }
}

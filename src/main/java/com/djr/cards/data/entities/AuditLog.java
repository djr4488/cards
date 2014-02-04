package com.djr.cards.data.entities;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: djr4488
 * Date: 1/4/14
 * Time: 4:55 PM
 */
@Entity
@Table(name="audit_log")
public class AuditLog {
    @Id
    @Column(name="id", nullable=false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name="who")
    public String who;
    @Column(name = "what")
    public String what;
    @Column(name="data_info")
    public String info;
    @Column(name="date_time")
    @Temporal(TemporalType.TIMESTAMP)
    public Calendar time;

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuditLog auditLog = (AuditLog) o;

        if (id != null ? !id.equals(auditLog.id) : auditLog.id != null) return false;
        if (info != null ? !info.equals(auditLog.info) : auditLog.info != null) return false;
        if (time != null ? !time.equals(auditLog.time) : auditLog.time != null) return false;
        if (what != null ? !what.equals(auditLog.what) : auditLog.what != null) return false;
        if (who != null ? !who.equals(auditLog.who) : auditLog.who != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (who != null ? who.hashCode() : 0);
        result = 31 * result + (what != null ? what.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AuditLog{" +
                "id=" + id +
                ", who='" + who + '\'' +
                ", what='" + what + '\'' +
                ", info='" + info + '\'' +
                ", time=" + time +
                '}';
    }
}

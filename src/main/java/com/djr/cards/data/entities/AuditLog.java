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

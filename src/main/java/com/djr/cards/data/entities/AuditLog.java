package com.djr.cards.data.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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
        return EqualsBuilder.reflectionEquals(this, o, null);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, null);
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.DEFAULT_STYLE);
    }
}

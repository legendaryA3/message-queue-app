package com.aprisoft.message.queue.app.entities;

import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.*;
import com.aprisoft.message.queue.app.entities.enumerations.ClientStatus;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "STATUS_HISTORY")
@Data
@EqualsAndHashCode(callSuper = true)
@SQLDelete(sql = "update status_history set archived = true, last_modified = current_timestamp where id = ?", check = ResultCheckStyle.COUNT)
@TypeDef(
        name = "jsonb",
        typeClass = JsonBinaryType.class
)
@Where(clause = "archived = false")
@ToString(of = {"patient", "status", "dateStatus"})
public class StatusHistory extends TransactionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @JoinColumn(name = "PATIENT_ID")
    @ManyToOne
    private Patient patient;

    @Enumerated(EnumType.STRING)
    private ClientStatus status;

    @Column(name = "DATE_STATUS")
    private LocalDate dateStatus;

    @Enumerated(EnumType.STRING)
    private ClientStatus outcome;

    @Column(name = "AGREED_DATE")
    private LocalDate agreedDate;

    @Size(max = 100)
    @Column(name = "REASON_FOR_INTERRUPTION")
    private String reasonForInterruption;

    @Column(name = "DATE_TRACKED")
    private LocalDate dateTracked;

    @Size(max = 100)
    @Column(name = "CAUSE_OF_DEATH")
    private String causeOfDeath;

    private Boolean auto = false;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private JsonNode extra;
}

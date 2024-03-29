package com.aprisoft.message.queue.app.entities;

import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.*;
import com.aprisoft.message.queue.app.entities.enumerations.DmocType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "DEVOLVE")
@SQLDelete(sql = "update devolve set archived = true, last_modified = current_timestamp where id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "archived = false")
@Data
@TypeDef(
        name = "jsonb",
        typeClass = JsonBinaryType.class
)
public class Devolve extends TransactionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @JoinColumn(name = "PATIENT_ID")
    @ManyToOne
    private Patient patient;

    @JoinColumn(name = "ddd_outlet_id")
    @ManyToOne
    private DDDOutlet dddOutlet;

    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE_DEVOLVED")
    private LocalDate dateDevolved;

    @Column(name = "DMOC_TYPE")
    @NotNull
    @Enumerated(EnumType.STRING)
    private DmocType dmocType;

    @JoinColumn(name = "related_viral_load_id")
    @ManyToOne
    private Laboratory relatedViralLoad;

    @JoinColumn(name = "related_cd4_id")
    @ManyToOne
    private Laboratory relatedCd4;

    @ManyToOne
    private Clinic relatedClinic;

    @ManyToOne
    private Pharmacy relatedPharmacy;

    @Column(name = "DATE_NEXT_CLINIC")
    private LocalDate dateNextClinic;

    @Column(name = "DATE_NEXT_REFILL")
    private LocalDate dateNextRefill;

    @Column(name = "NOTES")
    private String notes;

    @Column(name = "DATE_DISCONTINUED")
    private LocalDate dateDiscontinued;

    @Column(name = "REASON_DISCONTINUED")
    private String reasonDiscontinued;

    private LocalDate dateReturnedToFacility;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private JsonNode extra;
}

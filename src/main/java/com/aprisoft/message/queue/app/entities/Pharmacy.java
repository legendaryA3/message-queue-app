package com.aprisoft.message.queue.app.entities;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.Mapping;
import com.blazebit.persistence.view.MappingSingular;
import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PHARMACY")
@Data
@SQLDelete(sql = "update pharmacy set archived = true, last_modified = current_timestamp where id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "archived = false")
@TypeDef(
        name = "jsonb",
        typeClass = JsonBinaryType.class
)
@ToString(of = "dateVisit", callSuper = true)
@EqualsAndHashCode(of = {"patient", "dateVisit"}, callSuper = true)
public class Pharmacy extends TransactionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @JoinColumn(name = "PATIENT_ID")
    @ManyToOne
    private Patient patient;

    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE_VISIT")
    private LocalDate dateVisit;

    @Column(name = "ADR_SCREENED")
    private Boolean adrScreened;

    @Column(name = "PRESCRIPTION_ERROR")
    private Boolean prescriptionError;

    @Column(name = "ADHERENCE")
    private Boolean adherence;

    @Column(name = "MMD_TYPE")
    private String mmdType;

    @Column(name = "NEXT_APPOINTMENT")
    private LocalDate nextAppointment;

    @Column(name = "sync_status")
    private String syncStatus;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private JsonNode extra;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private Set<PharmacyLine> lines = new HashSet<>();

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private Set<PharmacyAdverseDrugReaction> adverseDrugReactions = new HashSet<>();

    @EntityView(Pharmacy.class)
    public interface InitialPharmacyView {
        LocalDate getDateVisit();

        Boolean getAdrScreened();

        Boolean getPrescriptionError();

        Boolean getAdherence();

        String getMmdType();

        LocalDate getNextAppointment();

        JsonNode getExtra();

        @MappingSingular
        Set<PharmacyLine> getLines();

        @MappingSingular
        Set<PharmacyAdverseDrugReaction> getAdverseDrugReactions();

        @Mapping("archived")
        Boolean getArchived();

        @Mapping("lastModified")
        LocalDateTime getLastModified();

        String getSyncStatus();
    }

}

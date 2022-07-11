package com.aprisoft.message.queue.app.entities;

import com.blazebit.persistence.view.EntityView;
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
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "LABORATORY")
@Data
@SQLDelete(sql = "update laboratory set archived = true, last_modified = current_timestamp where id = ?", check = ResultCheckStyle.COUNT)
@EqualsAndHashCode(of = {"patient", "dateResultReceived"}, callSuper = true)
@ToString(of = "dateResultReceived", callSuper = true)
@TypeDef(
        name = "jsonb",
        typeClass = JsonBinaryType.class
)
public class Laboratory extends TransactionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @JoinColumn(name = "PATIENT_ID")
    @ManyToOne
    private Patient patient;

    private LocalDate dateResultReceived;

    private LocalDate dateSampleCollected;

    private LocalDate dateAssay;

    @Size(max = 15)
    @Column(name = "LABNO")
    private String labNo;

    @Column(name = "sync_status")
    private String syncStatus;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private JsonNode extra;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private Set<LaboratoryLine> lines = new HashSet<>();

    @EntityView(Laboratory.class)
    public interface InitialLaboratoryView {

        String getUuid();

        LocalDate getDateResultReceived();

        LocalDate getDateSampleCollected();

        LocalDate getDateAssay();

        String getLabNo();

        JsonNode getExtra();

        @MappingSingular
        Set<LaboratoryLine> getLines();

        Boolean getArchived();

        LocalDateTime getLastModified();

        String getSyncStatus();
    }
}

package com.aprisoft.message.queue.app.entities;

import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "covid19_vaccination")
@SQLDelete(sql = "update covid19_vaccination set archived = true where id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "archived = false")
@TypeDef(
        name = "jsonb",
        typeClass = JsonBinaryType.class
)
public class Covid19Vaccination implements Persistable<UUID> {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    UUID id;

    private String vaccinationId;

    private LocalDate birthDate;

    private String surname;

    private String firstName;

    private String otherNames;

    private String email;

    private String phone;

    private String sex;

    private String address;

    private String firstDoseAdverseEffect;

    private LocalDate dateOfFirstDoseAdverseEffect;

    private String secondDoseAdverseEffect;

    private LocalDate dateOfSecondDoseAdverseEffect;

    private String boosterDoseAdverseEffect;

    private LocalDate dateOfBoosterDoseAdverseEffect;

    @ManyToOne
    @JoinColumn(name = "PATIENT_ID", referencedColumnName = "UUID")
    Patient patient;

    @ManyToOne
    @NotNull
    Facility facility;

    private String firstDose;

    private LocalDate dateOfFirstDose;

    private String firstDoseBatchNo;

    private LocalDate firstDoseExpiryDate;

    private String firstDoseLocation;

    private String firstDoseFacility;

    private String secondDose;

    private LocalDate dateOfSecondDose;

    private String secondDoseBatchNo;

    private LocalDate secondDoseExpiryDate;

    private String secondDoseLocation;

    private String secondDoseFacility;

    private String boosterShot;

    private LocalDate dateOfBoosterShot;

    private String boosterShotBatchNo;

    private LocalDate boosterShotExpiryDate;

    private String boosterDoseLocation;

    private String boosterDoseFacility;

    private Boolean archived = false;

    private LocalDateTime lastModified;

    private Boolean workInHealthSector;

    private String occupation;

    private String placeOfWork;

    private String firstDoseCommunity;
    private String secondDoseCommunity;
    private String boosterDoseCommunity;

    private String hivStatus;
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private JsonNode medicalConditions;

    private LocalDate dateOfDecliningVaccination;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private JsonNode reasonsForDecliningVaccination;

    @Override
    public boolean isNew() {
        return id == null;
    }

    @PrePersist
    public void prePersist() {
        lastModified = LocalDateTime.now();
        archived = false;
    }

    @PreUpdate
    public void preUpdate() {
        lastModified = LocalDateTime.now();
    }
}

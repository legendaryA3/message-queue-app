package com.aprisoft.message.queue.app.entities;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.blazebit.persistence.view.Mapping;
import com.blazebit.persistence.view.MappingSingular;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "CLINIC")
@Data
@ToString(of = "dateVisit", callSuper = true)
@SQLDelete(sql = "update clinic set archived = true, last_modified = current_timestamp where id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "archived = false")
@TypeDef(
        name = "jsonb",
        typeClass = JsonBinaryType.class
)
@EqualsAndHashCode(of = {"patient", "dateVisit"}, callSuper = true)
public class Clinic extends TransactionEntity implements Serializable {
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

    @Size(max = 15)
    @Column(name = "CLINIC_STAGE")
    private String clinicStage;

    @Size(max = 15)
    @Column(name = "FUNC_STATUS")
    private String funcStatus;

    @Size(max = 75)
    @Column(name = "TB_STATUS")
    private String tbStatus;

    @Column(name = "VIRAL_LOAD")
    private Double viralLoad;

    @Column(name = "CD4")
    private Double cd4;

    @Column(name = "CD4P")
    private Double cd4p;

    @ManyToOne
    @JoinColumn(name = "REGIMEN_TYPE_ID")
    private RegimenType regimenType;

    @ManyToOne
    @JoinColumn(name = "REGIMEN_ID")
    private Regimen regimen;

    @Column(name = "BODY_WEIGHT")
    private Double bodyWeight;

    @Column(name = "HEIGHT")
    private Double height;

    @Column(name = "WAIST")
    private Double waist;

    @Size(max = 7)
    @Column(name = "BP")
    private String bp;

    @Column(name = "PREGNANT")
    private Boolean pregnant;

    @Column(name = "LMP")
    private LocalDate lmp;

    @Column(name = "BREASTFEEDING")
    private Boolean breastfeeding;

    @Size(max = 5)
    @Column(name = "OI_SCREENED")
    private String oiScreened;

    @Size(max = 50)
    @Column(name = "STI_IDS")
    private String stiIds;

    @Size(max = 5)
    @Column(name = "STI_TREATED")
    private String stiTreated;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    @JsonIgnore
    Set<OpportunisticInfection> opportunisticInfections = new HashSet<>();

    @Size(max = 5)
    @Column(name = "ADR_SCREENED")
    private String adrScreened;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    Set<ClinicAdverseDrugReaction> adverseDrugReactions = new HashSet<>();

    @Size(max = 15)
    @Column(name = "ADHERENCE_LEVEL")
    private String adherenceLevel;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    Set<Adhere> adheres = new HashSet<>();

    @Column(name = "COMMENCE")
    private Boolean commence = false;

    @Column(name = "NEXT_APPOINTMENT")
    private LocalDate nextAppointment;

    @Size(max = 500)
    @Column(name = "NOTES")
    private String notes;

    @Size(max = 90)
    @Column(name = "GESTATIONAL_AGE")
    private String gestationalAge;

    @Size(max = 90)
    @Column(name = "MATERNAL_STATUS_ART")
    private String maternalStatusArt;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private JsonNode extra;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "sync_status")
    private String syncStatus;

    @EntityView(Clinic.class)
    public interface IdView {
        @IdMapping
        Long getId();

        @Mapping("patient.gender")
        String getSex();
    }

    @EntityView(Clinic.class)
    public interface InitialClinicView {

        LocalDate getDateVisit();

        String getClinicStage();

        String getFuncStatus();

        String getTbStatus();

        Double getViralLoad();

        Double getCd4();

        Double getCd4p();

        RegimenType.RegimenTypeView getRegimenType();

        Regimen.RegimenView getRegimen();

        Double getBodyWeight();

        Double getHeight();

        Double getWaist();

        String getBp();

        Boolean getPregnant();

        LocalDate getLmp();

        Boolean getBreastfeeding();

        String getOiScreened();

        String getStiIds();

        String getStiTreated();

        @MappingSingular
        Set<OpportunisticInfection> getOpportunisticInfections();

        String getAdrScreened();

        @MappingSingular
        Set<ClinicAdverseDrugReaction> getAdverseDrugReactions();

        String getAdherenceLevel();

        @MappingSingular
        Set<Adhere> getAdheres();

        Boolean getCommence();

        LocalDate getNextAppointment();

        String getNotes();

        String getGestationalAge();

        String getMaternalStatusArt();

        JsonNode getExtra();

        String getUuid();

        LocalDateTime getLastModified();

        String getSyncStatus();
    }
}

package com.aprisoft.message.queue.app.entities;

import com.blazebit.persistence.view.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;
import org.hibernate.annotations.*;
import com.aprisoft.message.queue.app.entities.enumerations.ClientStatus;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PATIENT")
@Data
@ToString(of = {"id", "hospitalNum", "surname", "otherNames"})
@Where(clause = "archived = false or archived = true")
@SQLDelete(sql = "update Patient set archived = true, last_modified = current_timestamp where id = ?", check = ResultCheckStyle.COUNT)
@EqualsAndHashCode(of = {"hospitalNum", "facility"})
@TypeDef(
        name = "jsonb",
        typeClass = JsonBinaryType.class
)
public class Patient extends TransactionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "HOSPITAL_NUM")
    private String hospitalNum;

    @Size(max = 36)
    @Column(name = "UNIQUE_ID")
    private String uniqueId;

    @Size(max = 45)
    @Column(name = "SURNAME")
    private String surname;

    @Size(max = 75)
    @Column(name = "OTHER_NAMES")
    private String otherNames;

    @Size(max = 7)
    @Column(name = "GENDER")
    private String gender;

    @Column(name = "DATE_BIRTH")
    @NotNull
    private LocalDate dateBirth;

    @Size(max = 15)
    @Column(name = "MARITAL_STATUS")
    private String maritalStatus;

    @Size(max = 128)
    @Column(name = "EDUCATION")
    private String education;

    @Size(max = 128)
    @Column(name = "OCCUPATION")
    private String occupation;

    @Size(max = 300)
    @Column(name = "ADDRESS")
    private String address;

    @Size(max = 25)
    @Column(name = "PHONE")
    private String phone;

    @Column(name = "dob_estimated")
    private Boolean dobEstimated;

    @Size(max = 75)
    private String nextOfKin;

    private String nextOfKinAddress;

    @Size(max = 25)
    private String nextOfKinPhone;

    @Size(max = 25)
    private String nextOfKinRelationship;

    @Size(max = 15)
    @Column(name = "ENTRY_POINT")
    private String entryPoint;

    @Size(max = 15)
    @Column(name = "TARGET_GROUP")
    private String targetGroup;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "sync_status")
    private String syncStatus;

    @Column(name = "DATE_CONFIRMED_HIV")
    private LocalDate dateConfirmedHiv;

    @Column(name = "DATE_ENROLLED_PMTCT")
    private LocalDate dateEnrolledPMTCT;

    @Size(max = 100)
    @Column(name = "SOURCE_REFERRAL")
    private String sourceReferral;

    @Size(max = 35)
    @Column(name = "TIME_HIV_DIAGNOSIS")
    private String timeHivDiagnosis;

    @Size(max = 75)
    @Column(name = "TB_STATUS")
    private String tbStatus;

    @Column(name = "PREGNANT")
    private Boolean pregnant;

    @Column(name = "BREASTFEEDING")
    private Boolean breastfeeding;

    @Column(name = "DATE_REGISTRATION")
    private LocalDate dateRegistration;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS_AT_REGISTRATION")
    private ClientStatus statusAtRegistration;

    @Size(max = 15)
    @Column(name = "ENROLLMENT_SETTING")
    private String enrollmentSetting;

    @JoinColumn(name = "CASE_MANAGER_ID")
    @ManyToOne
    private CaseManager caseManager;

    /*@OneToOne
    private HtsNew htsNew;*/

    @Column(name = "DATE_STARTED")
    private LocalDate dateStarted;

    @Column(name = "SEND_MESSAGE")
    private Boolean sendMessage;

    @JoinColumn(name = "PARTNER_INFORMATION_ID")
    @ManyToOne(cascade = CascadeType.ALL)
    private PartnerInformation partnerInformation;

    @ManyToOne
    @JoinColumn(name = "LGA_ID")
    private Province lga;

    private Long htsId;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private JsonNode extra;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
    @JsonIgnore
    private List<Devolve> devolves;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Eac> eacs;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "patient")
    @JsonIgnore
    private List<Encounter> encounters;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "patient")
    @JsonIgnore
    private List<Clinic> clinics;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "patient")
    @JsonIgnore
    private List<Pharmacy> pharmacies;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "patient")
    @JsonIgnore
    private List<Anc> ancs;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "patient")
    @JsonIgnore
    private List<PatientCaseManager> patientCaseManagers;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "patient")
    @JsonIgnore
    private List<MotherInformation> motherInformations;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "patient")
    @JsonIgnore
    private List<MaternalFollowup> maternalFollowups;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "patient")
    @JsonIgnore
    private List<Delivery> deliveries;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "patient")
    @JsonIgnore
    private List<ChronicCare> chronicCares;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "patient")
    @JsonIgnore
    private List<Laboratory> laboratories;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "patient")
    @JsonIgnore
    private List<RegimenHistory> regimenHistories;

    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, mappedBy = "patient")
    @JsonIgnore
    private List<StatusHistory> statusHistories = new ArrayList<>();

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "patient")
    @JsonIgnore
    private List<DrugTherapy> drugTherapies;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "patient")
    @JsonIgnore
    private List<Nigqual> nigquals;

    @PrePersist
    @PreUpdate
    public void update() {
        surname = WordUtils.capitalize(StringUtils.lowerCase(surname));
        otherNames = WordUtils.capitalize(StringUtils.lowerCase(otherNames));

        address = WordUtils.capitalize(StringUtils.replace(StringUtils.lowerCase(address), ",", ", ").replaceAll("\\s+", " "));
        nextOfKinAddress = WordUtils.capitalize(StringUtils.replace(StringUtils.trimToEmpty(StringUtils.lowerCase(nextOfKinAddress)),
            ",", ", ").replaceAll("\\s+", " "));
        nextOfKin = WordUtils.capitalize(StringUtils.lowerCase(nextOfKin));
    }

    /*@EntityView(Patient.class)
    public interface IdView {
        @IdMapping
        @AttributeFilter(EqualFilter.class)
        UUID getId();
    }*/

    /*@EntityView(Patient.class)
    public interface CreateView extends IdView {

        String getUuid();
        // void setUuid(String uuid);

        LocalDateTime getLastModified();
        // void setLastModified(LocalDateTime lastModified);

        @Mapping("facility.id")
        Long getFacilityId();
        // void setFa

        @Mapping("hospitalNum")
        String getHospitalNum();

        @Mapping("uniqueId")
        String getUniqueId();

        @Mapping("surname")
        String getSurname();

        @Mapping("otherNames")
        String getOtherNames();

        @Mapping("gender")
        String getGender();

        @Mapping("dateBirth")
        LocalDate getDateBirth();

        @Mapping("maritalStatus")
        String getMaritalStatus();

        @Mapping("education")
        String getEducation();

        @Mapping("occupation")
        String getOccupation();

        @Mapping("address")
        String getAddress();

        @Mapping("phone")
        String getPhone();

        @Mapping("dobEstimated")
        Boolean getDobEstimated();

        @Mapping("nextOfKin")
        String getNextOfKin();

        @Mapping("nextOfKinAddress")
        String getNextOfKinAddress();

        @Mapping("nextOfKinPhone")
        String getNextOfKinPhone();

        @Mapping("nextOfKinRelationship")
        String getNextOfKinRelationship();

        @Mapping("entryPoint")
        String getEntryPoint();

        @Mapping("targetGroup")
        String getTargetGroup();

        @Mapping("htsNewId")
        Long getHtsNewId();

        @Mapping("dateConfirmedHiv")
        LocalDate getDateConfirmedHiv();

        @Mapping("dateEnrolledPMTCT")
        LocalDate getDateEnrolledPMTCT();

        @Mapping("sourceReferral")
        String getSourceReferral();

        @Mapping("timeHivDiagnosis")
        String getTimeHivDiagnosis();

        @Mapping("tbStatus")
        String getTbStatus();

        @Mapping("pregnant")
        Boolean getPregnant();

        @Mapping("breastfeeding")
        Boolean getBreastfeeding();

        @Mapping("dateRegistration")
        LocalDate getDateRegistration();

        @Mapping("statusAtRegistration")
        ClientStatus getStatusAtRegistration();

        @Mapping("enrollmentSetting")
        String getEnrollmentSetting();

        @Mapping("caseManager.id")
        Long getCaseManager();

        @Mapping("dateStarted")
        LocalDate getDateStarted();

        @Mapping("sendMessage")
        Boolean getSendMessage();

        @Mapping("partnerInformation.id")
        Long getPartnerInformation();

        @Mapping("lga.id")
        Long getLgaId();

        @Mapping("extra")
        JsonNode getExtra();

        @Mapping("htsId")
        Long getHtsId();

        @Mapping("archived")
        Boolean getArchived();

        String getSyncStatus();
    }

    @EntityView(Patient.class)
    @UpdatableEntityView
    public interface UpdateView extends CreateView {
        @NotNull
        void setId(UUID id);
    }*/

    @EntityView(Patient.class)
    public interface PatientRecordsView {

    }

}

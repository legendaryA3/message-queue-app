package com.aprisoft.message.queue.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.data.domain.Persistable;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "self_test")
@Data
public class SelfTesting implements Serializable, Persistable<UUID> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator",
        parameters = {
            @org.hibernate.annotations.Parameter(
                name = "uuid_gen_strategy_class",
                value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
            )
        }
    )
    @Column(name = "id", updatable = false, nullable = false)
    protected UUID id;
    @JoinColumn(name = "state_id")
    @ManyToOne
    private State state;
    @JoinColumn(name = "lga_id")
    @ManyToOne(optional = false)
    private Province lga;
    @Column(name = "client_code")
    protected String clientCode;

    @Column(name = "reference_code")
    protected String referenceCode;
    @Column(name = "target_group")
    protected String targetGroup;
    @JoinColumn(name = "facility_id")
    @ManyToOne
    protected Facility facility;
    @Column(name = "surname")
    protected String surname;
    @Column(name = "first_name")
    protected String firstName;
    @Column(name = "middle_name")
    protected String middleName;
    @Column(name = "client_address")
    protected String clientAddress;
    @Column(name = "age")
    protected Long age;
    @Column(name = "date_visit")
    protected LocalDate dateVisit;
    @Column(name = "date_of_birth")
    protected LocalDate dateOfBirth;
    @Column(name = "gender")
    protected String gender;
    @Column(name = "phone_number")
    protected String phoneNumber;
    @Column(name = "service_delivery_point")
    protected String serviceDeliveryPoint;
    @Column(name = "distributor")
    protected String distributor;
    @Column(name = "distribution_channel")
    protected String distributionChannel;
    @Column(name = "previous_hiv_test")
    protected String previousHIVTest;
    @Column(name = "known_hiv_status")
    protected String knownHIVStatus;
    @Type(type = "jsonb")
    @Column(name = "intended_use", columnDefinition = "jsonb")
    protected JsonNode intendedUse;
    @Column(name = "marital_status")
    protected String maritalStatus;
    @Column(name = "number_of_kit_issued")
    protected String numberOfKitIssued;
    @Column(name = "type_of_hiv_st")
    protected String typeOfHIVST;
    @Column(name = "type_of_testing_frequency")
    protected String typeOfTestingFrequency;
    @Column(name = "test_kit_used")
    protected String testKitUsed;
    @Column(name = "referred_to")
    protected String referredTo;
    @Column(name = "date_referred")
    protected LocalDate dateReferred;
    @Column(name = "test_result")
    protected String testResult;
    @Column(name = "referred")
    protected String referred;
    @Column(name = "service_referred_for")
    protected String serviceReferredFor;
    @Column(name = "received_confirmatory_hts")
    protected String receivedConfirmatoryHTS;
    @Column(name = "results_of_confirmatory_hts")
    protected String resultsOfConfirmatoryHTS;
    @Column(name = "positive_linked_to_care")
    protected String positiveLinkedToCare;
    @Column(name = "accessed_prevention_services")
    protected String accessedPreventionServices;
    @Column(name = "follow_up_insert_outcome_code")
    protected String followUpInsertOutcomeCode;
    @Column(name = "outcome")
    protected String outcome;
    @Column(name = "user_id")
    protected Long userId;
    @Column(name = "archived")
    protected Boolean archived;
    @Column(
        name = "created_at"
    )
    protected LocalDateTime createdAt;
    @Column(
        name = "updated_at"
    )
    @JsonIgnore
    protected LocalDateTime updatedAt;
    @Override
    public boolean isNew() {
        return false;
    }
    @PrePersist
    protected void preSave() {
        if (id == null) id = UUID.randomUUID();
        if (createdAt == null) createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        archived = Boolean.FALSE;
    }
    @PreUpdate
    protected void preUpdate() {
        // if (id == null) htsUuid = UUID.randomUUID().toString();
        // createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

}

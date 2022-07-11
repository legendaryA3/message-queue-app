package com.aprisoft.message.queue.app.entities;

import lombok.Data;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "Eid")
@Data
@SQLDelete(sql = "update eid set archived = true, last_modified = current_timestamp where id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "archived = false")
public class Eid extends TransactionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @JoinColumn(name = "PATIENT_ID")
    @ManyToOne
    private Patient patient;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "LABNO")
    private String labNo;

    @Size(max = 75)
    @Column(name = "MOTHER_NAME")
    private String motherName;

    @Size(max = 100)
    @Column(name = "MOTHER_ADDRESS")
    private String motherAddress;

    @Size(max = 15)
    @Column(name = "MOTHER_PHONE")
    private String motherPhone;

    @Size(max = 75)
    @Column(name = "SENDER_NAME")
    private String senderName;

    @Size(max = 75)
    @Column(name = "SENDER_DESIGNATION")
    private String senderDesignation;

    @Size(max = 100)
    @Column(name = "SENDER_ADDRESS")
    private String senderAddress;

    @Size(max = 15)
    @Column(name = "SENDER_PHONE")
    private String senderPhone;

    @Size(max = 100)
    @Column(name = "REASON_PCR")
    private String reasonPcr;

    @Size(max = 7)
    @Column(name = "RAPID_TEST_DONE")
    private String rapidTestDone;

    @Column(name = "DATE_RAPID_TEST")
    private LocalDate dateRapidTest;

    @Size(max = 15)
    @Column(name = "RAPID_TEST_RESULT")
    private String rapidTestResult;

    @Size(max = 45)
    @Column(name = "MOTHER_ART_RECEIVED")
    private String motherArtReceived;

    @Size(max = 45)
    @Column(name = "MOTHER_PROPHYLAX_RECEIVED")
    private String motherProphylaxReceived;

    @Size(max = 45)
    @Column(name = "CHILD_PROPHYLAX_RECEIVED")
    private String childProphylaxReceived;

    @Size(max = 7)
    @Column(name = "BREASTFED_EVER")
    private String breastfedEver;

    @Size(max = 45)
    @Column(name = "FEEDING_METHOD")
    private String feedingMethod;

    @Size(max = 7)
    @Column(name = "BREASTFED_NOW")
    private String breastfedNow;

    @Column(name = "FEEDING_CESSATION_AGE")
    private Integer feedingCessationAge;

    @Size(max = 45)
    @Column(name = "COTRIM")
    private String cotrim;

    @Column(name = "NEXT_APPOINTMENT")
    private LocalDate nextAppointment;
}

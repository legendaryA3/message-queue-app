package com.aprisoft.message.queue.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "ENCOUNTER")
@SQLDelete(sql = "update encounter set archived = true, last_modified = current_timestamp where id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "archived = false")
@Data
@EqualsAndHashCode(of = {"patient", "dateVisit"}, callSuper = true)
public class Encounter extends TransactionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @JoinColumn(name = "PATIENT_ID")
    @ManyToOne
    @JsonIgnore
    private Patient patient;

    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE_VISIT")
    private LocalDate dateVisit;

    @Size(max = 5)
    @Column(name = "QUESTION1")
    private String question1;

    @Size(max = 5)
    @Column(name = "QUESTION2")
    private String question2;

    @Size(max = 5)
    @Column(name = "QUESTION3")
    private String question3;

    @Size(max = 5)
    @Column(name = "QUESTION4")
    private String question4;

    @Size(max = 5)
    @Column(name = "QUESTION5")
    private String question5;

    @Size(max = 5)
    @Column(name = "QUESTION6")
    private String question6;

    @Size(max = 5)
    @Column(name = "QUESTION7")
    private String question7;

    @Size(max = 5)
    @Column(name = "QUESTION8")
    private String question8;

    @Size(max = 5)
    @Column(name = "QUESTION9")
    private String question9;

    @Size(max = 100)
    @Column(name = "REGIMEN1")
    private String regimen1;

    @Size(max = 100)
    @Column(name = "REGIMEN2")
    private String regimen2;

    @Size(max = 100)
    @Column(name = "REGIMEN3")
    private String regimen3;

    @Size(max = 100)
    @Column(name = "REGIMEN4")
    private String regimen4;

    @Column(name = "DURATION1")
    private Integer duration1;

    @Column(name = "DURATION2")
    private Integer duration2;

    @Column(name = "DURATION3")
    private Integer duration3;

    @Column(name = "DURATION4")
    private Integer duration4;

    @Column(name = "PRESCRIBED1")
    private Integer prescribed1;

    @Column(name = "PRESCRIBED2")
    private Integer prescribed2;

    @Column(name = "PRESCRIBED3")
    private Integer prescribed3;

    @Column(name = "PRESCRIBED4")
    private Integer prescribed4;

    @Column(name = "DISPENSED1")
    private Integer dispensed1;

    @Column(name = "DISPENSED2")
    private Integer dispensed2;

    @Column(name = "DISPENSED3")
    private Integer dispensed3;

    @Column(name = "DISPENSED4")
    private Integer dispensed4;

    @Size(max = 500)
    @Column(name = "NOTES")
    private String notes;

    @Column(name = "NEXT_REFILL")
    private LocalDate nextRefill;

    @Size(max = 100)
    @Column(name = "REGIMENTYPE")
    private String regimentype;

    private String puuid;
}

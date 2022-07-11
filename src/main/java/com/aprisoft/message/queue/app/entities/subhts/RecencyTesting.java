package com.aprisoft.message.queue.app.entities.subhts;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecencyTesting {

    public String testName;
    public String testDate;
    public String sampleType;
    public String dateSampleCollected;
    public String dateSampleSent;
    public String pcrLab;
    public String rapidRecencyAssay;
    public Double viralLoadConfirmationResult;
    public String viralLoadConfirmationTestDate;
    public String finalRecencyTestResult;
    public String consent;
    public String recencyNumber;
    public String controlLine;
    public String verificationLine;
    public String longTermLine;
    public String recencyInterpretation;
    public String viralLoadRequest;
    public String sampleReferenceNumber;
    public String viralLoadClassification;
}

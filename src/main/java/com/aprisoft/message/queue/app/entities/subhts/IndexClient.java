package com.aprisoft.message.queue.app.entities.subhts;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndexClient {
    public String surname;
    public String firstName;
    public String lastName;
    public String partnerId;
    public String dateOfBirth;
    public String gender;
    public String age;
    public String homeAddress;
    public String hangOutSpot;
    public String phoneNumber;
    public String alternativePhoneNumber;
    public String relationshipToIndexClient;
    public String doYouCurrentlyLiveWithThePartner;
    public String asPartnerEverTestedPositiveForHIV;
    public String isPartnerTakingMedicationsForHIV;
    public JsonNode intimatePartnerViolenceScreening;
    public String notificationMethodSelected;
    public String byWhichDateWillPartnerComeForTesting;
}

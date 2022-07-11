package com.aprisoft.message.queue.app.controllers;

import com.aprisoft.message.queue.app.entities.DataSync;
import com.aprisoft.message.queue.app.entities.Patient;
import com.aprisoft.message.queue.app.entities.entity.views.InitialPatientView;
import com.aprisoft.message.queue.app.repositories.FacilityRepository;
import com.aprisoft.spring_messaging.entities.*;
import com.aprisoft.spring_messaging.entities.entity.views.*;
import com.aprisoft.message.queue.app.repositories.DataSyncRepository;
import com.aprisoft.message.queue.app.repositories.PatientRepository;
import com.aprisoft.message.queue.app.services.SyncTableService;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.view.EntityViewManager;
import com.blazebit.persistence.view.EntityViewSetting;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MessageController {
    public final DataSyncRepository dataSyncRepository;
    public final SyncTableService syncTableService;
    public final PatientRepository patientRepository;
    public final EntityManager em;
    public final CriteriaBuilderFactory cbf;
    private final EntityViewManager evm;
    public final FacilityRepository facilityRepository;


    @GetMapping("/message")
    public List<InitialPatientView> sendMessage() {
        var settings = EntityViewSetting.create(InitialPatientView.class, 0, 10);
        var cb = cbf.create(em, InitialPatientView.class)
                .from(Patient.class)
                .where("dateRegistration").ge(LocalDate.now().minusYears(2))
                .orderByAsc("id");

        return evm.applySetting(settings, cb).withCountQuery(false).getResultList();

    }

    @GetMapping("/last")
    public DataSync.MaxSyncTime maxSyncTime() {
        var settings = EntityViewSetting.create(DataSync.MaxSyncTime.class);
        var cb = cbf.create(em, DataSync.MaxSyncTime.class)
                .from(DataSync.class);

        return (DataSync.MaxSyncTime) evm.applySetting(settings, cb);
    }

    @GetMapping("/patient")
    public List<InitialPatientView> getPatientByMaxTime() {
        var settings =
                EntityViewSetting.create(InitialPatientView.class, 1, 1);
        var cb = cbf.create(em, InitialPatientView.class)
                .from(Patient.class)
                .orderByAsc("id");
        // log.info("Generated query == {}", cb.getQuery().toString());

        return evm.applySetting(settings, cb).withCountQuery(false).getResultList();

        //.withCountQuery(false).getResultList();
        // return patientRepository.findByLastModifiedGreaterThan(patientMaxTime);
    }

}

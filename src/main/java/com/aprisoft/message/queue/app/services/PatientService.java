package com.aprisoft.message.queue.app.services;

import com.aprisoft.message.queue.app.entities.Patient;
import com.aprisoft.message.queue.app.repositories.PatientRepository;
import com.aprisoft.message.queue.app.entities.entity.views.PatientView;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.view.EntityViewManager;
import com.blazebit.persistence.view.EntityViewSetting;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private static final Logger logger = LoggerFactory.getLogger(PatientService.class.getSimpleName());

    public final EntityManager em;
    public final CriteriaBuilderFactory cbf;
    private final EntityViewManager evm;

    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    public List<PatientView> getPatientByMaxTime(LocalDateTime patientMaxTime) {
        var settings = EntityViewSetting.create(PatientView.class);
        var cb = cbf.create(em, PatientView.class)
                .from(Patient.class)
                .orderByAsc("id");
        if (patientMaxTime != null){
            cb = cbf.create(em, PatientView.class)
                    .from(Patient.class)
                    .where("lastModified").ge(patientMaxTime)
                    .orderByAsc("id");
        }
        return evm.applySetting(settings, cb).getResultList();

        //.withCountQuery(false).getResultList();
        // return patientRepository.findByLastModifiedGreaterThan(patientMaxTime);
    }

    @Transactional
    public void updateSyncStatus(String patientUuid, String status) {
        var cb = cbf.update(em, Patient.class)
                .set("syncStatus", status)
                .where("uuid").eq(patientUuid);
        cb.executeUpdate();
    }

}

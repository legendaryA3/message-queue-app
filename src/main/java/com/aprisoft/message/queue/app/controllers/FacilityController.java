package com.aprisoft.message.queue.app.controllers;

import com.aprisoft.message.queue.app.controllers.vm.FacilityVM;
import com.aprisoft.message.queue.app.entities.Facility;
import com.aprisoft.message.queue.app.repositories.FacilityRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/facilities")
public class FacilityController {

    private final FacilityRepository facilityRepository;

    public FacilityController(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    @GetMapping("/facilities_with_data")
    public List<FacilityVM> getFacilitiesWithRecord() {
        List<Facility> facilities = facilityRepository.findDistinctByFacilityA();
        List<FacilityVM> facilityVMS = new ArrayList<>();

        for (Facility facility: facilities) {
            FacilityVM facilityVM = new FacilityVM();
            facilityVM.setId(facility.getId());
            facilityVM.setName(facility.getName());
            facilityVMS.add(facilityVM);
        }

        return facilityVMS;
    }
}

package com.example.emergency_doctor.conrollers;

import com.example.emergency_doctor.entities.Clinic;
import com.example.emergency_doctor.models.Point;
import com.example.emergency_doctor.services.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/clinic")
public class ClinicController {
    @Autowired
    private ClinicService clinicService;

    @PostMapping("add-clinic")
    public Clinic addClinic(@RequestBody Clinic clinic){
       String userName= SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
       return clinicService.saveClinic(clinic,userName);
    }

    @GetMapping("get-the-k-nearest-clinics")
    public List<Clinic> getTheKClinics(@RequestBody Point point){
        return clinicService.getTheKNearestClilcs(point);
    }
}

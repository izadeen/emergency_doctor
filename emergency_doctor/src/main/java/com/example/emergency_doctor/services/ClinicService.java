package com.example.emergency_doctor.services;

import com.example.emergency_doctor.entities.Clinic;
import com.example.emergency_doctor.entities.User;
import com.example.emergency_doctor.models.Point;
import com.example.emergency_doctor.repostories.ClinicRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class ClinicService {
    @Autowired
    private ClinicRepository clinicRepository;
    @Autowired
    private UserService userService;

    public Clinic saveClinic(Clinic clinic, String doctorName){
        clinic.setDoctor(userService.getUserByUsername(doctorName));
        return clinicRepository.save(clinic);
    }

    public List<Clinic> getTheKNearestClilcs(Point point){
        List<Clinic> allClinics=clinicRepository.findAll();
        allClinics.sort((Clinic clinic1,Clinic clinic2)->{
            Point point1=Point.builder()
                    .latitude(clinic1.getLatitude())
                    .longitude(clinic1.getLongitude())
                    .build();
            Point point2=Point.builder()
                    .latitude(clinic2.getLatitude())
                    .longitude(clinic2.getLongitude())
                    .build();
            return (int)(point.distance(point1)-point.distance(point2));
        });
        return allClinics.stream().limit(5).collect(Collectors.toList());
    }
}

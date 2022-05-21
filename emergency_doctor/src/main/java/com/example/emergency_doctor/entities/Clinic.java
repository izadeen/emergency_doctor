package com.example.emergency_doctor.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private User doctor;
    private String description;
    private Double longitude;
    private Double latitude;

}

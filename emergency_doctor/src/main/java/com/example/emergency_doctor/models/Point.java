package com.example.emergency_doctor.models;

import lombok.Builder;

@Builder
public class Point {
    private Double longitude;
    private Double latitude;

    public Double distance(Point point){
        if(point.longitude==null || point.latitude ==null)
            return Double.POSITIVE_INFINITY;
        return Math.sqrt((this.latitude-point.latitude)+(this.longitude- point.longitude));
    }
}

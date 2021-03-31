package no.hiof.erikvs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Star extends CelestialBody{
    //Defining member variables
    private double effectiveTemp;
    public static final double ONE_SOLAR_MASS = 1.98892E30;
    public static final double ONE_SOLAR_RADIUS = 695700;

    // Empty constructor to use for conversion from JSON
    public Star(){
    }

    // constructor
    public Star(String name, double radius, double mass, double effectiveTemp, String pictureUrl) {
        super(name, radius, mass, pictureUrl);
        this.effectiveTemp = effectiveTemp;
    }


    // methods for calculating radius and mass of star
    @JsonIgnore
    @Override
    public double RadiusInKm() {
        return radius * ONE_SOLAR_RADIUS;
    }

    @JsonIgnore
    @Override
    public double MassInKg() {
        return mass * ONE_SOLAR_MASS;
    }

    // get/set for instance variable effectiveTemp
    @JsonProperty("effectiveTemp")
    public double getEffectiveTemp() {
        return effectiveTemp;
    }

    public void setEffectiveTemp(int effectiveTemp) {
        this.effectiveTemp = effectiveTemp;
    }

    // get/set for instance variable name
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // get/set for instance variable radius
    @JsonProperty("radius")
    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    // get/set for instance variable mass
    @JsonProperty("mass")
    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    // get/set for instance variable pictureUrl
    @JsonProperty("pictureUrl")
    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @JsonIgnore
    @Override
    public String toString() {
        return name + " is the largest star in our solar system. It has a radius of " + radius + " km, and a mass of " + mass + " kg.";
    }

}
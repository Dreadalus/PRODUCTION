package no.hiof.erikvs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Planet extends NaturalSatellite {
    //member variables inherited from CelestialBody

    // Empty constructor to use for conversion from JSON
    public Planet(){
    }

    // constructor
    public Planet(int solarOrder, String name, double radius, double mass, double semiMajorAxis, double eccentricity, int orbitalPeriod, String pictureUrl, CelestialBody centralCelestialBody) {
        super(solarOrder, name, radius, mass, semiMajorAxis, eccentricity, orbitalPeriod, pictureUrl, centralCelestialBody);
    }

    // methods for calculating radius, mass and surface gravity of planet in km/kg/(m/s2)
    @JsonIgnore
    @Override
    public double RadiusInKm() {
        return radius *  ONE_JUPITER_RADIUS;
    }

    @JsonIgnore
    @Override
    public double MassInKg() {
        return mass * ONE_JUPITER_MASS;
    }

    @JsonIgnore
    public double getSurfaceGravity() {
        return GRAVITATIONAL_CONSTANT * MassInKg() / Math.pow(((RadiusInKm())*1000), 2);
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
        return name + " is a planet in our solar system. It has a radius of " + radius + " km, and a mass of " + mass + " kg.";
    }
}
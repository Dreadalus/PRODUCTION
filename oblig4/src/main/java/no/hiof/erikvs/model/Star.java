package no.hiof.erikvs.model;

public class Star extends CelestialBody{
    //Defining member variables
    private int effectiveTemp;
    public static final double ONE_SOLAR_MASS = 1.98892E30;
    public static final double ONE_SOLAR_RADIUS = 695700;

    // constructor
    public Star(String name, double radius, double mass, int effectiveTemp, String pictureUrl) {
        super(name, radius, mass);
        this.effectiveTemp = effectiveTemp;
    }

    // methods for calculating radius and mass of star
    @Override
    public double RadiusInKm() {
        return radius * ONE_SOLAR_RADIUS;
    }

    @Override
    public double MassInKg() {
        return mass * ONE_SOLAR_MASS;
    }

    // get/set for instance variable effectiveTemp
    public int getEffectiveTemp() {
        return effectiveTemp;
    }

    public void setEffectiveTemp(int effectiveTemp) {
        this.effectiveTemp = effectiveTemp;
    }

    @Override
    public String toString() {
        return name + " is the largest star in our solar system. It has a radius of " + radius + " km, and a mass of " + mass + " kg.";
    }

}
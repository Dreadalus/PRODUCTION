package no.hiof.erikvs.model;

public abstract class CelestialBody implements Comparable<CelestialBody>{
    public String name;
    public double radius, mass;

    public CelestialBody(String name, double radius, double mass) {
        this.name = name;
        this.radius = radius;
        this.mass = mass;
    }

    public abstract double MassInKg();

    public abstract double RadiusInKm();

    //TODO: 2.1 implement Comparable in PlanetSystem

    @Override// method for comparing celestial bodies by mass and radius
    public int compareTo(CelestialBody otherCelestialBody) {
        int returnvalue = (int) (this.MassInKg() - otherCelestialBody.MassInKg());

        if (returnvalue == 0)
            return (int) (this.RadiusInKm()- otherCelestialBody.RadiusInKm());

        return returnvalue;
    }

    // get/set for instance variable name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // get/set for instance variable radius
    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    // get/set for instance variable mass
    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }
}

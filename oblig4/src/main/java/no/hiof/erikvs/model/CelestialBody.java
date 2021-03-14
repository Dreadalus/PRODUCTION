package no.hiof.erikvs.model;

public abstract class CelestialBody implements Comparable<CelestialBody>{
    public int solarOrder;
    public String name, pictureUrl;
    public double radius, mass;


    public CelestialBody(int solarOrder, String name, double radius, double mass, String pictureUrl) {
        this.solarOrder = solarOrder;
        this.name = name;
        this.radius = radius;
        this.mass = mass;
        this.pictureUrl = pictureUrl;
    }

    public abstract double MassInKg();

    public abstract double RadiusInKm();


    /**2.1 implement Comparable in CelestialBody**/
    @Override// method for comparing celestial bodies by mass and radius
    public int compareTo(CelestialBody otherCelestialBody) {
        return this.name.compareTo(otherCelestialBody.name);
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

    // get/set for instance variable pictureUrl
    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    // get/Set for instance variable solarOrder
    public int getSolarOrder() {
        return solarOrder;
    }

    public void setSolarOrder(int solarOrder) {
        this.solarOrder = solarOrder;
    }
}

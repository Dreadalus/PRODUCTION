public class Star extends CelestialBody{
    //Defining member variables
    private int effectiveTemp;

    // constructor
    public Star(String name, double radius, double mass, int effectiveTemp) {
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
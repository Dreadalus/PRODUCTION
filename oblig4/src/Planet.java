public class Planet extends NaturalSatellite {
    //member variables inherited from CelestialBody

    // constructor
    public Planet(String name, double radius, double mass, double semiMajorAxis, double eccentricity, int orbitalPeriod, CelestialBody centralCelestialBody) {
        super(name, radius, mass, semiMajorAxis, eccentricity, orbitalPeriod, centralCelestialBody);
    }

    // methods for calculating radius, mass and surface gravity of planet in km/kg/(m/s2)
    @Override
    public double RadiusInKm() {
        return radius *  ONE_JUPITER_RADIUS;
    }

    @Override
    public double MassInKg() {
        return mass * ONE_JUPITER_MASS;
    }

    public double getSurfaceGravity() {
        return GRAVITATIONAL_CONSTANT * MassInKg() / Math.pow(((RadiusInKm())*1000), 2);
    }

    @Override
    public String toString() {
        return name + " is a planet in our solar system. It has a radius of " + radius + " km, and a mass of " + mass + " kg.";
    }
}
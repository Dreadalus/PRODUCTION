public abstract class CelestialBody {
    public String name;
    public double radius, mass;
    public static final double ONE_JUPITER_MASS = 1.898E27;
    public static final double ONE_JUPITER_RADIUS = 71492;
    public static final double GRAVITATIONAL_CONSTANT = 0.0000000000667408;
    public static final double ONE_ASTRONOMICAL_UNITS = 149597871;

    public CelestialBody(String name, double radius, double mass) {
        this.name = name;
        this.radius = radius;
        this.mass = mass;
    }

    public abstract double MassInKg();

    public abstract double RadiusInKm();

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

public class Planet {
    //Defining member variables
    private String name;
    private double radius;
    private double mass;

    // Defining constructor
    public Planet(String name, double radius, double mass) {
        this.name = name;
        this.radius = radius;
        this.mass = mass;
    }

    // methods for calculating radius, mass and surface gravity of planet in km/kg/(m/s2)
    public double planetRadius() {
        return radius *  71492;
    }

    public double planetMass() {
        return mass * 1.898E27;
    }

    public double planetGravity() {
        return 0.0000000000667408 * planetMass() / Math.pow(((planetRadius())*1000), 2);
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

    @Override
    public String toString() {
        return name + " is a planet in our solar system. It has a radius of " + radius + " km, and a mass of " + mass + " kg.";
    }
}
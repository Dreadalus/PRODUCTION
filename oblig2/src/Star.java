public class Star {
    //Defining member variables
    private String name;
    private double radius;
    private double mass;
    private int effectiveTemp;

    // Defining constructor
    public Star(String name, double radius, double mass, int effectiveTemp) {
        this.name = name;
        this.radius = radius;
        this.mass = mass;
        this.effectiveTemp = effectiveTemp;
    }

    // methods for calculating radius and mass of star in km/kg
   public double starRadius() {
        return radius * 695700;
    }

    public double starMass() {
        return mass * 1.98892E30;
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
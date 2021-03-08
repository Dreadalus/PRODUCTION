/*
Task2_3a Planet Class
*/

public class Planet {
	// Defining member variables
	private String planet;
	private int radius;
	private double mass;

	// Defining constructor
	public Planet(String planet, int radius, double mass){
		this.planet = planet;
		this.radius = radius;
		this.mass = mass;
	}

public void printPlanet() {
	System.out.println(planet + " has a radius of " + radius + " and a mass of " + mass + " kg ");
}
	// get/set for instance variable planet
	public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }
	// get/set for instance variable radius
	public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
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

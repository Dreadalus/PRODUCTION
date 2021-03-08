/*
Task2_3a Instancing Planet Class
*/

public class Oppgave2_3 {
	public static void main(String [] args ) {
	
	// Define instance variables for objects
	Planet planet = new Planet("Mars", 3889, 6.39E23);
	Planet test = new Planet("Test", 4444, 1.03E61);

	// Call print method of class
	planet.printPlanet();
	test.printPlanet();
	}
}
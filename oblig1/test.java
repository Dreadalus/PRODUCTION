/*make a class that represents a planet
has 3 variables, name radius and mass
instance variables should be private
make get and set methods
create objects of your class
*/

class Planet {
private String planet; 

//method get returns the value og variable name
public char getPlanet() {
	return planet;
}
public void setPlanet (String newPlanet) {
	this.planet = newPlanet
}
}

public class PlanetCreation {
	public static void main(String[] args) {
		// Create instance of Planet class
		Planet planet = newPlanet();
		
		//assigning values to fields of Planet
		planet.setName("Pluto");
		System.out.println(planet.getName());
}
}

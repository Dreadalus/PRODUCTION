import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {

        // define instance variables for objects TASK 2.2
        PlanetSystem home = new PlanetSystem("The Solar System");
        Star sun = new Star("The Sun", 1.0, 1.0, 5777);
        Planet mercury = new Planet ("Mercury", 0.03412549655905556, 1.7297154899894627E-4);
        Planet venus = new Planet ("Venus", 0.08465003077267387, 0.002564278187565859);
        Planet earth = new Planet ("Earth", 0.08911486599899289, 0.003146469968387777);
        Planet mars = new Planet ("Mars", 0.04741089912158004, 3.3667017913593256E-4);
        Planet jupiter = new Planet ("Jupiter", 1.0, 1.0);
        Planet saturn = new Planet ("Saturn", 0.8145247020645666, 0.2994204425711275);
        Planet uranus = new Planet ("Uranus", 0.35475297935433336, 0.04573761854583773);
        Planet neptune = new Planet ("Neptune", 0.34440217087226543, 0.05395152792413066);

        // define and populate ArrayList
        ArrayList<Planet> planetList = new ArrayList<Planet>();
        planetList.add(mercury);
        planetList.add(venus);
        planetList.add(earth);
        planetList.add(mars);
        planetList.add(jupiter);
        planetList.add(saturn);
        planetList.add(uranus);
        planetList.add(neptune);

        // assigning instance variable sun as variable centerStar of home
        home.setCenterStar(sun);
        home.setPlanetList(planetList);

        System.out.println("\n ******************** TASK 2.3 ********************\n");

        // call print method of class
        System.out.println(home);
        System.out.println(home.getCenterStar());
        System.out.println(planetList.get(7));
        System.out.println(planetList.get(2));
        System.out.println(planetList.get(3));


        System.out.println("\n ******************** TASK 2.4 ********************\n");

        // call methods to calculate mass/radius
        System.out.println("The radius of " + home.getPlanetList().get(5).getName() + " is " + home.getPlanetList().get(5).planetRadius() + " km.");
        System.out.println("The mass of " + home.getPlanetList().get(5).getName() + " is " + home.getPlanetList().get(5).planetMass() + " kg.");
        System.out.println("The radius of " + home.getCenterStar().getName() + " is " + home.getCenterStar().starRadius() + " km.");
        System.out.println("The mass of " + home.getCenterStar().getName() + " is " + home.getCenterStar().starMass() + " kg.");

        System.out.println("\n ******************** TASK 2.5 ********************\n");

        // call method to calculate gravity
        System.out.println("The surface gravity of " + home.getPlanetList().get(7).getName() + " is " + home.getPlanetList().get(7).planetGravity() + " m/s2.");

        System.out.println("\n ******************** TASK 2.6 ********************\n");

        // call methods to print biggest and smallest planet
        System.out.println(home.biggestPlanet() + " is the biggest planet.");
        System.out.println(home.smallestPlanet() + " is the smallest planet.");
        System.out.println();
    }
 }
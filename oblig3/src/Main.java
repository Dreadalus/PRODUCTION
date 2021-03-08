import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {

        PlanetSystem home = new PlanetSystem("The Solar System");
        Star sun = new Star("The sun", 1.0, 1.0, 5777);
        Planet mercury = new Planet ("Mercury", 0.03412549655905556, 1.7297154899894627E-4, 0.387,0.206, 88, sun);
        Planet venus = new Planet ("Venus", 0.08465003077267387, 0.002564278187565859, 0.723, 0.007, 225, sun);
        Planet earth = new Planet ("Earth", 0.08911486599899289, 0.003146469968387777, 1, 0.017, 365, sun);
        Planet mars = new Planet ("Mars", 0.04741089912158004, 3.3667017913593256E-4, 1.524, 0.093, 687, sun);
        Planet jupiter = new Planet ("Jupiter", 1.0, 1.0, 5.20440, 0.049, 4380, sun);
        Planet saturn = new Planet ("Saturn", 0.8145247020645666, 0.2994204425711275, 9.5826, 0.057, 10585, sun);
        Planet uranus = new Planet ("Uranus", 0.35475297935433336, 0.04573761854583773, 19.2184, 0.046, 30660, sun);
        Planet neptune = new Planet ("Neptune", 0.34440217087226543, 0.05395152792413066, 30.11, 0.010, 60225, sun);

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

        System.out.println("\n ******************** TASK 2.1 ********************\n");
        /* The problem with moving variables and methods from one class and up in the hierarchy is that the implementation of the methods differ in Planet and Star.
        * To avoid having multiple methods with similar functionality in CelestialBody they were made abstract and variables used in both methods were made constant.
        * This also means that when Planet if pushed further down the hierarchy by extending NaturalSatellite, it will still have access to the methods and variables of CelestialBody*/


        System.out.println("\n ******************** TASK 2.2 ********************\n");
        System.out.println(home.getPlanet("mars"));

        System.out.println("\n ******************** TASK 2.5 ********************\n");
        System.out.println("The earth has a distance of " + earth.distanceToCentralBody(0) + " km to " + earth.getCentralCelestialBody().getName() + " at 0 degrees.");
        System.out.println("The earth has a distance of " + earth.distanceToCentralBody(90) + " km to " + earth.getCentralCelestialBody().getName() + " at 90 degrees.");
        System.out.println("The earth has a distance of " + earth.distanceToCentralBody(180) + " km to " + earth.getCentralCelestialBody().getName() + " at 180 degrees.");
        System.out.println("The earth has a distance of " + earth.distanceToCentralBody(270) + " km to " + earth.getCentralCelestialBody().getName() + " at 270 degrees.");
        System.out.println("The earth has a distance of " + earth.distanceToCentralBody(360) + " km to " + earth.getCentralCelestialBody().getName() + " at 360 degrees.");

        System.out.println("The earth has a distance of " + earth.distanceToCentralBodyV2(6.28319) + " km to " + earth.getCentralCelestialBody().getName() + " at 6.28319 radians.");

        System.out.println("\n ******************** TASK 2.6 ********************\n");
        System.out.println("At a distance of " + earth.distanceToCentralBody(0) + " km, the Earth has a velocity of " + earth.orbitingVelocity(earth.distanceToCentralBody(0)) + " km/s.");
        System.out.println("At a distance of " + earth.distanceToCentralBody(45) + " km, the Earth has a velocity of " + earth.orbitingVelocity(earth.distanceToCentralBody(45)) + " km/s.");
        System.out.println("At a distance of " + earth.distanceToCentralBody(90) + " km, the Earth has a velocity of " + earth.orbitingVelocity(earth.distanceToCentralBody(90)) + " km/s.");
        System.out.println("At a distance of " + earth.distanceToCentralBody(135) + " km, the Earth has a velocity of " + earth.orbitingVelocity(earth.distanceToCentralBody(135)) + " km/s.");
        System.out.println("At a distance of " + earth.distanceToCentralBody(180) + " km, the Earth has a velocity of " + earth.orbitingVelocity(earth.distanceToCentralBody(180)) + " km/s.");

    }
}
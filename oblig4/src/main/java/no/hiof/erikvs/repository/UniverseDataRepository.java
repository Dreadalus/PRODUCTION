package no.hiof.erikvs.repository;

import no.hiof.erikvs.model.CelestialBody;
import no.hiof.erikvs.model.Planet;
import no.hiof.erikvs.model.PlanetSystem;
import no.hiof.erikvs.model.Star;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class UniverseDataRepository implements UniverseRepository {

    // Defining ArrayList which will contain Lists of objects
    private ArrayList<PlanetSystem> Galaxy = new ArrayList<>();

    // The ArrayList where planet objects are contained (within PlanetSystem)
    private ArrayList<Planet> planetList = new ArrayList<>();

    public UniverseDataRepository() {

        // Initializing all data regarding our planet system
        PlanetSystem solarSystem = new PlanetSystem("Solar System", "https://cdn.drawception.com/images/panels/2012/8-11/EjyON8jxzz-4.png"); // Which contains a CenterStar and an ArrayList of Planets

        // The contents of ArrayList planetList, our solar system
        Star sun = new Star(0,"Sun", 1.9885E30, 695342, 5777, "http://bit.ly/3cVhuZc");
        Planet mercury = new Planet(1,"Mercury", 3.283E23, 2439.7, 0.387, 0.206, 88, "http://bit.ly/2TB2Heo", sun);
        Planet venus = new Planet(2, "Venus", 4.867E24, 6051.8, 0.723, 0.007, 225, "http://bit.ly/2W3p4L9", sun);
        Planet earth = new Planet(3, "Earth", 5.972E24, 6371, 1, 0.017, 365, "http://bit.ly/33bvXLZ", sun);
        Planet mars = new Planet(4, "Mars", 6.39E23, 3389.5, 1.524, 0.093, 687, "http://bit.ly/3aGyFvr", sun);
        Planet jupiter = new Planet(5,"Jupiter", 1.898E27, 69911, 5.20440, 0.049, 4380, "http://bit.ly/2Q0fjK3", sun);
        Planet saturn = new Planet(6, "Saturn", 5.683E26, 58232, 9.5826, 0.057, 10585, "http://bit.ly/2W0sqic", sun);
        Planet uranus = new Planet(7, "Uranus", 8.681E25, 25362, 19.2184, 0.046, 30660, "http://bit.ly/335pbHy", sun);
        Planet neptune = new Planet(8, "Neptune", 1.024E26, 24622, 30.11, 0.010, 60225, "http://bit.ly/38AyEba", sun);

        // Adding content to ArrayList planetList (An arraylist containing only objects)
        planetList.add(mercury);
        planetList.add(venus);
        planetList.add(earth);
        planetList.add(mars);
        planetList.add(jupiter);
        planetList.add(saturn);
        planetList.add(uranus);
        planetList.add(neptune);

        // Adding reference information to solar system
        solarSystem.setPlanetList(planetList);
        solarSystem.setCenterStar(sun);

        // Adding the solar system (object solarSystem (containing planetList ArrayList and reference points)) to Arraylist containing ArrayList of Objects
        Galaxy.add(solarSystem);

    }

    /**
     * 2.4 Implementations of methods defined in UniverseRepository to get all planet systems and a single planet system
     **/
    @Override // Get all ArrayLists of planet systems in ArrayList Galaxy
    public ArrayList<PlanetSystem> getAllPlanetSystems() {
        return Galaxy;
    }

    @Override // Input planet system name and return given planet system (single object within arraylist)
    public PlanetSystem getPlanetSystem(String planetSystemName) {
        for (PlanetSystem planetSystem : Galaxy) {
            if (planetSystem.getName().equalsIgnoreCase(planetSystemName))
                return planetSystem;
        }
        return null;
    }

    /** 2.6 Implementations of methods defined in UniverseRepository to get all planets and a single planet of a given system
     **/
    @Override // Get all planet objects from ArrayList planetList, Michal guided me to this solution - but I had to change it a bit to make the last task work.
    public ArrayList<Planet> getAllPlanets(String planetSystemName, String sortByParam) {
        System.out.println(sortByParam);
        ArrayList<Planet> target = new ArrayList<Planet>();
        target = getPlanetSystem(planetSystemName).getPlanetList();
        System.out.println(target);
        /** 2.8 Implementations of methods defined in UniverseRepository to get all planets and a single planet of a given system
         **/
        if (sortByParam.equals("name")) // Two different implementations
            Collections.sort(target, new Comparator<Planet>() {
                @Override //overriding collections.sort within itself
                public int compare(Planet a, Planet b) {    //anon method
                    return a.name.compareTo(b.name);
                }
            });
        else if (sortByParam.equals("mass"))
            Collections.sort(target, new Comparator<Planet>() {
                @Override
                public int compare(Planet a, Planet b) {
                    int returnvalue = (int) (a.mass - b.mass);
                    return returnvalue;
                }
            });
        else if (sortByParam.equals("radius"))
            target.sort(Comparator.comparing(CelestialBody::getRadius)); // short hand code - double colon ??? I stole this on the internet.
        else if (sortByParam.equals("num"))
            target.sort(Comparator.comparing(CelestialBody::getSolarOrder));
        return target;
    }

   @Override // Input planet name and return given planet (object within arrayList)
   public Planet getSinglePlanet(String planetSystemName, String planetName) {
    for (PlanetSystem planetSystem : Galaxy){
            if (planetSystem.getName().equalsIgnoreCase(planetSystemName))
                return planetSystem.getPlanet(planetName);
        }
        return null;
    }
}

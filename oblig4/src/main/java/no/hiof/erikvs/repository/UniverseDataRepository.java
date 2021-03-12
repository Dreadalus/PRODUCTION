package no.hiof.erikvs.repository;

import no.hiof.erikvs.model.Planet;
import no.hiof.erikvs.model.PlanetSystem;
import no.hiof.erikvs.model.Star;
import java.util.ArrayList;

public class UniverseDataRepository implements UniverseRepository{

    // Defining ArrayList which will contain Lists of objects
    private ArrayList<PlanetSystem> Galaxy = new ArrayList<>();

    public UniverseDataRepository(){

        // Initializing all data regarding our planet system
        PlanetSystem solarSystem = new PlanetSystem("Solar System"); // Which contains a CenterStar and an ArrayList of Planets
        ArrayList<Planet> planetList = new ArrayList<>(); // The ArrayList where planet objects are contained (within PlanetSystem)

        // The contents of ArrayList planetList, our solar system
        Star sun = new Star("Sun",1.9885E30,695342,5777,"http://bit.ly/3cVhuZc");
        Planet mercury = new Planet ("Mercury", 3.283E23,2439.7, 0.387, 0.206, 88,"http://bit.ly/2TB2Heo");
        Planet venus = new Planet ("Venus",4.867E24,6051.8, 0.723, 0.007, 225,"http://bit.ly/2W3p4L9");
        Planet earth = new Planet ("Earth",5.972E24,6371, 1, 0.017, 365,"http://bit.ly/33bvXLZ");
        Planet mars = new Planet ("Mars",6.39E23,3389.5, 1.524, 0.093, 687,"http://bit.ly/3aGyFvr");
        Planet jupiter = new Planet ("Jupiter",1.898E27,69911, 5.20440, 0.049, 4380,"http://bit.ly/2Q0fjK3");
        Planet saturn = new Planet ("Saturn",5.683E26,58232, 9.5826, 0.057, 10585,"http://bit.ly/2W0sqic");
        Planet uranus = new Planet ("Uranus",8.681E25,25362, 19.2184, 0.046, 30660,"http://bit.ly/335pbHy");
        Planet neptune = new Planet ("Neptune",1.024E26,24622, 30.11, 0.010, 60225,"http://bit.ly/38AyEba");

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
    /**2.4 Implementations of methods defined in UniverseRepository to get all planet systems and a single planet system**/
    @Override // Get all ArrayLists of planet systems in ArrayList Galaxy
    public ArrayList<PlanetSystem> getAllPlanetSystems() {
        return Galaxy;
    }

    @Override // Input planet system name and return given planet system (single object within arraylist)
    public PlanetSystem getPlanetSystem(String planetSystemName) {
        for (PlanetSystem planetSystem : Galaxy){
            if (planetSystem.getName().equals(planetSystemName))
                return planetSystem;
        }
        return null;
    }
}

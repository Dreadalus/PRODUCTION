package no.hiof.erikvs.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.hiof.erikvs.model.CelestialBody;
import no.hiof.erikvs.model.Planet;
import no.hiof.erikvs.model.PlanetSystem;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class UniverseJSONRepository implements UniverseRepository{

    // Defining HashMap which will contain Lists of objects
    HashMap<String, PlanetSystem> Galaxy = new ObjectMapper().readValue(new File("src\\main\\resources\\planets_100.json", String.valueOf(HashMap.class)));

    // Defining ArrayList which will contain Lists of objects
    //private ArrayList<PlanetSystem> Galaxy = new ArrayList<>();

    // The ArrayList where planet objects are contained (within PlanetSystem)
    private ArrayList<Planet> planetList = new ArrayList<>();

    public UniverseJSONRepository() {

        // Creating instance of objectmapper class, used to read and write JSON.
        ObjectMapper objectmapper = new ObjectMapper();


        // Take either file name or File-object in constructor
        //TODO: make this work and add try/catch
      //  PlanetSystem Galaxy = objectmapper.readValue(new File("src\\main\\resources\\planets_100.json", String.valueOf(PlanetSystem.class)));

    }

    //TODO: need to change this to work with hashmap
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

        /** 2.1 HashMap methods **/


        /** 2.6 Implementations of methods defined in UniverseRepository to get all planets and a single planet of a given system
         **/
        @Override // Get all planet objects from ArrayList planetList. Michal guided me to this solution - but I had to change it a bit to make the last task work.
        public ArrayList<Planet> getAllPlanets(String planetSystemName, String sortByParam) {
            ArrayList<Planet> target = new ArrayList<Planet>();
            target = getPlanetSystem(planetSystemName).getPlanetList();

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
                target.sort(Comparator.comparing(CelestialBody::getRadius)); // short hand code - double colon operator
            else if (sortByParam.equals("num")) // because sort changes the order of the list, I added the variable solarOrder to celestial bodies so that there always is an original way to sort planets by.
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

package no.hiof.erikvs.repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.hiof.erikvs.model.CelestialBody;
import no.hiof.erikvs.model.Planet;
import no.hiof.erikvs.model.PlanetSystem;

import java.io.IOException;
import java.util.*;
import java.io.File;

import static java.time.chrono.JapaneseEra.values;

public class UniverseJSONRepository implements UniverseRepository {

    // Instantiate HashMap to store JSON data in.
    public HashMap<String, PlanetSystem> planetSystemHashMap = new HashMap<String, PlanetSystem>();

    public UniverseJSONRepository(File jsonFile) {

        // Creating instance of objectmapper class, used to read and write JSON.
        ObjectMapper objectMapper = new ObjectMapper();

        /** 5-2.1 method to read data **/
        // Read data from JSON and store in HashMap - benefits of storing in HashMap is that we can grab data by value over index as would be done in ArrayList.
        try {
            //System.out.println(jsonFile);
            PlanetSystem[] planetSystemArrayList = objectMapper.readValue(jsonFile, PlanetSystem[].class);

            for (PlanetSystem system : planetSystemArrayList) {
                planetSystemHashMap.put(system.getName(), system);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    /**
     * 5-2.1 HashMap methods
     **/
    @JsonIgnore
    @Override // Get all planetsystems from HashMap and stick them in new local ArrayList
    public ArrayList<PlanetSystem> getAllPlanetSystems() {
        return new ArrayList<>(planetSystemHashMap.values());
    }

    @JsonIgnore
    @Override // Input planet system name and return given planet system (picking out value from HashMap by name)
    public PlanetSystem getPlanetSystem(String planetSystemName) {
        for (PlanetSystem planetSystem : planetSystemHashMap.values()) {
            if (planetSystem.getName().equalsIgnoreCase(planetSystemName))
                return planetSystem;
        }
        return null;
    }

    /**
     * 5-.2-1d
     **/
    public static void writeToJSONFile(HashMap<String, PlanetSystem> planetSystemHashMap, String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(path), planetSystemHashMap.values().toArray());
    }


    /**
     * 4-2.6 Implementations of methods defined in UniverseRepository to get all planets and a single planet of a given system
     **/
    @JsonIgnore
    @Override
    // Get all planet objects from ArrayList planetList. Michal guided me to this solution - but I had to change it a bit to make the last task work.
    public ArrayList<Planet> getAllPlanets(String planetSystemName, String sortByParam) {
        ArrayList<Planet> target = new ArrayList<Planet>();
        target = getPlanetSystem(planetSystemName).getPlanetList();

        /** 4-2.8 Implementations of methods defined in UniverseRepository to get all planets and a single planet of a given system
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
        //TODO: removed the reference to solarOrder
           /* else if (sortByParam.equals("num")) // because sort changes the order of the list, I added the variable solarOrder to celestial bodies so that there always is an original way to sort planets by.
                target.sort(Comparator.comparing(CelestialBody::getSolarOrder));*/
        return target;
    }

    @JsonIgnore
    @Override // Input planet name and return given planet (object within arrayList)
    public Planet getSinglePlanet(String planetSystemName, String planetName) {
        for (PlanetSystem planetSystem : planetSystemHashMap.values()) {
            if (planetSystem.getName().equalsIgnoreCase(planetSystemName))
                return planetSystem.getPlanet(planetName);
        }
        return null;
    }

    /**
     * 5-2.3
     **/
    @Override
    public Planet deletePlanet(String planetSystemName, String planetName) throws IOException {
        Planet planetToDelete = new Planet(); //initialize object to be deleted
        ObjectMapper objectMapper = new ObjectMapper();
        for (PlanetSystem planetSystem : planetSystemHashMap.values()) {
            if (planetSystem.getName().equalsIgnoreCase(planetSystemName)) {// find the planet
                planetToDelete = planetSystem.getPlanet(planetName); // define found planet as planetToDelete

                PlanetSystem updatedPlanetSystem = planetSystem; // new temp system used to overwrite containing system in hashmap

                ArrayList<Planet> updatedPlanetList = updatedPlanetSystem.getPlanetList(); // new temp planet list = temp list of temp system above

                // removing object defined to be deleted from temp array list of planets
                for (int iteration = 0; iteration < updatedPlanetList.size(); iteration++) {
                    if (updatedPlanetList.get(iteration).getName() == planetToDelete.getName()) {
                        updatedPlanetList.remove(iteration);
                    }
                }

                updatedPlanetSystem.setPlanetList(updatedPlanetList); // updating the temp list with above change

                planetSystemHashMap.replace(planetSystem.getName(), updatedPlanetSystem); // replaces existing planet system with new updated temp, effectively deleting planet

                writeToJSONFile(planetSystemHashMap, "src/main/resources/planets_100.json");
                return planetToDelete;
            }
        }
        return null;
    }

    /**
     * 5-2.4
     **/
    @Override
    public Planet createPlanet(String planetSystemName, String planetName, double radius, double mass, double semiMajorAxis, double eccentricity, double orbitalPeriod, String pictureUrl) throws IOException {
        Planet planetToCreate = new Planet(); //initialize object to be created
        ObjectMapper objectMapper = new ObjectMapper();
        for (PlanetSystem planetSystem : planetSystemHashMap.values()) {
            if (planetSystem.getName().equalsIgnoreCase(planetSystemName)) {// define containing system

                PlanetSystem updatedPlanetSystem = planetSystem; // new temp system used to overwrite containing system in hashmap
                ArrayList<Planet> updatedPlanetList = updatedPlanetSystem.getPlanetList(); // new temp planet list = temp list of temp system above

                // possibly a better way to do this. Just defining parameter data as new planet data
                planetToCreate.setName(planetName);
                planetToCreate.setRadius(radius);
                planetToCreate.setSemiMajorAxis(semiMajorAxis);
                planetToCreate.setEccentricity(eccentricity);
                planetToCreate.setOrbitalPeriod(orbitalPeriod);
                planetToCreate.setPictureUrl(pictureUrl);

                updatedPlanetList.add(planetToCreate);

                updatedPlanetSystem.setPlanetList(updatedPlanetList); // updating the temp list with above change

                planetSystemHashMap.replace(planetSystem.getName(), updatedPlanetSystem); // replaces existing planet system with new updated temp, effectively deleting planet

                writeToJSONFile(planetSystemHashMap, "src/main/resources/planets_100.json");
                return null;
            }

        }
        return null;
    }

    /**
     * 5-2.5
     **/
    @Override
    public Planet updatePlanet(String planetSystemName, String planetName, String PlanetNameNew, double radius, double mass, double semiMajorAxis, double eccentricity, double orbitalPeriod, String pictureUrl) throws IOException {
        for (PlanetSystem planetSystem : planetSystemHashMap.values()) {
            if (planetSystem.getName().equalsIgnoreCase(planetSystemName)) {// define containing system //TODO: Find system

                PlanetSystem updatedPlanetSystem = planetSystem; // new temp system used to overwrite containing system in hashmap

                ArrayList<Planet> updatedPlanetList = updatedPlanetSystem.getPlanetList(); // new temp planet list = temp list of temp system above

                //TODO: is != bad practice in Java?  How can I make reverse equals/equals for non string

                // Checking for changes to existing planet and effecting change to temp planet if change exists
                for (int iteration = 0; iteration < updatedPlanetList.size(); iteration++) {
                    if (updatedPlanetList.get(iteration).getName().equals(planetName)) {
                        if (updatedPlanetList.get(iteration).getRadius() != radius) {
                            updatedPlanetList.get(iteration).setRadius(radius);
                        }
                        if (updatedPlanetList.get(iteration).getMass() != mass) {
                            updatedPlanetList.get(iteration).setMass(mass);
                        }
                        if (updatedPlanetList.get(iteration).getSemiMajorAxis() != semiMajorAxis) {
                            updatedPlanetList.get(iteration).setSemiMajorAxis(semiMajorAxis);
                        }
                        if (updatedPlanetList.get(iteration).getEccentricity() != eccentricity) {
                            updatedPlanetList.get(iteration).setEccentricity(eccentricity);
                        }
                        if (updatedPlanetList.get(iteration).getOrbitalPeriod() != orbitalPeriod) {
                            updatedPlanetList.get(iteration).setOrbitalPeriod(orbitalPeriod);
                        }
                        if (updatedPlanetList.get(iteration).getPictureUrl() != pictureUrl) {
                            updatedPlanetList.get(iteration).setPictureUrl(pictureUrl);
                        }
                        if (!updatedPlanetList.get(iteration).getName().equals(PlanetNameNew)) {
                            updatedPlanetList.get(iteration).setName(PlanetNameNew);
                        }
                    }
                }

                updatedPlanetSystem.setPlanetList(updatedPlanetList); // updating the temp list with above change

                planetSystemHashMap.replace(planetSystem.getName(), updatedPlanetSystem); // replaces existing planet system with new updated temp, effectively deleting planet

                writeToJSONFile(planetSystemHashMap, "src/main/resources/planets_100.json");
                return null;
            }
        }
        return null;
    }
    //TODO:  Reset the planet resources
}
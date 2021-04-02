package no.hiof.erikvs.repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.hiof.erikvs.model.CelestialBody;
import no.hiof.erikvs.model.Planet;
import no.hiof.erikvs.model.PlanetSystem;

import java.io.IOException;
import java.util.*;
import java.io.File;

public class UniverseJSONRepository implements UniverseRepository{

    // Instantiate HashMap to store JSON data in.
    public HashMap <String, PlanetSystem> planetSystemHashMap = new HashMap<String, PlanetSystem>();

    public UniverseJSONRepository(File jsonFile)  {

        // Creating instance of objectmapper class, used to read and write JSON.
        ObjectMapper objectMapper = new ObjectMapper();

        /** 5-2.1 method to read data **/
        // Read data from JSON and store in HashMap - benefits of storing in HashMap is that we can grab data by value over index as would be done in ArrayList.
        try {
            //System.out.println(jsonFile);
            PlanetSystem[] planetSystemArrayList = objectMapper.readValue(jsonFile, PlanetSystem[].class);

            for (PlanetSystem system : planetSystemArrayList){
                planetSystemHashMap.put(system.getName(), system);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        /*  // Initial attempt at 2.1
          try {
                ArrayList<PlanetSystem> Galaxy = objectmapper.readValue("planets_100.json", PlanetSystem.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }*/

            // Defining HashMap which will contain planetsystems from JSON and fill it with objects from JSON - https://stackoverflow.com/questions/21544973/convert-jsonobject-to-map/21545023 convert jsonObject to Map
           /* try {


                HashMap<Integer, PlanetSystem> planetSystemHashMap = objectmapper.readValue(Paths.get("src/main/resources/planets_100.json").toFile(), HashMap.class); //new ObjectMapper().readValue(new File("src\\main\\resources\\planets_100.json", String.valueOf(HashMap.class)));

                // converting hashmap to ArrayList
                Collection<PlanetSystem> values = planetSystemHashMap.values();
                List<PlanetSystem> planetSystemArrayList = new ArrayList<PlanetSystem>(values);

                for (PlanetSystem system : planetSystemArrayList){
                Galaxy.add(system);
                }
                System.out.println(Galaxy.get(5));

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }*/

        /* Set<Entry<Integer, PlanetSystem>> entrySet = planetSystemHashMap.entrySet();
            List<Map.Entry<Integer, PlanetSystem> >planetSystemArrayList = new ArrayList<Map.Entry<Integer, PlanetSystem> >(entrySet);
                */


    }

    /** 5-2.1 HashMap methods **/
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

        /** 5-.2-1d**/
        public static void writeToJSONFile(HashMap <String, PlanetSystem> planetSystemHashMap, String path) throws IOException {
            ObjectMapper objectMapper = new ObjectMapper();

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(path), planetSystemHashMap);
        }



        /** 4-2.6 Implementations of methods defined in UniverseRepository to get all planets and a single planet of a given system
         **/
        @JsonIgnore
        @Override // Get all planet objects from ArrayList planetList. Michal guided me to this solution - but I had to change it a bit to make the last task work.
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
            for (PlanetSystem planetSystem : planetSystemHashMap.values()){
                if (planetSystem.getName().equalsIgnoreCase(planetSystemName))
                    return planetSystem.getPlanet(planetName);
            }
            return null;
        }

    @Override
    public Planet addPlanet(String planetName, double radius, double mass, double SemiMajorAxis, double Eccentricity, double orbitalPeriod, String pictureUrl) {
        return null;
    }

    @Override
    public Planet editPlanet() {
        return null;
    }

    @Override
    public Planet deletePlanet() {
        return null;
    }
}

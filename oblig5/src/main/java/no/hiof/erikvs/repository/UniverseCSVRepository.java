
package no.hiof.erikvs.repository;

import no.hiof.erikvs.model.CelestialBody;
import no.hiof.erikvs.model.Planet;
import no.hiof.erikvs.model.PlanetSystem;
import no.hiof.erikvs.model.Star;
import java.io.*;
import java.util.*;

public class UniverseCSVRepository implements UniverseRepository{

    // source CSV location
    File source = new File("src/main/resources/planets_100.csv");

    // Create empty list that will contain objects read from CSV which are created below
    ArrayList<PlanetSystem> readCSVList = new ArrayList<>();

    public UniverseCSVRepository(File csvFile) {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(source))) {
            String line;

            PlanetSystem tempPlanetSystem = new PlanetSystem();
            ArrayList<Planet> tempPlanetList = new ArrayList<>();

            /**Each rotation of the while corresponds to one line of CSV.
             * If the storage does not contain a given planetsystem, a full rotation is run
             * creating both the system, star and first planet + the storage hierarchy for current system
             * the else runs if the system exists, and fills it with remaining planets.
             * When a new systemname is read, a new if rotation is started.
             * **/

            while ((line = bufferedReader.readLine()) != null) { // reads each line of csv until there are none left
                String[] values = line.split(";"); // splits values on given string
                List<String> list = Arrays.asList(values); //storing strings in list to work with easier

                if (!list.contains(tempPlanetSystem.getName())) {
                    if (tempPlanetSystem.getName() != null) { // if current planetsystem is empty do this
                        tempPlanetSystem.setPlanetList(tempPlanetList); // define current planetlist as planetlist for current system
                        readCSVList.add(tempPlanetSystem); // add current planetsystem to storage
                    }

                    // System.out.println("****************************************************************");

                    tempPlanetSystem = new PlanetSystem(values[0], values[1]); // create planetsystem out of CSV values
                    // System.out.println("Creating new system: " + tempPlanetSystem.getName());

                    Star tempStar = new Star(values[2], Double.parseDouble(values[3]), Double.parseDouble(values[4]), Double.parseDouble(values[5]), values[6]); // create centerstar out of CSV values
                    // System.out.println("Creating new star: " + tempStar.getName());
                    tempPlanetSystem.setCenterStar(tempStar); // define current star as centerstar for current planetsystem

                    Planet tempPlanet = new Planet(values[7], Double.parseDouble(values[8]), Double.parseDouble(values[9]), Double.parseDouble(values[10]), Double.parseDouble(values[11]), Double.parseDouble(values[12]), values[13], tempStar); // create planet out of CSV values
                    //  System.out.println("Creating new plant: " + tempPlanet);
                    tempPlanetList.add(tempPlanet); // add current planet to planetlist

                } else {
                    Star tempStar = new Star(values[2], Double.parseDouble(values[3]), Double.parseDouble(values[4]), Double.parseDouble(values[5]), values[6]); // creating local star as variable to make planet object in next line
                    Planet tempPlanet = new Planet(values[7], Double.parseDouble(values[8]), Double.parseDouble(values[9]), Double.parseDouble(values[10]), Double.parseDouble(values[11]), Double.parseDouble(values[12]), values[13], tempStar);
                    //  System.out.println("Creating new star: " + tempPlanet);
                    tempPlanetList.add(tempPlanet);
                }

            }
            tempPlanetSystem.setPlanetList(tempPlanetList);
            readCSVList.add(tempPlanetSystem);
           /* System.out.println("****************************************************************");
            System.out.println("Number of systems created: " + readCSVList.size());
            System.out.println("Name of first system: " + readCSVList.get(0).getName());
            System.out.println("First system star: " + readCSVList.get(0).getCenterStar());
            System.out.println("First system plant: " + readCSVList.get(0).getPlanetList().get(0));*/

        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }


    @Override
    public ArrayList<PlanetSystem> getAllPlanetSystems() {
        return readCSVList;
    }

    @Override
    public PlanetSystem getPlanetSystem(String planetSystemName) {
        PlanetSystem target = null;
        for (int i = 0; i < readCSVList.size(); i++) { // loops through the arraylist and checks for name similarity
            if (readCSVList.get(i).getName().equalsIgnoreCase(planetSystemName)) {
                target = readCSVList.get(i);
                break;
            }
        }
        return target; //returns null if planet name is not in list. Not best practice, but ok for now. //TODO: maybe throw NoSuchElementException?
    }


    //TODO there is an issue with this method where all planets are a part of every system
    @Override
    public ArrayList<Planet> getAllPlanets(String planetSystemName, String sortByParam) {
        ArrayList<Planet> target = new ArrayList<Planet>();
        target = getPlanetSystem(planetSystemName).getPlanetList();
        for (int i = 0; i < readCSVList.size(); i++) { // loops through the arraylist and checks for name similarity
            if (sortByParam.equals("name"))
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
           //else if (sortByParam.equals("num"))
               // target.sort(Comparator.comparing(CelestialBody::getSolarOrder));

        }
        return target;
    }

    @Override
    public Planet getSinglePlanet(String planetSystemName, String planetName) {
        ArrayList<Planet> targetList = null;
        Planet targetPlanet = null;
        for (int i = 0; i < readCSVList.size(); i++) {
            if (readCSVList.get(i).getName().equalsIgnoreCase(planetSystemName))
                targetList = readCSVList.get(i).getPlanetList();
            for (int y = 0; y < targetList.size(); y++)
                if (targetList.get(y).getName().equalsIgnoreCase(planetName))
                    targetPlanet =targetList.get(y);
        }
        return targetPlanet;
}

}


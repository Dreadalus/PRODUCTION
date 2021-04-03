package no.hiof.erikvs;


import no.hiof.erikvs.model.Planet;
import no.hiof.erikvs.model.PlanetSystem;
import no.hiof.erikvs.model.Star;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static no.hiof.erikvs.repository.UniverseCSVRepository.writeToCSVFile;

public class Main {


    ArrayList<PlanetSystem> readCSVList = new ArrayList<>();

    public static void main(String[] args) {

        // source CSV location
        File source = new File("src/main/resources/planets_100.csv");
        File source2 = new File("src/main/resources/planets_0.csv");

        // Create empty list that will contain objects read from CSV which are created below
        ArrayList<PlanetSystem> readCSVList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(source))) {
            String line;

            PlanetSystem tempPlanetSystem = new PlanetSystem();
            ArrayList<Planet> tempPlanetList = new ArrayList<>();

            /**Each rotation of the while corresponds to one line of CSV.
             * If the storage does not contain a given planet system, a full rotation is run
             * creating both the system, star and first planet + the storage hierarchy for current system
             * the else runs if the system exists, and fills it with remaining planets.
             * When a new system name is read, a new if rotation is started.**/
            while ((line = bufferedReader.readLine()) != null) { // reads each line of csv until there are none left
                String[] values = line.split(";"); // splits values on given string
                List<String> list = Arrays.asList(values); //storing strings in list to work with easier

                if (!list.contains(tempPlanetSystem.getName())) {
                    if (tempPlanetSystem.getName() != null) { // if current planetsystem is empty do this
                        tempPlanetSystem.setPlanetList(tempPlanetList); // define current planetlist as planetlist for current system
                        readCSVList.add(tempPlanetSystem); // add current planetsystem to storage
                        tempPlanetList = new ArrayList<>(); // resets the temp list
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
          System.out.println("****************************************************************");
            System.out.println("Number of systems created: " + readCSVList.size());
            System.out.println("Name of first system: " + readCSVList.get(0).getName());
            System.out.println("First system star: " + readCSVList.get(0).getCenterStar());
            System.out.println("First system plant: " + readCSVList.get(0).getPlanetList().get(0));

            System.out.println(readCSVList);

            //writeToCSVFile(readCSVList, source2);

            //System.out.println(source2);

        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}

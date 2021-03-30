package no.hiof.erikvs;

import no.hiof.erikvs.model.Planet;
import no.hiof.erikvs.model.PlanetSystem;
import no.hiof.erikvs.model.Star;
import static java.util.Objects.isNull;
import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // my source CSV location
        File source = new File("src/main/resources/planets_100.csv");

        // Create empty list that will contain objects read from CSV which are created below
        ArrayList<PlanetSystem> readCSVList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(source))) {
            String line;

            PlanetSystem tempPlanetSystem = new PlanetSystem(null, null);
            ArrayList<Planet> tempPlanetList = new ArrayList<>();
            Star tempStar = new Star();
            Planet tempPlanet = new Planet();

            // main loop for creating local arraylist from csv content
            while ((line = bufferedReader.readLine()) != null) { // reads each line of csv until there are none left
                String[] values = line.split(";"); // splits values on given string

                System.out.println(values[0]);

                /*if (tempPlanetSystem.getName().equals(null)) { //if tempPlanetSystem is empty do this //TODO: Need to find out how to check if tempPlanetSystem is null or not.
                    tempPlanetSystem = new PlanetSystem(values[0], values[1]);*/
                if ("Solar System" == values[0] ) {// if the planet system doesnt exist in temporary PlanetSystem ArrayList, do this
                    // the above line also restarts the loop because if there is a system with X name, the directly below if runs and dumps the existing temp into storage
                    tempPlanetSystem.setPlanetList(tempPlanetList); // dumps the filled planetList into planetSystem

                    readCSVList.add(tempPlanetSystem);

                    tempPlanetSystem = new PlanetSystem(values[0], values[1]);
                    //values[0].equals(tempPlanetSystem.getName())
                } else if (tempPlanetSystem.getName() != values[0]) {// if the planet system exists, do this loop
                    if (!values[2].equals(tempStar.getName())) // checks if star exists and creates it if not
                    {
                        tempStar = new Star(values[2], values[3], values[4], values[5], values[6]); // creates star
                        tempPlanetSystem.setCenterStar(tempStar); // adds star to list
                        //!values[7].equals(tempPlanet.getName())
                    } else if (tempPlanet.getName() == values[7]) { // checks if planet exists and creates it if not
                        tempPlanet = new Planet(values[7], values[8], values[9], values[10], values[11], values[12], values[13]); // create planet
                        tempPlanetList.add(tempPlanet); // add planet to temporary arraylist of planets
                    }

                }
                System.out.println(readCSVList);
            }

        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
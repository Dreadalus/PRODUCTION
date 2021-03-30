package no.hiof.erikvs;

import no.hiof.erikvs.model.Planet;
import no.hiof.erikvs.model.PlanetSystem;
import no.hiof.erikvs.model.Star;

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

            // main loop for creating local arraylist from csv content
            while ((line = bufferedReader.readLine()) != null) { // reads each line of csv until there are none left
                String[] values = line.split(";"); // splits values on given string

                if (!readCSVList.contains(values[0]))
                {// checking if name of planetsystem is in readCSVList, if it isnt new planetsystem is created
                    PlanetSystem planetSystem = new PlanetSystem(values[0], values[1]); //TODO: so all systems are called planetSystem? Im sure this crashes the fucking code.
                    ArrayList<Planet> planetList = new ArrayList<>();
                    planetSystem.setPlanetList(planetList); // sets planetlist for newly created system
                    readCSVList.add(planetSystem); }
                else if (!readCSVList.contains(values[2]))
                { // checking if name of centerstar is in readCSVList, if it isnt new star is created
                    Star star = new Star(values[2], values[3], values[4], values[5], values[6]);
                    planetSystem.setCenterStar(star);  //TODO: cant add to system contained locally within loop above?
                } else if (!readCSVList.contains(values[7]))
                {
                    Planet planet = new Planet(values[7], values[8], values[9], values[10], values[11], values[12], values[13]);
                    planetList.add(planet); //TODO: cant add to planet contained locally within loop above?
                }
            }
        }
        catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
}
}
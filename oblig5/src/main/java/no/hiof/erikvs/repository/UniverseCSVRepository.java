/* //TODO: Whole thing has been block commented
package no.hiof.erikvs.repository;
import no.hiof.erikvs.model.Planet
import no.hiof.erikvs.model.Planet;
import no.hiof.erikvs.model.PlanetSystem;

import java.io.*;
import java.util.ArrayList;

public class UniverseCSVRepository {
    File source = new File("src/main/resources/planets_100.csv");

    public UniverseCSVRepository(File csvFile){

        ArrayList<PlanetSystem> readCSVList = new ArrayList<>(); // Create empty list that will contain objects


        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(source)))     {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(";");

                //TODO: First define planet system and star. IF value 0 of line == value 0 of planet system, make planet object and add it to system
                //TODO repeat this within the while loop

                // determine how many lines of CSV you are working with and create planet systems
                // then fill systems with respective planets (this is done in the video)

                //loop 1 define planet system
                PlanetSystem planetsystem = new PlanetSystem(values[0],values[1]);//I want a centerstar & list of planets here
                // if planetsystem has 0 == True, check value 3 if not true add star, if 3 == True, check value 7 and add planet, if 7 == TRUE next.

               //loop 2 fill planets into list of planets and then add this list to respective planet system.
                Planet planet = new Planet(values[])
                readCSVList.add(planetsystem);

                // I need an empty list in the planetsystem to dump my planets into...

               */
/*  read a planetsystem object in from csv where
               0 is systemname
               1 is pictureURL - planetlist
               2 is star name
               3 is star radius
               4 is star mass
               5 is star effective temp
               6 is star picture - star defined as centerCelestialBody
               7 is planet name
               8 is planet radius
               9 is planet mass
               10 is semimajor
               11 is eccentricity
               12 is orbital period
               13 is planet picture - planet object
               *//*




            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
*/

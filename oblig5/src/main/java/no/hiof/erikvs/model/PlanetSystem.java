package no.hiof.erikvs.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class PlanetSystem extends ArrayList<PlanetSystem> implements Comparable<PlanetSystem>{
    //Defining member variables
    private String name, pictureUrl;
    private Star centerStar;
    private ArrayList<Planet> planetList;

    // Empty constructor to use for conversion from JSON
    public PlanetSystem(){
    }

    // Defining constructor
    public PlanetSystem(String name, String pictureUrl) {
        this.name = name;
        this.pictureUrl = pictureUrl;
    }

    /**2.1 implement Comparable in PlanetSystem**/
   @Override// method for comparing planetsystems by centerstar mass and radius
    public int compareTo(PlanetSystem otherPlanetSystem) {
        int returnvalue = (int) (this.centerStar.MassInKg() - otherPlanetSystem.centerStar.MassInKg());

        if (returnvalue == 0)
            return (int) (this.centerStar.RadiusInKm() - otherPlanetSystem.centerStar.RadiusInKm());

        return returnvalue;
    }

    //methods for returning largest and smallest planets
    public String biggestPlanet() {
        Planet biggest = planetList.get(0);
        for (int i = 0; i < planetList.size(); i++) {
            if (planetList.get(i).getRadius() > biggest.getRadius()) {
                biggest = planetList.get(i);
            } else if (planetList.get(i).getRadius() == biggest.getRadius()) {
                if (planetList.get(i).getMass() > biggest.getMass())
                    biggest = planetList.get(i);
            }
        }
        return biggest.getName();
    }

    public String smallestPlanet() {
        Planet smallest = planetList.get(0);
        for (int i = 0; i < planetList.size(); i++) {
            if (planetList.get(i).getRadius() < smallest.getRadius()) {
                smallest = planetList.get(i);
            } else if (planetList.get(i).getRadius() == smallest.getRadius()) {
                if (planetList.get(i).getMass() < smallest.getMass())
                    smallest = planetList.get(i);
            }
        }
        return smallest.getName();
    }

    // return planet from array based on name as string input
    public Planet getPlanet(String name) {
        Planet target = null;
        for (int i = 0; i < planetList.size(); i++) {
            if (planetList.get(i).getName().equalsIgnoreCase(name)) {
                target = planetList.get(i);
                break;
            }
        }
        return target; //returns null if planet name is not in list. Not best practice, but ok for now.
    }

    // get/set for instance variable name
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // get/set for instance variable centerStar
    @JsonProperty("centerStar")
    public Star getCenterStar() {
        return centerStar;
    }

    public void setCenterStar(Star centerStar) {
        this.centerStar = centerStar;
    }

    // get/set for ArrayList planets
    @JsonProperty("planets")
    public ArrayList<Planet> getPlanetList() {
        return planetList;
    }

    public void setPlanetList(ArrayList<Planet> planetList) {
        this.planetList = planetList;
    }

    // get/set for pictureUrl
    @JsonProperty("pictureURL")
    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @Override
    public String toString() {
        return name + " is the coolest place I have ever been. " + centerStar.getName() + " is very hot. " + planetList.get(0).getName() + " is a planet of this planet system.";
    }
}
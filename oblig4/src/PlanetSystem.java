import java.util.ArrayList;

public class PlanetSystem {
    //Defining member variables
    private String name;
    private Star centerStar;
    private ArrayList<Planet> planetList;

    // Defining constructor
    public PlanetSystem(String name) {
        this.name = name;
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
    Planet getPlanet(String name) {
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
    public String getName() {
        return name;
    }

    public void getName(String name) {
        this.name = name;
    }

    // get/set for instance variable centerStar
    public Star getCenterStar() {
        return centerStar;
    }

    public void setCenterStar(Star centerStar) {
        this.centerStar = centerStar;
    }

    // get/set for ArrayList planets
    public ArrayList<Planet> getPlanetList() {
        return planetList;
    }

    public void setPlanetList(ArrayList<Planet> planetList) {
        this.planetList = planetList;
    }

    @Override
    public String toString() {
        return name + " is the coolest place I have ever been. " + centerStar.getName() + " is very hot. " + planetList.get(0).getName() + " is a planet of this planet system.";
    }
}
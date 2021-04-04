package no.hiof.erikvs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class NaturalSatellite extends CelestialBody {

    private double semiMajorAxis, eccentricity;
    private double orbitalPeriod;
    private CelestialBody centralCelestialBody;
    public static final double ONE_JUPITER_MASS = 1.898E27;
    public static final double ONE_JUPITER_RADIUS = 71492;
    public static final double GRAVITATIONAL_CONSTANT = 0.0000000000667408;
    public static final double ONE_ASTRONOMICAL_UNITS = 149597871;

        // Empty constructor to use for conversion from JSON
        public NaturalSatellite(){}

        //TODO: Removed reference to solarOrder
        public NaturalSatellite(/*int solarOrder, */String name, double radius, double mass, double semiMajorAxis, double eccentricity, double orbitalPeriod, String pictureUrl, CelestialBody centralCelestialBody){
        super(/*solarOrder,*/ name, radius, mass, pictureUrl);
        this.semiMajorAxis = semiMajorAxis;
        this.eccentricity = eccentricity;
        this.orbitalPeriod = orbitalPeriod;
        this.pictureUrl = pictureUrl;
        this.centralCelestialBody = centralCelestialBody;

    }

    /**
     * Kepler's orbit formula which returns distance in kilometers by multiplying the standard formula by 1 AU.
     * */
    @JsonIgnore
    double distanceToCentralBody(double degrees){
        double radians = Math.toRadians(degrees);
        double keplers = ((semiMajorAxis*(1-Math.pow(eccentricity,2)))/(1+eccentricity*Math.cos(radians)));
        return keplers*ONE_ASTRONOMICAL_UNITS;
    }

    // Alternate use of kepler's orbit formula taking radians directly instead of converting from degrees.
    @JsonIgnore
    double distanceToCentralBodyV2(double radians){
        double keplers = ((semiMajorAxis*(1-Math.pow(eccentricity,2)))/(1+eccentricity*Math.cos(radians)));
        return keplers*ONE_ASTRONOMICAL_UNITS;
    }

    @JsonIgnore
    double orbitingVelocity(double distance){
        return (Math.sqrt((GRAVITATIONAL_CONSTANT*centralCelestialBody.MassInKg()/(distance/1000)))); //
    }

    // get/set for instance variable semiMajorAxis
    @JsonProperty("semiMajorAxis")
    public double getSemiMajorAxis() {
        return semiMajorAxis;
    }

    public void setSemiMajorAxis(double semiMajorAxis) {
        this.semiMajorAxis = semiMajorAxis;
    }

    // get/set for instance variable eccentricity
    @JsonProperty("eccentricity")
    public double getEccentricity() {
        return eccentricity;
    }

    public void setEccentricity(double eccentricity) {
        this.eccentricity = eccentricity;
    }

    // get/set for instance variable orbitalPeriod
    @JsonProperty("orbitalPeriod")
    public double getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public void setOrbitalPeriod(double orbitalPeriod) {
        this.orbitalPeriod = orbitalPeriod;
    }

    // get/set for instance variable centralCelestialBody
    @JsonProperty("centralCelestialBody")
    public CelestialBody getCentralCelestialBody() {
        return centralCelestialBody;
    }

    public void setCentralCelestialBody(CelestialBody centralCelestialBody) {
        this.centralCelestialBody = centralCelestialBody;}

    // get/set for instance variable pictureUrl
    public String getPictureUrl() {
        return pictureUrl; }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl; }

}

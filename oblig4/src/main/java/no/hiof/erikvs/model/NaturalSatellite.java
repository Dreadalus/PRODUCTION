package no.hiof.erikvs.model;

public abstract class NaturalSatellite extends CelestialBody {

    private double semiMajorAxis, eccentricity;
    private int orbitalPeriod;
    private CelestialBody centralCelestialBody;
    private String pictureUrl;
    public static final double ONE_JUPITER_MASS = 1.898E27;
    public static final double ONE_JUPITER_RADIUS = 71492;
    public static final double GRAVITATIONAL_CONSTANT = 0.0000000000667408;
    public static final double ONE_ASTRONOMICAL_UNITS = 149597871;

    public NaturalSatellite(String name, double radius, double mass, double semiMajorAxis, double eccentricity, int orbitalPeriod, String pictureUrl, CelestialBody centralCelestialBody){
        super(name, radius, mass, pictureUrl);
        this.semiMajorAxis = semiMajorAxis;
        this.eccentricity = eccentricity;
        this.orbitalPeriod = orbitalPeriod;
        this.pictureUrl = pictureUrl;
        this.centralCelestialBody = centralCelestialBody;

    }

    /**
     * Kepler's orbit formula which returns distance in kilometers by multiplying the standard formula by 1 AU.
     * */

    double distanceToCentralBody(double degrees){
        double radians = Math.toRadians(degrees);
        double keplers = ((semiMajorAxis*(1-Math.pow(eccentricity,2)))/(1+eccentricity*Math.cos(radians)));
        return keplers*ONE_ASTRONOMICAL_UNITS;
    }

    // Alternate use of kepler's orbit formula taking radians directly instead of converting from degrees.
    double distanceToCentralBodyV2(double radians){
        double keplers = ((semiMajorAxis*(1-Math.pow(eccentricity,2)))/(1+eccentricity*Math.cos(radians)));
        return keplers*ONE_ASTRONOMICAL_UNITS;
    }


    double orbitingVelocity(double distance){
        return (Math.sqrt((GRAVITATIONAL_CONSTANT*centralCelestialBody.MassInKg()/(distance/1000)))); //
    }

    // getters and setters
    public double getSemiMajorAxis() {
        return semiMajorAxis;
    }

    public double getEccentricity() {
        return eccentricity;
    }

    public int getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public CelestialBody getCentralCelestialBody() {
        return centralCelestialBody;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setSemiMajorAxis(double semiMajorAxis) {
        this.semiMajorAxis = semiMajorAxis;
    }

    public void setEccentricity(double eccentricity) {
        this.eccentricity = eccentricity;
    }

    public void setOrbitalPeriod(int orbitalPeriod) {
        this.orbitalPeriod = orbitalPeriod;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public void setCentralCelestialBody(CelestialBody centralCelestialBody) {
        this.centralCelestialBody = centralCelestialBody;

    }
}

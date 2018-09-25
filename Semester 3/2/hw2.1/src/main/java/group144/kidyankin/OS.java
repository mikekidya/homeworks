package group144.kidyankin;

/** Class realising the OS of the computer */
public class OS {

    private String name;
    private double probabilityOfInfection;

    /**
     * Creates the new OS with given name and probability of infection
     *
     * @param name the name os OS
     * @param probabilityOfInfection probability of infection of OS
     */
    public OS(String name, double probabilityOfInfection) {
        this.name = name;
        this.probabilityOfInfection = probabilityOfInfection;
    }

    /**
     * Returns the name of OS
     * @return the name of OS
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the probability of infection of OS
     * @return the probability of infection of OS
     */
    public double getProbabilityOfInfection() {
        return probabilityOfInfection;
    }

}

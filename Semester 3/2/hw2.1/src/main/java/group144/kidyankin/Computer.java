package group144.kidyankin;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/*** Class realizing computer in network
 *   It shout be used only with Network
 */
public class Computer {

    private String name;
    private OS osType;
    private List<Computer> connectedComputers;
    private boolean isInfected = false;
    private boolean hasInfectedConnectedComputer = false;

    /**
     * Creates new clear computer without any connections
     *
     * @param name name of the computer
     * @param osType os of the computer
     */
    public Computer(String name, OS osType) {
        this.osType = osType;
        this.connectedComputers = new LinkedList<>();
        this.name = name;
    }

    /**
     * Tries to infect computer if it is possible
     * @return true if infection was successful
     */
    public boolean tryInfect() {
        if (hasInfectedConnectedComputer) {
            Random generator = new Random();
            if (generator.nextFloat() < osType.getProbabilityOfInfection()) {
                isInfected = true;
                return true;
            }
        }
        return false;
    }

    /**
     * Adds connection from this computer to other
     * @param other computer connection will be added
     */
    public void addConnected(Computer other) {
        connectedComputers.add(other);
    }

    /**
     * Returns true if computer is infected
     * @return true if computer is infected
     */
    public boolean isInfected() {
        return isInfected;
    }

    /**
     * Set the computer infection state manually
     * @param infected true if set infected
     */
    public void setInfected(boolean infected) {
        isInfected = infected;
    }

    /**
     * Returns the list of connected computers
     * @return the list of connected computers
     */
    public List<Computer> getConnectedComputers() {
        return connectedComputers;
    }

    /**
     * Indicates that computer has infected connected another computer
     */
    public void addInfectedConnected() {
        this.hasInfectedConnectedComputer = true;
    }

    /**
     * Returns true if computer has infected connected
     * @return true if computer has infected connected
     */
    public boolean hasInfectedConnectedComputer() {
        return hasInfectedConnectedComputer;
    }

    /**
     * Returns the name of the computer
     * @return the name of the computer
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + ": " + (isInfected() ? "Infected" : "Not Infected");
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Computer) && this.name.equals(((Computer) obj).name);
    }
}

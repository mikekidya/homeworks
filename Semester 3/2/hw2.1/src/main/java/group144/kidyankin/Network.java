package group144.kidyankin;

import java.util.LinkedList;
import java.util.List;

/** Class modelling the network of computers */
public class Network {

    private List<Computer> computers;
    private List<Computer> couldBeInfected;
    private RandomGenerator randomGenerator;

    /**
     * Creates empty network
     */
    public Network(RandomGenerator randomGenerator) {
        computers = new LinkedList<>();
        couldBeInfected = new LinkedList<>();
        this.randomGenerator = randomGenerator;
    }

    /**
     * Set the computer in network infected
     * @param infected computer to be set infected
     */
    public void setFirstInfected(Computer infected) {
        for (Computer comp : computers) {
            if (comp.equals(infected)) {
                comp.setInfected(true);
                couldBeInfected.addAll(addNewProbablyInfected(comp));
                break;
            }
        }
    }

    /**
     * Adds computer to network
     * @param computer to be added
     */
    public void addComputer(Computer computer) {
        computers.add(computer);
    }

    /**
     * Returns the list of computers in this network
     * @return the list of computers in this network
     */
    public List<Computer> getComputers() {
        return computers;
    }

    /**
     * Modelling the step of infection in this network
     */
    public void modelStep() {
        List<Computer> listOfNewInfected = new LinkedList<>();
        List<Computer> newProbablyInfected = new LinkedList<>();
        for (Computer computer : couldBeInfected) {
            if (computer.tryInfect(randomGenerator)) {
                listOfNewInfected.add(computer);
                newProbablyInfected.addAll(addNewProbablyInfected(computer));
            }
        }
        couldBeInfected.removeAll(listOfNewInfected);
        couldBeInfected.addAll(newProbablyInfected);
    }

    private List<Computer> addNewProbablyInfected(Computer computer) {
        List<Computer> result = new LinkedList<>();
        for (Computer connected : computer.getConnectedComputers()) {
            if (!connected.hasInfectedConnectedComputer() && !connected.isInfected()) {
                connected.addInfectedConnected();
               result.add(connected);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Computer computer : computers) {
            builder.append(computer);
            builder.append('\n');
        }
        return builder.toString();
    }

}

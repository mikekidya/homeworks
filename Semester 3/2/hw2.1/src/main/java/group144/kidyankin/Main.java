package group144.kidyankin;

import java.util.*;

public class Main {

    public static List<OS> getOperationSystems(Scanner in) {
        System.out.print("Enter the number of operation systems: ");
        int numberOfCOS = in.nextInt();
        List<OS> osList = new ArrayList<>();
        for (int i = 1; i <= numberOfCOS; i++) {
            System.out.print("Enter the name of " + i + " OS: ");
            String name = in.next();
            System.out.print("Enter the probability on infection: ");
            osList.add(new OS(name, in.nextDouble()));
        }
        return osList;
    }

    public static void addConnections(Network network, Scanner in) {
        for (Computer inputComputer : network.getComputers()) {
            for (Computer outputComputer : network.getComputers()) {
                if (!inputComputer.equals(outputComputer)) {
                    System.out.print("Enter Y if there is a connection " + inputComputer.getName()
                            + " -> " + outputComputer.getName() + ":");
                    if (in.next().equals("Y")) {
                        inputComputer.addConnected(outputComputer);
                    }
                }
            }
        }
    }

    public static void chooseFirst(Network network, Scanner in) {
        System.out.print("Enter the name of infected computer: ");
        boolean isChosen = false;
        String nameOfInfected = in.next();
        for (Computer computer : network.getComputers()) {
            if (computer.getName().equals(nameOfInfected)) {
                network.setFirstInfected(computer);
                isChosen = true;
            }
        }
        if (!isChosen) {
            System.out.println("WRONG INPUT");
            System.exit(-1);
        }
    }

    public static void addComputers(Network network, List<OS> osList, Scanner in) {
        System.out.print("Enter the number of computers: ");
        int numberOfComputers = in.nextInt();
        for(int i = 1; i <= numberOfComputers; i++) {
            System.out.print("Enter the name of " + i + " computer: ");
            String name = in.next();
            System.out.print("Enter the name of " + i + " computer OS: ");
            String osName = in.next();
            boolean isAdded = false;
            for (OS os : osList) {
                if (os.getName().equals(osName)) {
                    network.addComputer(new Computer(name, os));
                    isAdded = true;
                }
            }
            if (!isAdded) {
                System.out.println("WRONG INPUT");
                System.exit(-1);
            }
        }
    }

    public static Network getNetwork(Scanner in) {
        List<OS> osList = getOperationSystems(in);

        Network network = new Network();
        addComputers(network, osList, in);
        addConnections(network, in);
        chooseFirst(network, in);
        return network;
    }

    public static void main(String[] args) {
        System.out.println("VIRUS INFECTION MODELLING");
        Scanner in = new Scanner(System.in);
        Network network = getNetwork(in);

        String result = "N";
        while ("N".equals(result)) {
            network.modelStep();
            System.out.println(network);
            System.out.print("Enter N to model next step");
            result = in.next();
        }
    }
}

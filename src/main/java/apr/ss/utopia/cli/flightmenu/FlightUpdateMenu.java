package apr.ss.utopia.cli.flightmenu;

import apr.ss.utopia.cli.Menu;
import apr.ss.utopia.entity.Flight;
import apr.ss.utopia.inputhandler.StringInputHandler;

public class FlightUpdateMenu implements Menu<String> {

    private final String QUIT = "quit";
    private Flight flight;
    private String[] inputs;

    public FlightUpdateMenu(Flight flight) {
        this.flight = flight;
        while (true) {
            display();
            System.out.println("Please enter a new Origin Airport or enter N/A for no change.");
            inputs[0] = getMenuSelection();
            if (inputs[0].equalsIgnoreCase(QUIT)) return;
            System.out.println("Please enter a new Origin City or enter N/A for no change.");
            inputs[0] = getMenuSelection();
            if (inputs[0].equalsIgnoreCase(QUIT)) return;
            System.out.println("Please enter a new Destination Airport or enter N/A for no change.");
            inputs[0] = getMenuSelection();
            if (inputs[0].equalsIgnoreCase(QUIT)) return;
            System.out.println("Please enter a new Destination City or enter N/A for no change.");
            inputs[0] = getMenuSelection();
            if (inputs[0].equalsIgnoreCase(QUIT)) return;
            System.out.println("Please enter a new Departure Date (mm-dd-yyyy Format) or enter N/A for no change.");
            inputs[0] = getMenuSelection();
            if (inputs[0].equalsIgnoreCase(QUIT)) return;
            System.out.println("Please enter a new Departure Time (24h Format) or enter N/A for no change.");
            inputs[0] = getMenuSelection();
            if (inputs[0].equalsIgnoreCase(QUIT)) return;
            System.out.println("Please enter a new Arrival Date (mm-dd-yyyy Format) or enter N/A for no change.");
            inputs[0] = getMenuSelection();
            if (inputs[0].equalsIgnoreCase(QUIT)) return;
            System.out.println("Please enter a new Arrival Time (24h Format) or enter N/A for no change.");
            inputs[0] = getMenuSelection();
            if (inputs[0].equalsIgnoreCase(QUIT)) return;

            // TODO: Process inputs for an update

        }
    }

    @Override
    public void display() {
        System.out.println("You have chosen to update the Flight with " +
                "Flight ID: " + flight.getId() + " and " +
                "Flight Origin: " + flight.getRoute().getOriginAirport().getCity() + " and " +
                "Flight Destination: " + flight.getRoute().getDestinationAirport().getCity() + ". \n" +
                "Enter ‘quit’ at any prompt to cancel operation.");
    }

    @Override
    public String getMenuSelection() {
        return new StringInputHandler().getInput();
    }
}

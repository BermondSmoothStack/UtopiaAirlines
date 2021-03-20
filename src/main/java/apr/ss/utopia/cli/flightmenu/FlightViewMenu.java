package apr.ss.utopia.cli.flightmenu;

import apr.ss.utopia.cli.Menu;
import apr.ss.utopia.entity.Flight;

public class FlightViewMenu implements Menu {

    Flight flight;

    public FlightViewMenu(Flight f) {
        this.flight = f;
    }

    @Override
    public void display() {
        System.out.println(
                "You have chosen to view the Flight with Flight ID:" +
                        flight.getId() +
                        "and Departure Airport " + flight.getRoute().getOriginAirport().getCity() +
                        "and Arrival Airport " + flight.getRoute().getDestinationAirport().getCity() +
                        ""
        );
    }

    @Override
    public Integer getMenuSelection() {
        return null;
    }
}

package apr.ss.utopia.cli.flightmenu;

import apr.ss.utopia.cli.Menu;
import apr.ss.utopia.entity.Flight;
import apr.ss.utopia.inputhandler.IntInputHandler;

import java.io.IOException;
import java.util.List;

public class SelectFlightMenu implements Menu<Integer> {
    public static final String VIEW_METHOD = "view";
    public static final String BOOK_METHOD = "book";

    private List<Flight> managedFlights;

    public SelectFlightMenu(String method) throws IOException {
        this("", method);
    }

    public SelectFlightMenu(String header, String method) throws IOException {
        // TODO: get all flights
        //  select origin.iata_id as origin_id, origin.city as origin, destination.iata_id as destination_id,  destination.city as destination
        //  from
        //  (select iata_id, city
        //  from flight
        //  join route
        //  on flight.id = route.id
        //  join airport
        //  on route.origin_id = airport.iata_id) as origin,
        //  *
        //  (select iata_id, city
        //  from flight
        //  join route
        //  on flight.id = route.id
        //  join airport
        //  on route.destination_id = airport.iata_id) as destination
        //  *
        //  ** managedFlights = resultSet **
        while (true) {
            display(header);
            Integer input = getMenuSelection();
            if (input > managedFlights.size())
                return;
            switch (method) {
                case VIEW_METHOD:
                    new FlightMenu(managedFlights.get(input));
                    break;
                case BOOK_METHOD:
                    new FlightBookingMenu(managedFlights.get(input), FlightBookingMenu.BOOK_METHOD);
                    break;
                default:
                    return;
            }
        }
    }

    @Override
    public void display() {
        int c = 1;
        for (Flight f : managedFlights) {
            String item = "[" + c + "] " +
                    f.getRoute().getOriginAirport().getAirportCode() +
                    f.getRoute().getOriginAirport().getCity() +
                    " → " +
                    f.getRoute().getDestinationAirport().getCity();
            System.out.println(item);
            c++;
        }
        System.out.println("[" + managedFlights.size() + "] Quit to cancel operation.");
    }

    @Override
    public Integer getMenuSelection() {
        IntInputHandler ih = new IntInputHandler(1, managedFlights.size() + 1);
        ih.handler();
        return ih.getVerifiedInput();
    }
}

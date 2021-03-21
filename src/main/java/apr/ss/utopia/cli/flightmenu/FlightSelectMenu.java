package apr.ss.utopia.cli.flightmenu;

import apr.ss.utopia.cli.Menu;
import apr.ss.utopia.entity.Flight;
import apr.ss.utopia.inputhandler.IntInputHandler;

import java.io.IOException;
import java.util.List;

public class FlightSelectMenu implements Menu<Integer> {
    public static final String VIEW_METHOD = "view";
    public static final String BOOK_METHOD = "book";
    public static final String CANCEL_METHOD = "cancel";

    private List<Flight> managedFlights;

    public FlightSelectMenu(String method) throws IOException {
        this("", method);
    }

    public FlightSelectMenu(String header, String method) throws IOException {
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
        //  ,booking where confirmation_code = "5555554";  IF CANCELLING THIS IS INCLUDED
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
                    new FlightMgmtMenu(managedFlights.get(input), FlightMgmtMenu.BOOK_METHOD);
                    break;
                case CANCEL_METHOD:
                    new FlightMgmtMenu(managedFlights.get(input), FlightMgmtMenu.CANCEL_METHOD);
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

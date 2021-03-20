package apr.ss.utopia.cli.employeemenu;

import apr.ss.utopia.cli.Menu;
import apr.ss.utopia.cli.flightmenu.FlightMenu;
import apr.ss.utopia.entity.Flight;

import java.util.ArrayList;
import java.util.List;

public class EmployeeFlightMenu implements Menu {

    private List<Flight> managedFlights;

    public EmployeeFlightMenu() {
        while (true) {
            display();
            Integer input = getMenuSelection();
            if (input > managedFlights.size())
                return;
            new FlightMenu(managedFlights.get(input));
        }
    }

    @Override
    public void display() {
        // TODO: Query flights:
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
    }

    @Override
    public Integer getMenuSelection() {
        EmployeeFlightInputHandler ih = new EmployeeFlightInputHandler(managedFlights.size() + 1);
        ih.handler();
        return ih.getVerifiedInput();
    }
}

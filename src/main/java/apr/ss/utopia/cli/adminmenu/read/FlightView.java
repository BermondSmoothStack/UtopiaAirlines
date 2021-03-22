package apr.ss.utopia.cli.adminmenu.read;

import apr.ss.utopia.cli.adminmenu.AbsCRUD;
import apr.ss.utopia.entity.Flight;
import apr.ss.utopia.inputhandler.StringInputHandler;
import apr.ss.utopia.service.FlightService;

import java.sql.SQLException;
import java.util.List;

public class FlightView extends AbsCRUD {

    @Override
    protected void display() throws SQLException {

        System.out.println("View a Flight:\n" +
                "Enter a Flight ID: ");
        String code = new StringInputHandler().getVerifiedInput();
        Flight f = new Flight();
        f.setId(Integer.parseInt(code));
        FlightService fs = new FlightService();
        f = fs.fetchFlightById(f);
        System.out.println("Airport Information.\n" +
                "ID: " + f.getId() + "\n" +
                "Origin Airport: " + f.getRoute().getOriginAirport().toString() + "\n" +
                "Destination Airport: " + f.getRoute().getDestinationAirport().toString() + "\n" +
                "Flight Departure Time: " + f.getDepartureTime() + "\n" +
                "Flight Arrival Time: " + f.getArrivalTime() + "\n" +
                "Airplane Type: " + f.getAirplane().getType().getId() + "\n" +
                "Seats Price: " + f.getSeatPrice() + "\n"
        );
    }
}

package apr.ss.utopia.cli.adminmenu.read;

import apr.ss.utopia.cli.adminmenu.AbsCRUD;
import apr.ss.utopia.entity.Flight;
import apr.ss.utopia.inputhandler.StringInputHandler;

import java.sql.SQLException;

public class FlightView extends AbsCRUD {

    @Override
    protected void display() throws SQLException {

        System.out.println("View a Flight:\n" +
                "Enter a Flight ID: ");
        String code = new StringInputHandler().getVerifiedInput();
        Flight f = null; // TODO: call read Flight service
        System.out.println("Airport Information:" +
                "ID: " + f.getId() + "\n" +
                "Origin Airport: " + f.getRoute().getOriginAirport().toString() + "\n" +
                "Destination Airport: " + f.getRoute().getDestinationAirport().toString() + "\n" +
                "Flight Departure Time: " + f.getDepartureTime() + "\n" +
                "Flight Arrival Time: " + f.getArrivalTime() + "\n" +
                "Airplane Type: " + f.getAirplane().getType() + "\n" +
                "Reserved Seats: " + f.getSeats() + "\n" +
                "Seats Price: " + f.getSeatPrice() + "\n"
        );
    }
}

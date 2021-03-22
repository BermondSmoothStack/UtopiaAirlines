package apr.ss.utopia.cli.adminmenu.read;

import apr.ss.utopia.cli.adminmenu.AbsCRUD;
import apr.ss.utopia.entity.FlightBookings;
import apr.ss.utopia.entity.Passenger;
import apr.ss.utopia.inputhandler.StringInputHandler;
import apr.ss.utopia.service.FlightBookingService;
import apr.ss.utopia.service.PassengerService;

import java.sql.SQLException;

public class TicketsView extends AbsCRUD {

    @Override
    protected void display() throws SQLException {

        System.out.println("View a Ticket:\n" +
                "Enter your Passenger ID: ");
        PassengerService fbs = new PassengerService();
        Passenger p = fbs.getPassengerById(Integer.parseInt(new StringInputHandler().getVerifiedInput()));
        System.out.println("Passenger Information." +
                "\nGiven Name: " + p.getGivenName() +
                "\nFamily Name: " + p.getFamilyName() +
                "\nDate of Birth: " + p.getDob().toString() +
                "\nGender: " + p.getGender() +
                "\nAddress: " + p.getAddress()

        );
    }
}

package apr.ss.utopia.cli.adminmenu.read;

import apr.ss.utopia.cli.adminmenu.AbsCRUD;
import apr.ss.utopia.entity.FlightBookings;
import apr.ss.utopia.entity.Passenger;

import java.sql.SQLException;

public class TicketsView extends AbsCRUD {

    @Override
    protected void display() throws SQLException {

        System.out.println("View a Ticket:\n" +
                "Enter the Passenger ID: ");
        Passenger p = null; // TODO: call read Passenger service
        System.out.println("Passenger Information." +
                "\nGiven Name: " + p.getGivenName() +
                "\nFamily Name: " + p.getFamilyName() +
                "\nDate of Birth: " + p.getDob().toString() +
                "\nGender: " + p.getGender() +
                "\nAddress: " + p.getAddress()

        );
    }
}

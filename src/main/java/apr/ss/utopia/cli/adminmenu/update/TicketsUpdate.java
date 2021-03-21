package apr.ss.utopia.cli.adminmenu.update;

import apr.ss.utopia.cli.adminmenu.AbsCRUD;
import apr.ss.utopia.entity.Booking;
import apr.ss.utopia.entity.Passenger;
import apr.ss.utopia.inputhandler.StringInputHandler;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class TicketsUpdate extends AbsCRUD {

    @Override
    protected void display() throws SQLException {
        Passenger p = new Passenger();
        Booking b = new Booking();

        System.out.println("Would you like to create a new booking for a passenger [Y/N]");
        String willCreateBookingInput = new StringInputHandler().getVerifiedInput();
        if ("Y".equalsIgnoreCase(willCreateBookingInput)) {
            // TODO: Create booking
        } else {
            System.out.println("Enter the Booking ID");
            String bookingId = new StringInputHandler().getVerifiedInput();
            // TODO: Fetch booking
        }
        System.out.println("Input passenger ID: ");
        String passengerIdInput = new StringInputHandler().getVerifiedInput();
        // TODO: Fetch passenger

        String input;
        System.out.println("Updating a new Passenger.");
        System.out.println("Change Given Name?[Y/N]");
        input = new StringInputHandler().getVerifiedInput();
        if ("Y".equalsIgnoreCase(input)) {
            System.out.println("Enter new Given Name: ");
            p.setGivenName(new StringInputHandler().getVerifiedInput());
        }
        System.out.println("Change Family Name?[Y/N]");
        input = new StringInputHandler().getVerifiedInput();
        if ("Y".equalsIgnoreCase(input)) {
            System.out.println("Enter new Family Name: ");
            p.setGivenName(new StringInputHandler().getVerifiedInput());
        }

        System.out.println("Change Date of Birth?[Y/N]");
        if ("Y".equalsIgnoreCase(input)) {
            while (true) {
                System.out.println("Enter new Date of Birth (MM-dd-yyyy):");
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy").withResolverStyle(ResolverStyle.STRICT);
                try {
                    LocalDateTime dob = LocalDateTime.parse(new StringInputHandler().getVerifiedInput(), dtf);
                    p.setDob(dob);
                    break;
                } catch (DateTimeParseException e) {
                    System.out.println("Date wasn't properly formatted, try again.");
                }
            }
        }
        System.out.println("Change Gender?[Y/N]");
        if ("Y".equalsIgnoreCase(input)) {
            System.out.println("Enter new Gender");
            p.setGender(new StringInputHandler().getVerifiedInput());
        }
        System.out.println("Change Address?[Y/N]");
        if ("Y".equalsIgnoreCase(input)) {
            System.out.println("Enter Address:");
            p.setAddress(new StringInputHandler().getVerifiedInput());
        }

        // TODO: Update passenger
    }
}

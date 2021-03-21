package apr.ss.utopia.cli.adminmenu.create;

import apr.ss.utopia.cli.adminmenu.AbsCRUD;
import apr.ss.utopia.entity.Booking;
import apr.ss.utopia.entity.Passenger;
import apr.ss.utopia.inputhandler.StringInputHandler;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.UUID;

public class TicketsCreate extends AbsCRUD {


    @Override
    protected void display() throws SQLException {
        Passenger p = new Passenger();
        Booking b = new Booking();

        System.out.println("Would you like to create a booking for a passenger [Y/N]");
        String willCreateBookingInput = new StringInputHandler().getVerifiedInput();
        if (!"Y".equalsIgnoreCase(willCreateBookingInput)) {
            return;
        } else {
            System.out.println("Do you have an existing booking? [Y/N]");
            String hasBooking = new StringInputHandler().getVerifiedInput();
            if (!"Y".equalsIgnoreCase(hasBooking)){
                System.out.println("Creating a new Booking");
                // TODO: Create booking
            } else {
                System.out.println("Enter Booking ID:");
                String bookingID = new StringInputHandler().getVerifiedInput();
                // TODO: Fetch booking
            }
        }

        System.out.println("Do you have an existing Passenger [Y/N]");
        String hasPassengerInput = new StringInputHandler().getVerifiedInput();

        if (!"Y".equalsIgnoreCase(hasPassengerInput)) {
            System.out.println("Creating a new Passenger.");
            System.out.println("Enter Given Name:");
            p.setGivenName(new StringInputHandler().getVerifiedInput());
            System.out.println("Enter Family Name:");
            p.setFamilyName(new StringInputHandler().getVerifiedInput());

            while(true) {
                System.out.println("Enter Date of Birth (MM-dd-yyyy):");
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy").withResolverStyle(ResolverStyle.STRICT);
                try {
                    LocalDateTime dob = LocalDateTime.parse(new StringInputHandler().getVerifiedInput(), dtf);
                    p.setDob(dob);
                    break;
                } catch(DateTimeParseException e){
                    System.out.println("Date wasn't properly formatted, try again.");
                }
            }
            System.out.println("Enter Gender:");
            p.setGender(new StringInputHandler().getVerifiedInput());
            System.out.println("Enter Address:");
            p.setAddress(new StringInputHandler().getVerifiedInput());
            // TODO: Create passenger
        } else {
            System.out.println("Input passenger ID: ");
            String passengerIdInput = new StringInputHandler().getVerifiedInput();
            // TODO: Fetch passenger
        }
    }
}
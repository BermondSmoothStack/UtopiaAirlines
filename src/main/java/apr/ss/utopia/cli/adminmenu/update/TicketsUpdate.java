package apr.ss.utopia.cli.adminmenu.update;

import apr.ss.utopia.cli.adminmenu.AbsCRUD;
import apr.ss.utopia.entity.Booking;
import apr.ss.utopia.entity.Flight;
import apr.ss.utopia.entity.Passenger;
import apr.ss.utopia.inputhandler.IntInputHandler;
import apr.ss.utopia.inputhandler.StringInputHandler;
import apr.ss.utopia.service.BookingService;
import apr.ss.utopia.service.FlightBookingService;
import apr.ss.utopia.service.FlightService;
import apr.ss.utopia.service.PassengerService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.List;
import java.util.UUID;

public class TicketsUpdate extends AbsCRUD {

    @Override
    protected void display() throws SQLException {
        Passenger p = new Passenger();
        Booking b = new Booking();

        System.out.println("Would you like to create a new booking for a passenger [Y/N]");
        String willCreateBookingInput = new StringInputHandler().getVerifiedInput();
        if ("Y".equalsIgnoreCase(willCreateBookingInput)) {
            System.out.println("Creating a new Booking");
            b.setActive(true);
            String uuid = UUID.randomUUID().toString().replace("-", "");
            b.setConfirmationCode(uuid);
        } else {
            while (true) {
                System.out.println("Enter Booking ID:");
                String bookingID = new StringInputHandler().getVerifiedInput();
                b = new BookingService().getBookingById(bookingID);
                if (null == b || null == b.getId() || b.getId() < 1) {
                    System.out.println("Booking not found, try again? [Y/N]");
                    String tryagain = new StringInputHandler().getVerifiedInput();
                    if (!"Y".equalsIgnoreCase(tryagain))
                        return;
                } else break;

            }
        }
        System.out.println("Input passenger ID: ");
        String passengerIdInput = new StringInputHandler().getVerifiedInput();

        PassengerService ps = new PassengerService();
        p = ps.getPassengerById(Integer.parseInt(passengerIdInput));

        String input;
        System.out.println("Updating a Passenger.");
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
        input = new StringInputHandler().getVerifiedInput();
        if ("Y".equalsIgnoreCase(input)) {
            while (true) {
                System.out.println("Enter new Date of Birth (MM-dd-yyyy):");
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-uuuu").withResolverStyle(ResolverStyle.STRICT);
                try {
                    LocalDate dob = LocalDate.from(dtf.parse(new StringInputHandler().getVerifiedInput()));
                    p.setDob(dob);
                    break;
                } catch (DateTimeParseException e) {
                    System.out.println("Date wasn't properly formatted, try again.");
                }
            }
        }
        System.out.println("Change Gender?[Y/N]");
        input = new StringInputHandler().getVerifiedInput();
        if ("Y".equalsIgnoreCase(input)) {
            System.out.println("Enter new Gender");
            p.setGender(new StringInputHandler().getVerifiedInput());
        }
        System.out.println("Change Address?[Y/N]");
        if ("Y".equalsIgnoreCase(input)) {
            System.out.println("Enter Address:");
            p.setAddress(new StringInputHandler().getVerifiedInput());
        }

        try {
            ps.updateTicket(b, p);
            System.out.println("Passenger Details Updated;");
        } catch (SQLException e) {
            System.out.println("Ticket Creation Failed");
            System.out.println(e);
        }
    }
}

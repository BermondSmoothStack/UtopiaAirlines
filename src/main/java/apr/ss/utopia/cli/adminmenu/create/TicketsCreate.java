package apr.ss.utopia.cli.adminmenu.create;

import apr.ss.utopia.cli.adminmenu.AbsCRUD;
import apr.ss.utopia.entity.Booking;
import apr.ss.utopia.entity.Passenger;
import apr.ss.utopia.inputhandler.StringInputHandler;
import apr.ss.utopia.service.BookingService;
import apr.ss.utopia.service.PassengerService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.UUID;

public class TicketsCreate extends AbsCRUD {


    @Override
    protected void display() throws SQLException {
        Passenger p = new Passenger();
        Booking b = new Booking();

        System.out.println("Creating a new Ticket");
        System.out.println("Do you have an existing booking? [Y/N]");
        String hasBooking = new StringInputHandler().getVerifiedInput();
        if (!"Y".equalsIgnoreCase(hasBooking)) {
            System.out.println("Creating a new Booking");
            b.setActive(true);
            String uuid = UUID.randomUUID().toString().replace("-", "");
            b.setConfirmationCode(uuid);
            b = new BookingService().createBooking(b);
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

        System.out.println("Creating a new Passenger.");
        System.out.println("Enter Given Name:");
        p.setGivenName(new StringInputHandler().getVerifiedInput());
        System.out.println("Enter Family Name:");
        p.setFamilyName(new StringInputHandler().getVerifiedInput());

        while (true) {
            System.out.println("Enter Date of Birth (MM-dd-yyyy):");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-uuuu").withResolverStyle(ResolverStyle.STRICT);
            try {
                LocalDate dob = LocalDate.from(dtf.parse(new StringInputHandler().getVerifiedInput()));
                p.setDob(dob);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Date wasn't properly formatted, try again.");
            }
        }
        System.out.println("Enter Gender:");
        p.setGender(new StringInputHandler().getVerifiedInput());
        System.out.println("Enter Address:");
        p.setAddress(new StringInputHandler().getVerifiedInput());
        p.setBooking(b);
        p = new PassengerService().createPassenger(p);
        if (null == p || null == p.getId() || p.getId() < 1) {
            System.out.println("Passenger Creation Failed, please try again.");
        } else System.out.println("Ticket Created. Confirmation code is: " + p.getBooking().getConfirmationCode());
    }
}

package apr.ss.utopia.cli.adminmenu.create;

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
        System.out.println("Select a flight\n");
        FlightService fs = new FlightService();
        List<Flight> flightList = fs.fetchAllFlights();
        int f = 1;
        for (Flight flight : flightList) {
            System.out.println("[" + f + "] " + flight.showRoute());
            f++;
        }
        IntInputHandler fih = new IntInputHandler(1, f);
        fih.handler();
        Flight flight = flightList.get(fih.getVerifiedInput() - 1);
        FlightBookingService fbs = new FlightBookingService();
        try {
            fbs.createTicket(flight, b, p);
            System.out.println("Ticket Created. Confirmation code is: " + p.getBooking().getConfirmationCode());
        } catch (SQLException e) {
            System.out.println("Ticket Creation Failed");
            System.out.println(e);
        }


    }
}

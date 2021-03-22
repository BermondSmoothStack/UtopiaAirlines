package apr.ss.utopia.cli.flightmenu;

import apr.ss.utopia.cli.Menu;
import apr.ss.utopia.dao.FlightBookingsDAO;
import apr.ss.utopia.entity.*;
import apr.ss.utopia.inputhandler.IntInputHandler;
import apr.ss.utopia.service.BookingService;
import apr.ss.utopia.service.FlightBookingService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

public class FlightMgmtMenu implements Menu<Integer> {

    public static final String BOOK_METHOD = "book";
    public static final String CANCEL_METHOD = "cancel";
    private Flight flight;
    private Passenger passenger;


    public FlightMgmtMenu(Flight flight, String mode, Passenger passenger) throws IOException {
        this.passenger = passenger;
        this.flight = flight;
        String type = "";
        while (true) {
            display("Pick a seat you want to " + mode + " a ticket for.\n ");
            switch (getMenuSelection()) {
                case 1:
                    new FlightViewMenu(flight);
                    break;
                case 2:
                    type = SeatType.FIRST_CLASS;
                    break;
                case 3:
                    type = SeatType.BUSINESS_CLASS;
                    break;
                case 4:
                    type = SeatType.ECONOMY_CLASS;
                    break;
                case 5:
                    return;
                default:
            }
            if (mode.equalsIgnoreCase(BOOK_METHOD)) {
                Booking b = new Booking();
                String uuid = UUID.randomUUID().toString().replace("-", "");
                b.setConfirmationCode(uuid);
                b.setActive(true);
                try {
                    FlightBookingService fbs = new FlightBookingService();
                    fbs.createTicket(flight, b, null);
                } catch (SQLException e){
                    System.out.println("Booking failed...");
                }
                System.out.println("Booking Successful!");
            } else if (mode.equalsIgnoreCase(CANCEL_METHOD)) {
                BookingService bs = new BookingService();
                if (bs.cancelBooking(flight, passenger)) System.out.println("Booking Cancellation Successful");
            }

        }
    }

    public FlightMgmtMenu(Flight flight, String mode) throws IOException {
        this(flight,mode,null);
    }

    @Override
    public void display() {
        System.out.println("[1] View Flight Details\n" +
                "[2] First\n" +
                "[3] Business\n" +
                "[4] Economy\n" +
                "[5] Quit to cancel operation ");
    }

    @Override
    public Integer getMenuSelection() {
        IntInputHandler ih = new IntInputHandler(1, 5);
        ih.handler();
        return ih.getVerifiedInput();
    }
}

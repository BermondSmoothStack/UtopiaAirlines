package apr.ss.utopia.cli.adminmenu.delete;

import apr.ss.utopia.cli.adminmenu.AbsCRUD;
import apr.ss.utopia.dao.PassengerDAO;
import apr.ss.utopia.entity.Booking;
import apr.ss.utopia.entity.FlightBookings;
import apr.ss.utopia.entity.Passenger;
import apr.ss.utopia.inputhandler.StringInputHandler;
import apr.ss.utopia.service.BookingService;
import apr.ss.utopia.service.PassengerService;

import java.sql.Connection;
import java.sql.SQLException;

public class TicketsDelete extends AbsCRUD {

    @Override
    protected void display() throws SQLException {

        System.out.println("Remove Ticket.\n" +
                "Enter Confirmation code: ");
        String code = new StringInputHandler().getVerifiedInput();
        Booking booking = new Booking();
        booking.setConfirmationCode(code);
        BookingService bs = new BookingService();
        if (bs.deleteBookingByConfirmationCode(booking)) System.out.println("Delete successful");
        else System.out.println("Delete failed...");
    }


}

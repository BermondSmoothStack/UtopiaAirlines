package apr.ss.utopia.cli.adminmenu.update;

import apr.ss.utopia.cli.adminmenu.AbsCRUD;
import apr.ss.utopia.entity.Booking;
import apr.ss.utopia.inputhandler.StringInputHandler;
import apr.ss.utopia.service.BookingService;

import java.sql.SQLException;

public class AdminOverrideCancellation extends AbsCRUD {
    @Override
    protected void display() throws SQLException {
        System.out.println("Overriding Trip Cancellation.\nPlease input Booking Confirmation Code:");
        String id = new StringInputHandler().getVerifiedInput();
        BookingService bs = new BookingService();
        Booking b = bs.getBookingById(id);
        if (null == b || null == b.getId()) {
            System.out.println("Booking not found");
            return;
        }
        String status = b.getActive() ? "Active" : "Inactive";
        System.out.println("Current ticket booking status is: " + status +
                "\nWould you like to switch the status of this booking. [Y/N]");
        String willSwitchStr = new StringInputHandler().getVerifiedInput();
        if ("Y".equalsIgnoreCase(willSwitchStr)) {
            b.setActive(!b.getActive());
            bs.updateBookingStatus(b);
        }

    }
}

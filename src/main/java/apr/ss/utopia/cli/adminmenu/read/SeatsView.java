package apr.ss.utopia.cli.adminmenu.read;

import apr.ss.utopia.cli.adminmenu.AbsCRUD;
import apr.ss.utopia.entity.Seats;
import apr.ss.utopia.inputhandler.StringInputHandler;
import apr.ss.utopia.service.SeatService;

import java.sql.SQLException;

public class SeatsView extends AbsCRUD {

    @Override
    protected void display() throws SQLException {

        System.out.println("View a Seat:\n" +
                "Enter the Seat ID: ");
        String code = new StringInputHandler().getVerifiedInput();
        Seats seats = new Seats();
        seats.setId(Integer.parseInt(code));
        SeatService ss = new SeatService();
        seats = ss.fetchSeatById(seats);
        System.out.println("Seat Information." +
                "\nID: " + seats.getId() +
                "\nSeat Type: " + seats.getType().getName() +
                "\nSeat Amount: " + seats.getCapacity() +
                "\nSlots: " + seats.getCapacity()
        );
    }
}

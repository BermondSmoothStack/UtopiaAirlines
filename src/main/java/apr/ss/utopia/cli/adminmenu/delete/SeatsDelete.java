package apr.ss.utopia.cli.adminmenu.delete;

import apr.ss.utopia.cli.adminmenu.AbsCRUD;
import apr.ss.utopia.entity.Seats;
import apr.ss.utopia.inputhandler.StringInputHandler;
import apr.ss.utopia.service.SeatService;

import java.sql.SQLException;

public class SeatsDelete extends AbsCRUD {

    @Override
    protected void display() throws SQLException {

        System.out.println("Remove Passenger.\n" +
                "Enter Seats ID: ");
        String code = new StringInputHandler().getVerifiedInput();
        Seats seat = new Seats();
        seat.setId(Integer.parseInt(code));
        SeatService ss = new SeatService();
        if (ss.deleteSeat(seat)) System.out.println("Delete successful");
        else System.out.println("Delete failed...");
    }
}

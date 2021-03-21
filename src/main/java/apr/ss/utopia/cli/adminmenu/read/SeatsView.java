package apr.ss.utopia.cli.adminmenu.read;

import apr.ss.utopia.cli.adminmenu.AbsCRUD;
import apr.ss.utopia.entity.Seats;

import java.sql.SQLException;

public class SeatsView extends AbsCRUD {

    @Override
    protected void display() throws SQLException {

        System.out.println("View a Seat:\n" +
                "Enter the Seat ID: ");
        Seats seats = null; // TODO: call read Seats service
        System.out.println("Seat Information." +
                "\nID: " + seats.getId() +
                "\nSeat Type: " + seats.getType().getName() +
                "\nAirplane Type: " + seats.getAirplaneType().getId() +
                "\nSlots: " + seats.getCapacity()
        );
    }
}

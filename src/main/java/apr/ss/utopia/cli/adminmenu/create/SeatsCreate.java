package apr.ss.utopia.cli.adminmenu.create;

import apr.ss.utopia.cli.adminmenu.AbsCRUD;
import apr.ss.utopia.entity.AirplaneType;
import apr.ss.utopia.entity.SeatType;
import apr.ss.utopia.entity.Seats;
import apr.ss.utopia.inputhandler.IntInputHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeatsCreate extends AbsCRUD {


    @Override
    protected void display() throws SQLException {
        Seats seats = new Seats();
        SeatType seatType = null;
        System.out.println("Creating new Seat.");
        System.out.println("Enter Seat Type:\n" +
                "[1] First Class\n" +
                "[2] Business Class\n" +
                "[3] Economy Class\n" +
                "[4] Cancel");
        IntInputHandler ih = new IntInputHandler(1, 4);
        ih.handler();
        Integer seatTypeInt = ih.getVerifiedInput();
        if (seatTypeInt == 4) return;
        // TODO: Fetch SeatType
        seats.setType(seatType);

        System.out.println("Select Airplane type: ");
        // TODO: Fetch airplane types
        List<AirplaneType> airplaneTypes = new ArrayList<>();
        int c = 1;
        for (AirplaneType at : airplaneTypes) {
            System.out.println("\n[" + c + "]Airplane Type: " + at.getId());
            c++;
        }
        System.out.println("\n[" + c + "] Quit");

        ih = new IntInputHandler(1, c);
        ih.handler();
        Integer airplaneType = ih.getVerifiedInput();
        if (airplaneType == c) return;
        seats.setAirplaneType(airplaneTypes.get(airplaneType));

        System.out.println("Enter amount of seats of this type:");
        ih = new IntInputHandler(1, c);
        ih.handler();
        Integer slots = ih.getVerifiedInput();

        seats.setCapacity(slots);

        // TODO: Create new seat
    }

}

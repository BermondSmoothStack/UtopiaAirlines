package apr.ss.utopia.cli.adminmenu.update;

import apr.ss.utopia.cli.adminmenu.AbsCRUD;
import apr.ss.utopia.entity.AirplaneType;
import apr.ss.utopia.entity.SeatType;
import apr.ss.utopia.entity.Seats;
import apr.ss.utopia.inputhandler.IntInputHandler;
import apr.ss.utopia.inputhandler.StringInputHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeatsUpdate extends AbsCRUD {

    @Override
    protected void display() throws SQLException {

        System.out.println("Updating a Seat.\n" +
                "Enter Seat ID: ");
        // TODO: Fetch Seats
        Seats seats = new Seats();
        System.out.println("Enter Seat Type (Current Seat Type: " + seats.getType().getName() + ") :\n" +
                "[1] First Class\n" +
                "[2] Business Class\n" +
                "[3] Economy Class\n" +
                "[4] Skip");
        IntInputHandler ih = new IntInputHandler(1, 4);
        ih.handler();
        Integer seatTypeInt = ih.getVerifiedInput();
        if (seatTypeInt != 4) {
            // TODO: Fetch SeatType
            SeatType seatType = null;
            seats.setType(seatType);
        }

        System.out.println("Select Airplane type (Current Airplane Type: " + seats.getAirplaneType().getId() + ") : ");
        // TODO: Fetch airplane types
        List<AirplaneType> airplaneTypes = new ArrayList<>();
        int c = 1;
        for (AirplaneType at : airplaneTypes) {
            System.out.println("\n[" + c + "]Airplane Type: " + at.getId());
            c++;
        }
        System.out.println("\n[" + c + "] Skip");

        ih = new IntInputHandler(1, c);
        ih.handler();
        Integer airplaneType = ih.getVerifiedInput();
        if (airplaneType != c) {
            seats.setAirplaneType(airplaneTypes.get(airplaneType));
        }

        System.out.println("Enter amount of seats of this type (Current seats:" + seats.getCapacity() + "): ");
        ih = new IntInputHandler(1, 999);
        ih.handler();
        Integer slots = ih.getVerifiedInput();

        seats.setCapacity(slots);

        // TODO: update new seat

    }
}

package apr.ss.utopia.cli.adminmenu.update;

import apr.ss.utopia.cli.adminmenu.AbsCRUD;
import apr.ss.utopia.entity.AirplaneType;
import apr.ss.utopia.entity.SeatType;
import apr.ss.utopia.entity.Seats;
import apr.ss.utopia.inputhandler.IntInputHandler;
import apr.ss.utopia.inputhandler.StringInputHandler;
import apr.ss.utopia.service.AirplaneService;
import apr.ss.utopia.service.AirportService;
import apr.ss.utopia.service.SeatService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeatsUpdate extends AbsCRUD {

    @Override
    protected void display() throws SQLException {

        System.out.println("Updating a Seat.\n" +
                "Enter Seat ID: ");

        Seats seats = new Seats();
        SeatService ss = new SeatService();
        seats.setId(Integer.parseInt(new StringInputHandler().getVerifiedInput()));
        seats = ss.fetchSeatById(seats);
        System.out.println("Enter Seat Type (Current Seat Type: " + seats.getType().getName() + ") :\n" +
                "[1] First Class\n" +
                "[2] Business Class\n" +
                "[3] Economy Class\n" +
                "[4] Skip");
        IntInputHandler ih = new IntInputHandler(1, 4);
        ih.handler();
        Integer seatTypeInt = ih.getVerifiedInput();
        String seatTypeString = "";
        switch (seatTypeInt) {
            case 1:
                seatTypeString = SeatType.FIRST_CLASS;
                break;
            case 2:
                seatTypeString = SeatType.BUSINESS_CLASS;
                break;
            case 3:
                seatTypeString = SeatType.ECONOMY_CLASS;
                break;
        }

        if (seatTypeInt != 4) {
            SeatType seatType = ss.fetchSeatTypeByName(seatTypeString);
            seats.setType(seatType);
        }

        AirplaneService as = new AirplaneService();
        List<AirplaneType> airplaneTypes = as.fetchAllAirplaneTypes();
        int c = 1;
        for (AirplaneType at : airplaneTypes) {
            System.out.println("[" + c + "]Airplane Type: " + at.getId());
            c++;
        }
        System.out.println("[" + c + "] Skip");

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

        ss.updateSeat(seats);
        // TODO: update new seat

    }
}

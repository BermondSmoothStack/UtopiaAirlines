package apr.ss.utopia.cli.adminmenu.create;

import apr.ss.utopia.cli.adminmenu.AbsCRUD;
import apr.ss.utopia.entity.AirplaneType;
import apr.ss.utopia.entity.SeatType;
import apr.ss.utopia.entity.Seats;
import apr.ss.utopia.inputhandler.IntInputHandler;
import apr.ss.utopia.service.AirplaneService;
import apr.ss.utopia.service.SeatService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeatsCreate extends AbsCRUD {


    @Override
    protected void display() throws SQLException {
        Seats seats = new Seats();
        SeatType seatType;
        System.out.println("Creating new Seat.");
        System.out.println("Enter Seat Type:\n" +
                "[1] First Class\n" +
                "[2] Business Class\n" +
                "[3] Economy Class\n" +
                "[4] Cancel");
        IntInputHandler ih = new IntInputHandler(1, 4);
        ih.handler();
        Integer seatTypeInt = ih.getVerifiedInput();
        String stypeString;
        switch (seatTypeInt) {
            case 1:
                stypeString = SeatType.FIRST_CLASS;
                break;
            case 2:
                stypeString = SeatType.BUSINESS_CLASS;
                break;
            case 3:
                stypeString = SeatType.ECONOMY_CLASS;
                break;
            default:
                return;
        }
        seatType = new SeatService().fetchSeatTypeByName(stypeString);
        if (seatType == null){
            System.out.println("Seat Type failed, try again. (Contact admin if problem persists)");
        }
        seats.setType(seatType);

        System.out.println("Select Airplane type: ");
        List<AirplaneType> airplaneTypes = new AirplaneService().fetchAllAirplaneTypes();
        int c = 1;
        for (AirplaneType at : airplaneTypes) {
            System.out.println("[" + c + "] Airplane Type: " + at.getId());
            c++;
        }
        System.out.println("[" + c + "] Quit");

        ih = new IntInputHandler(1, c);
        ih.handler();
        Integer airplaneType = ih.getVerifiedInput();
        if (airplaneType == c) return;

        seats.setAirplaneType(airplaneTypes.get(airplaneType-1));

        System.out.println("Enter amount of seats of this type:");
        ih = new IntInputHandler(1, seats.getAirplaneType().getCapacity());
        ih.handler();
        Integer slots = ih.getVerifiedInput();
        seats.setCapacity(slots);

        System.out.println("Enter reserved seats [0 - " + seats.getCapacity() + "]:");
        ih = new IntInputHandler(0, seats.getCapacity());
        ih.handler();
        seats.setReserved(ih.getVerifiedInput());

        seats = new SeatService().createSeat(seats);
        if (!(null == seats || null == seats.getId())) {
            System.out.println("Seat Created Successfully");
        }
    }

}

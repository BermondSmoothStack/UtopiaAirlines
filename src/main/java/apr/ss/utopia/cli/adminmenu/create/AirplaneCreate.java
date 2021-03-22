package apr.ss.utopia.cli.adminmenu.create;

import apr.ss.utopia.cli.adminmenu.AbsCRUD;
import apr.ss.utopia.entity.Airplane;
import apr.ss.utopia.entity.AirplaneType;
import apr.ss.utopia.inputhandler.IntInputHandler;
import apr.ss.utopia.service.AirplaneService;

import java.sql.SQLException;
import java.util.List;

public class AirplaneCreate extends AbsCRUD {

    private Airplane airplane;

    @Override
    protected void display() throws SQLException {
        Airplane airplane = new Airplane();
        System.out.println("Creating new planes");

        System.out.println("Select an airplane type:");
        List<AirplaneType> airplaneTypes = new AirplaneService().fetchAllAirplaneTypes();
        int c = 1;
        for (AirplaneType at : airplaneTypes) {
            System.out.println("[" + c + "] Airplane Type: " + at.getId() + " Capacity: " + at.getCapacity());
            c++;
        }
        System.out.println("[" + c + "] Quit");

        IntInputHandler ih = new IntInputHandler(1, c);
        ih.handler();
        Integer airplaneType = ih.getVerifiedInput();
        if (airplaneType == c) return;

        airplane.setType(airplaneTypes.get(airplaneType-1));

        airplane = new AirplaneService().createAirplane(airplane);
        if (null == airplane || null == airplane.getId()){
            System.out.println("Failed to create Airplane, try again.");
        } else {
            System.out.println("Plane successfully created.");
            this.airplane = airplane;
        }
    }

    public Airplane getAirplane() {
        return airplane;
    }
}

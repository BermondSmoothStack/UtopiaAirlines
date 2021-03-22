package apr.ss.utopia.cli.adminmenu.delete;

import apr.ss.utopia.cli.adminmenu.AbsCRUD;
import apr.ss.utopia.entity.Flight;
import apr.ss.utopia.inputhandler.StringInputHandler;
import apr.ss.utopia.service.FlightService;

import java.sql.SQLException;

public class FlightDelete extends AbsCRUD {

    @Override
    protected void display() throws SQLException {

        System.out.println("Remove Passenger.\n" +
                "Enter Flight ID: ");
        String code = new StringInputHandler().getVerifiedInput();
        Flight flight = new Flight();
        flight.setId(Integer.parseInt(code));
        FlightService fs = new FlightService();
        if (fs.deleteFlightById(flight)) System.out.println("Delete successful");
        else System.out.println("Delete failed...");
    }
}

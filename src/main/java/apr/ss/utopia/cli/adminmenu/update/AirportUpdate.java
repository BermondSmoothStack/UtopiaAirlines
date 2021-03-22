package apr.ss.utopia.cli.adminmenu.update;

import apr.ss.utopia.cli.adminmenu.AbsCRUD;
import apr.ss.utopia.entity.Airport;
import apr.ss.utopia.inputhandler.StringInputHandler;
import apr.ss.utopia.service.AirportService;

import java.sql.SQLException;

public class AirportUpdate extends AbsCRUD {

    @Override
    protected void display() throws SQLException {
        Airport a = new Airport();

        System.out.println("Updating an Airport:\n" +
                "Enter an IATA Code: ");
        a.setAirportCode(new StringInputHandler().getVerifiedInput());
        System.out.println("Enter the City of the Airport:");
        a.setCity(new StringInputHandler().getVerifiedInput());
        AirportService as = new AirportService();
        if (!as.updateAirport(a)) System.out.println("Airport Update Failed! Make sure you IATA already exists.");


    }
}

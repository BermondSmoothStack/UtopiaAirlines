package apr.ss.utopia.cli.adminmenu.update;

import apr.ss.utopia.cli.adminmenu.AbsCRUD;
import apr.ss.utopia.entity.Airport;
import apr.ss.utopia.inputhandler.StringInputHandler;

import java.sql.SQLException;

public class AirportUpdate extends AbsCRUD {

    @Override
    protected void display() throws SQLException {
        Airport a = new Airport();
        StringInputHandler sih = new StringInputHandler();

        System.out.println("Updating an Airport:\n" +
                "Enter an IATA Code: ");
        a.setAirportCode(sih.getInput());
        System.out.println("Enter the City of the Airport:");
        a.setCity(sih.getInput());
        // TODO: call update airport service
    }
}

package apr.ss.utopia.cli.adminmenu.read;

import apr.ss.utopia.cli.adminmenu.AbsCRUD;
import apr.ss.utopia.entity.Airport;
import apr.ss.utopia.inputhandler.StringInputHandler;

import java.sql.SQLException;

public class AirportView extends AbsCRUD {

    @Override
    protected void display() throws SQLException {
        StringInputHandler sih = new StringInputHandler();

        System.out.println("View an Airport:\n" +
                "Enter an IATA Code: ");
        Airport a = null; // TODO: call read airport service
        System.out.println("Enter the City of the new Airport:" +
                "IATA Code: " + a.getAirportCode() + "\n" +
                "City: " + a.getCity()
        );
    }
}

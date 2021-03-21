package apr.ss.utopia.cli.adminmenu.read;

import apr.ss.utopia.cli.adminmenu.AbsCRUD;
import apr.ss.utopia.entity.Airport;
import apr.ss.utopia.inputhandler.StringInputHandler;

import java.sql.SQLException;

public class AirportView extends AbsCRUD {

    @Override
    protected void display() throws SQLException {

        System.out.println("View an Airport:\n" +
                "Enter an IATA Code: ");
        String code = new StringInputHandler().getVerifiedInput();
        Airport a = null; // TODO: call read airport service
        System.out.println("Airport Information:" +
                "IATA Code: " + a.getAirportCode() + "\n" +
                "City: " + a.getCity()
        );
    }
}

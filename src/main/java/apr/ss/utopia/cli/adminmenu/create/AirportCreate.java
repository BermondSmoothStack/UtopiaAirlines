package apr.ss.utopia.cli.adminmenu.create;


import apr.ss.utopia.cli.adminmenu.AbsCRUD;
import apr.ss.utopia.entity.Airport;
import apr.ss.utopia.inputhandler.StringInputHandler;

import java.sql.SQLException;

public class AirportCreate extends AbsCRUD {


    @Override
    protected void display() throws SQLException {
        Airport a = new Airport();
        StringInputHandler sih = new StringInputHandler();

        System.out.println("Adding new Airports:\n" +
                "Enter an IATA Code: ");
        a.setAirportCode(sih.getInput());
        System.out.println("Enter the City of the new Airport:");
        a.setCity(sih.getInput());
        // TODO: call create airport service
    }
}

package apr.ss.utopia.cli.adminmenu.delete;

import apr.ss.utopia.cli.adminmenu.AbsCRUD;
import apr.ss.utopia.entity.Airport;
import apr.ss.utopia.inputhandler.StringInputHandler;

import java.sql.SQLException;

public class AirportDelete extends AbsCRUD {

    @Override
    protected void display() throws SQLException {
        Airport a = new Airport();
        StringInputHandler sih = new StringInputHandler();

        System.out.println("Delete Airport:\n" +
                "Enter an IATA Code: ");
        a.setAirportCode(sih.getInput());
        // TODO: call delete airport service
    }
}

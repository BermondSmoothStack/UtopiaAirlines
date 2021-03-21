package apr.ss.utopia.cli.adminmenu.delete;

import apr.ss.utopia.cli.adminmenu.AbsCRUD;
import apr.ss.utopia.inputhandler.StringInputHandler;

import java.sql.SQLException;

public class FlightDelete extends AbsCRUD {

    @Override
    protected void display() throws SQLException {

        System.out.println("Remove Passenger.\n" +
                "Enter Flight ID: ");
        String code = new StringInputHandler().getVerifiedInput();
        // TODO: call delete flight service
    }
}

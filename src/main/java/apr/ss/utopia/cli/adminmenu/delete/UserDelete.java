package apr.ss.utopia.cli.adminmenu.delete;

import apr.ss.utopia.cli.adminmenu.AbsCRUD;
import apr.ss.utopia.inputhandler.StringInputHandler;

import java.io.IOException;
import java.sql.SQLException;

public class UserDelete extends AbsCRUD {

    public UserDelete() throws IOException {
    }

    @Override
    protected void display() throws SQLException {

        System.out.println("Deleting User. Press enter twice now to abort.\n" +
                "Enter User ID: ");
        String code = new StringInputHandler().getVerifiedInput();
        if (!"".equals(code)) {}
        // TODO: call delete user service
    }
}

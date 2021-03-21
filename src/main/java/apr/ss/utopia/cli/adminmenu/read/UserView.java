package apr.ss.utopia.cli.adminmenu.read;

import apr.ss.utopia.cli.adminmenu.AbsCRUD;
import apr.ss.utopia.entity.User;

import java.sql.SQLException;

public class UserView extends AbsCRUD {

    @Override
    protected void display() throws SQLException {

        System.out.println("View a User:\n" +
                "Enter the User ID: ");
        User u = null; // TODO: call read User service
        System.out.println("User Information." +
                "\nGiven Name: " + u.getGivenName() +
                "\nFamily Name: " + u.getFamilyName() +
                "\nRole: " + u.getRole().getName() +
                "\nUsername: " + u.getUsername() +
                "\nEmail: " + u.getEmail() +
                "\nPhone: " + u.getPhone() +
                "\nPress any key to continue..."
        );
    }
}

package apr.ss.utopia.cli.adminmenu.read;

import apr.ss.utopia.cli.adminmenu.AbsCRUD;
import apr.ss.utopia.entity.User;
import apr.ss.utopia.inputhandler.StringInputHandler;
import apr.ss.utopia.service.UserService;

import java.sql.SQLException;

public class UserView extends AbsCRUD {

    @Override
    protected void display() throws SQLException {

        System.out.println("View a User:\n" +
                "Enter the User ID: ");
        UserService us = new UserService();
        User u = us.getUserById(Integer.parseInt(new StringInputHandler().getVerifiedInput()));
        System.out.println("User Information." +
                "\nGiven Name: " + u.getGivenName() +
                "\nFamily Name: " + u.getFamilyName() +
                "\nRole: " + u.getRole().getName() +
                "\nUsername: " + u.getUsername() +
                "\nEmail: " + u.getEmail() +
                "\nPhone: " + u.getPhone()
        );
    }
}

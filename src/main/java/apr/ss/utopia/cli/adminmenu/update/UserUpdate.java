package apr.ss.utopia.cli.adminmenu.update;

import apr.ss.utopia.cli.adminmenu.AbsCRUD;
import apr.ss.utopia.entity.Role;
import apr.ss.utopia.entity.User;
import apr.ss.utopia.inputhandler.IntInputHandler;
import apr.ss.utopia.inputhandler.StringInputHandler;

import java.sql.SQLException;
import java.util.List;

public class UserUpdate extends AbsCRUD {

    @Override
    protected void display() throws SQLException {

        System.out.println("Update a new User. Leave empty to skip.\n");
        System.out.println("Please enter User ID: ");
        String uidInput = new StringInputHandler().getVerifiedInput();
        User u = null;
        // TODO: Fetch user

        System.out.println("Current Given Name is [" + u.getGivenName() + "]");
        System.out.println("Enter Given Name:");
        String input = new StringInputHandler().getVerifiedInput();
        if ("".equals(input)) return;
        u.setGivenName(input);

        System.out.println("Current Family Name is [" + u.getFamilyName() + "]");
        System.out.println("Enter Family Name:");
        input = new StringInputHandler().getVerifiedInput();
        if ("".equals(input)) return;
        u.setFamilyName(input);

        System.out.println("Current  username is [" + u.getUsername() + "]");
        System.out.println("Enter Username:");
        input = new StringInputHandler().getVerifiedInput();
        if ("".equals(input)) return;
        u.setUsername(input);

        System.out.println("Enter Password:");
        input = new StringInputHandler().getVerifiedInput();
        if ("".equals(input)) return;
        u.setPassword(input);

        System.out.println("Current Email is [" + u.getEmail() + "]");
        System.out.println("Enter Email:");
        input = new StringInputHandler().getVerifiedInput();
        if ("".equals(input)) return;
        u.setEmail(input);

        System.out.println("Current Phone Number is [" + u.getPhone() + "]");
        System.out.println("Enter Phone Number:");
        input = new StringInputHandler().getVerifiedInput();
        if ("".equals(input)) return;
        u.setPhone(input);

        System.out.println("Current Role is [" + u.getRole().getName() + "]");
        System.out.println("Select Role: ");
        // TODO Fetch all roles
        List<Role> roleList = null;
        int c = 1;
        for (Role r : roleList) {
            System.out.println("[" + c + "] " + r.getName());
            c++;
        }
        System.out.println("[" + c + "] Quit");
        IntInputHandler intIh = new IntInputHandler(1, c);
        Integer intInput = intIh.getInput();
        if (c == intInput) return;
        u.setRole(roleList.get(intInput));

        // TODO: Create user
    }
}

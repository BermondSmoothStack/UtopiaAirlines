package apr.ss.utopia.service;

import apr.ss.utopia.dao.RoleDAO;
import apr.ss.utopia.dao.UserDAO;
import apr.ss.utopia.entity.Role;
import apr.ss.utopia.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    Util util = new Util();

    public User createUser(User u) {
        Connection conn = null;

        try {
            conn = util.getConnection();
            UserDAO userDAO = new UserDAO(conn);
            u.setId(userDAO.addUser(u));
            conn.commit();
            return u;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("User Creation Failed! Make sure you entered the correct information.");
            System.out.println(e);
        }
        return u;
    }

}

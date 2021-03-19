package apr.ss.utopia.dao;

import apr.ss.utopia.entity.Booking;
import apr.ss.utopia.entity.Passenger;
import apr.ss.utopia.entity.Role;
import apr.ss.utopia.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends BaseDAO<User>{

    public UserDAO(Connection conn) {
        super(conn);
    }

    public void addUser(User user) throws SQLException {
        save("insert into (" +
                        User.ROLE + ", " +
                        User.GVN_NAME + ", " +
                        User.FAM_NAME + ", " +
                        User.USERNAME + ", " +
                        User.EMAIL + ", " +
                        User.PASSWORD + ", " +
                        User.PHONE + ", " +
                        ") " + User.NAME + " values (?, ?, ?, ?, ?, ?, ?)",
                new Object[]{
                        user.getRole().getId(),
                        user.getGivenName(),
                        user.getFamilyName(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getPassword(),
                        user.getPhone()
                });
    }

    public void updateUser(User user) throws SQLException {
        save("update " + User.NAME + " set " +
                        User.ROLE + " = ?, " +
                        User.GVN_NAME + " = ?, " +
                        User.FAM_NAME + " = ?, " +
                        User.USERNAME + " = ?, " +
                        User.EMAIL + " = ?, " +
                        User.PASSWORD + " = ?, " +
                        User.PHONE + " = ? " +
                        "where " + User.ID + " = ?",
                new Object[]{
                        user.getRole().getId(),
                        user.getGivenName(),
                        user.getFamilyName(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getPassword(),
                        user.getPhone(),
                        user.getId()
                });
    }

    public void deleteUser(User user) throws SQLException {
        save("delete from " + User.NAME + " where " + User.ID + " = ?", new Object[]{user.getId()});
    }

    public List<User> readAllUser() throws ClassNotFoundException, SQLException {
        return read("select * from " + User.NAME, null);
    }

    public List<User> readUsersByCode(User user) throws ClassNotFoundException, SQLException {
        return read("select * from " + User.NAME + " where " + User.ID + " = ?", new Object[]{user.getId()});
    }

    @Override
    public List<User> extractData(ResultSet rs) throws SQLException {
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            User u = new User();
            Role r = new Role();

            r.setId(rs.getInt(User.ROLE));

            u.setRole(r);
            u.setId(rs.getInt(User.ID));
            u.setGivenName(rs.getString(User.GVN_NAME));
            u.setFamilyName(rs.getString(User.FAM_NAME));
            u.setEmail(rs.getString(User.EMAIL));
            u.setUsername(rs.getString(User.USERNAME));
            u.setPassword(rs.getString(User.PASSWORD));
            u.setPhone(rs.getString(User.PHONE));
            users.add(u);
        }
        return users;
    }
}

package apr.ss.utopia.service;

import apr.ss.utopia.dao.FlightDAO;
import apr.ss.utopia.entity.Flight;

import java.sql.Connection;
import java.sql.SQLException;

public class FlightService {
    Util util = new Util();

    public Flight createFlight(Flight f) {
        Connection conn;

        try {
            conn = util.getConnection();
            FlightDAO flightDAO = new FlightDAO(conn);
            f.setId(flightDAO.addFlight(f));
            conn.commit();
            return f;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Flight Creation Failed! Make sure you entered the correct information.");
            System.out.println(e);
        }
        return f;
    }
}

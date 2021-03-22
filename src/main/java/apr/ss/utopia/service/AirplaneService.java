package apr.ss.utopia.service;


import apr.ss.utopia.dao.AirplaneTypeDAO;
import apr.ss.utopia.entity.AirplaneType;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AirplaneService {
    Util util = new Util();

    public List<AirplaneType> fetchAllAirplaneTypes() {
        Connection conn;

        try {
            conn = util.getConnection();
            AirplaneTypeDAO airplaneTypeDAO = new AirplaneTypeDAO(conn);
            return airplaneTypeDAO.readAllAirplaneType();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Airplane Creation Failed! Make sure you entered the correct information.");
            System.out.println(e);
        }
        return null;
    }
}

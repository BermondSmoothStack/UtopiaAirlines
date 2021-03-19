package apr.ss.utopia.dao;

import apr.ss.utopia.entity.Airplane;
import apr.ss.utopia.entity.Airport;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirportDAO extends BaseDAO<Airport> {

    public AirportDAO(Connection conn) {
        super(conn);
    }

    public void addAirplane(Airport airport) throws SQLException {
        save("insert into (" +
                        Airport.CODE + ", " +
                        Airport.CITY + ") " +
                        Airport.NAME + " values (?, ?)",
                new Object[]{
                        airport.getAirportCode(),
                        airport.getCity()
                });
    }

    public void updateAirportCode(Airport airport) throws SQLException {
        save("update " + Airport.NAME + " set " +
                        Airport.CITY + " = ? " +
                        "where " + Airport.CODE + " = ?",
                new Object[]{
                        airport.getCity(),
                        airport.getAirportCode()
                });
    }

    public void deleteAirport(Airport airport) throws SQLException {
        save("delete from " + Airport.NAME + " where " + Airport.CODE + " = ?", new Object[]{airport.getAirportCode()});
    }

    public List<Airport> readAllAirport() throws ClassNotFoundException, SQLException {
        return read("select * from " + Airport.NAME, new Object[]{});
    }

    public List<Airport> readAirportsByCode(Airport airport) throws ClassNotFoundException, SQLException {
        return read("select * from " + Airport.NAME + " where " + Airport.CODE + " = ?", new Object[]{airport.getAirportCode()});
    }

    @Override
    public List<Airport> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
        List<Airport> airports = new ArrayList<>();
        while (rs.next()) {
            Airport a = new Airport();
            a.setAirportCode(rs.getString(Airport.CODE));
            a.setCity(rs.getString(Airport.CITY));
            airports.add(a);
        }

        return airports;
    }
}

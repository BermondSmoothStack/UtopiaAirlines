package apr.ss.utopia.dao;

import apr.ss.utopia.entity.Airplane;
import apr.ss.utopia.entity.Airport;
import apr.ss.utopia.entity.Flight;
import apr.ss.utopia.entity.Route;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlightDAO extends BaseDAO<Flight> {

    public FlightDAO(Connection conn) {
        super(conn);
    }

    public void addFlight(Flight flight) throws SQLException {
        save("insert into (" +
                        Flight.ID + "," +
                        Flight.ROUTE + ", " +
                        Flight.AIRPLANE + ", " +
                        Flight.DEPARTURE + ", " +
//                        Flight.RESERVED_SEATS + "," + Moved to Seats Table
                        Flight.DURATION + "," +
                        Flight.SEAT_PRICE + ") " +
                        Flight.NAME + " values (?,?,?,?,?,?)",
                new Object[]{
                        flight.getId(),
                        flight.getRoute().getId(),
                        flight.getAirplane().getId(),
                        flight.getDepartureTime(),
                        flight.getDuration(),
//                        flight.getReservedSeats(),
                        flight.getSeatPrice()
                });
    }

    public void updateFlight(Flight flight) throws SQLException {
        save("update " + Flight.NAME + " set " +
                        Flight.ROUTE + " = ?, " +
                        Flight.AIRPLANE + " = ?, " +
                        Flight.DEPARTURE + " = ?, " +
                        Flight.DURATION + " = ?, " +
//                        Flight.RESERVED_SEATS + " = ?, " + Moved to Seats Table
                        Flight.SEAT_PRICE + " = ? " +
                        "where " + Flight.ID + " = ?",
                new Object[]{
                        flight.getRoute().getId(),
                        flight.getAirplane().getId(),
                        flight.getDepartureTime(),
                        flight.getDuration(),
//                        flight.getReservedSeats(),
                        flight.getSeatPrice(),
                        flight.getId()
                });
    }

    public void deleteFlight(Flight flight) throws SQLException {
        save("delete from " + Flight.NAME + " where " + Flight.ID + " = ?", new Object[]{flight.getId()});
    }

    public List<Flight> readAllFlight() throws ClassNotFoundException, SQLException {
        return read("select * from " + Flight.NAME, new Object[]{});
    }

    public List<Flight> readFlightsByCode(Flight flight) throws ClassNotFoundException, SQLException {
        return read("select * from " + Flight.NAME + " where " + Flight.ID + " = ?", new Object[]{flight.getId()});
    }

    @Override
    public List<Flight> extractData(ResultSet rs) throws SQLException {
        List<Flight> flights = new ArrayList<>();
        while (rs.next()) {
            Flight f = new Flight();
            Airplane a = new Airplane();
            Route r = new Route();

            a.setId(rs.getInt(Flight.AIRPLANE));
            r.setId(rs.getInt(Flight.ROUTE));

            f.setId(rs.getInt(Flight.ID));
            f.setAirplane(a);
            f.setRoute(r);
            f.setDuration(rs.getInt(Flight.DURATION));
            f.setDepartureTime(rs.getTimestamp(Flight.DEPARTURE).toLocalDateTime());
            f.setSeatPrice(rs.getFloat(Flight.SEAT_PRICE));

            flights.add(f);
        }

        return flights;
    }
}

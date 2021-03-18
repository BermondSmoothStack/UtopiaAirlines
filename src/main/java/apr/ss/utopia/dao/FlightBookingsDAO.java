package apr.ss.utopia.dao;

import apr.ss.utopia.entity.FlightBookings;
import apr.ss.utopia.entity.Route;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FlightBookingsDAO extends BaseDAO<FlightBookings> {

    public FlightBookingsDAO(Connection conn) {
        super(conn);
    }

    public void addFlightBookings(FlightBookings flightBookings) throws SQLException {
        save("insert into (" + FlightBookings.FLIGHT_ID + ", " + FlightBookings.BOOKING_ID + ") " + FlightBookings.NAME + " values (?, ?)", new Object[]{flightBookings.getFlight().getId(), flightBookings.getBooking().getId()});
    }

    public void deleteFlightBookings(FlightBookings flightBookings) throws SQLException {
        save("delete from " + FlightBookings.NAME +
                        " where " + FlightBookings.FLIGHT_ID + " = ? " +
                        "AND " + FlightBookings.BOOKING_ID + " = ?",
                new Object[]{flightBookings.getFlight().getId(), flightBookings.getBooking().getId()});
    }

    public List<FlightBookings> readAllFlightBookings() throws ClassNotFoundException, SQLException {
        return read("select * from " + FlightBookings.NAME, null);
    }

    public List<FlightBookings> readFlightBookingssByCode(FlightBookings flightBookings) throws ClassNotFoundException, SQLException {
        return read("select * from " + FlightBookings.NAME +
                        " where " + FlightBookings.FLIGHT_ID + " = ?" +
                        " and " + FlightBookings.BOOKING_ID + " = ?",
                new Object[]{flightBookings.getFlight().getId(), flightBookings.getBooking().getId()});
    }

    @Override
    public List<FlightBookings> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
        return null;
    }
}

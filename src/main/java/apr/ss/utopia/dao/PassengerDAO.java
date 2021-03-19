package apr.ss.utopia.dao;

import apr.ss.utopia.entity.Booking;
import apr.ss.utopia.entity.Passenger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PassengerDAO extends BaseDAO<Passenger> {

    public PassengerDAO(Connection conn) {
        super(conn);
    }

    public void addPassenger(Passenger passenger) throws SQLException {
        save("insert into (" +
                        Passenger.BOOKING + ", " +
                        Passenger.GVN_NAME + ", " +
                        Passenger.FAM_NAME + ", " +
                        Passenger.DOB + ", " +
                        Passenger.GENDER + ", " +
                        Passenger.ADDR + ", " +
                        ") " + Passenger.NAME + " values (?, ?, ?, ?, ?, ?)",
                new Object[]{
                        passenger.getBooking().getId(),
                        passenger.getGivenName(),
                        passenger.getFamilyName(),
                        java.sql.Date.valueOf(passenger.getDob().toLocalDate()),
                        passenger.getGender(),
                        passenger.getAddress()
                });
    }

    public void updatePassenger(Passenger passenger) throws SQLException {
        save("update " + Passenger.NAME + " set " +
                        Passenger.BOOKING + " = ?, " +
                        Passenger.GVN_NAME + " = ?, " +
                        Passenger.FAM_NAME + " = ?, " +
                        Passenger.DOB + " = ?, " +
                        Passenger.GENDER + " = ?, " +
                        Passenger.ADDR + " = ? " +
                        "where " + Passenger.ID + " = ?",
                new Object[]{
                        passenger.getBooking().getId(),
                        passenger.getGivenName(),
                        passenger.getFamilyName(),
                        java.sql.Date.valueOf(passenger.getDob().toLocalDate()),
                        passenger.getGender(),
                        passenger.getAddress(),
                        passenger.getId()
                });
    }

    public void deletePassenger(Passenger passenger) throws SQLException {
        save("delete from " + Passenger.NAME + " where " +
                        Passenger.ID + " = ?",
                new Object[]{passenger.getId()});
    }

    public List<Passenger> readAllPassenger() throws ClassNotFoundException, SQLException {
        return read("select * from " + Passenger.NAME, new Object[]{});
    }

    public List<Passenger> readPassengersByCode(Passenger passenger) throws ClassNotFoundException, SQLException {
        return read("select * from " + Passenger.NAME + " where " + Passenger.ID + " = ?", new Object[]{passenger.getId()});
    }

    @Override
    public List<Passenger> extractData(ResultSet rs) throws SQLException {
        List<Passenger> passengers = new ArrayList<>();
        while (rs.next()) {
            Passenger p = new Passenger();
            Booking b = new Booking();

            b.setId(rs.getInt(Passenger.BOOKING));

            p.setBooking(b);
            p.setId(rs.getInt(Passenger.ID));
            p.setGivenName(rs.getString(Passenger.GVN_NAME));
            p.setFamilyName(rs.getString(Passenger.FAM_NAME));
            p.setDob(rs.getTimestamp(Passenger.DOB).toLocalDateTime());
            p.setGender(rs.getString(Passenger.GENDER));
            p.setAddress(rs.getString(Passenger.ADDR));
        }
        return passengers;
    }
}

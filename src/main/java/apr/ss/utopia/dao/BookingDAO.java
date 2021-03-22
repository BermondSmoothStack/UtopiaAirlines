package apr.ss.utopia.dao;

import apr.ss.utopia.entity.Booking;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO extends BaseDAO<Booking> {

    public BookingDAO(Connection conn) {
        super(conn);
    }

    public Integer addBooking(Booking booking) throws SQLException {
        return save("insert into " + Booking.NAME + " (" +
                        Booking.ACTIVE + ", " +
                        Booking.CONFIRMATION_CODE + ") " +
                        " values (?, ?)",
                new Object[]{
                        booking.getActive(),
                        booking.getConfirmationCode()
                });
    }

    public void updateBooking(Booking booking) throws SQLException {
        save("update " + Booking.NAME + " set " +
                        Booking.ACTIVE + " = ?, " +
                        Booking.CONFIRMATION_CODE + " = ? " +
                        "where " + Booking.ID + " = ?",
                new Object[]{booking.getActive(), booking.getConfirmationCode(), booking.getId()});
    }

    public boolean deleteBookingByConfirmationCode(Booking booking) throws SQLException {
        return delete("delete from " + Booking.NAME + " where " + Booking.CONFIRMATION_CODE + " = ?", new Object[]{booking.getConfirmationCode()});
    }

    public List<Booking> readAllBooking() throws ClassNotFoundException, SQLException {
        return read("select * from " + Booking.NAME, new Object[]{});
    }

    public List<Booking> readBookingsByCode(Booking booking) throws ClassNotFoundException, SQLException {
        return read("select * from " + Booking.NAME + " where " + Booking.CONFIRMATION_CODE + " = ?", new Object[]{booking.getConfirmationCode()});
    }

    @Override
    public List<Booking> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
        List<Booking> bookings = new ArrayList<>();
        while (rs.next()) {
            Booking b = new Booking();
            b.setId(rs.getInt(Booking.ID));
            b.setActive(rs.getBoolean(Booking.ACTIVE));
            b.setConfirmationCode(rs.getString(Booking.CONFIRMATION_CODE));

            bookings.add(b);
        }
        return bookings;
    }
}
